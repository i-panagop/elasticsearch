package com.project.elasticsearch.service;


import static com.project.elasticsearch.utils.Constants.EMAIL_ANALYZED;
import static com.project.elasticsearch.utils.Constants.ERROR_DURING_TEXT_SEARCH_FOR_INDEX;
import static com.project.elasticsearch.utils.Constants.ID_ANALYZED;
import static com.project.elasticsearch.utils.Constants._LOCAL;

import co.elastic.clients.elasticsearch._types.FieldValue;
import co.elastic.clients.elasticsearch._types.SortOptions;
import co.elastic.clients.elasticsearch._types.SortOrder;
import co.elastic.clients.elasticsearch._types.query_dsl.Query;
import co.elastic.clients.elasticsearch.core.SearchRequest;
import co.elastic.clients.elasticsearch.core.SearchResponse;
import co.elastic.clients.elasticsearch.core.search.Hit;
import com.project.elasticsearch.client.ElasticSearchClient;
import com.project.elasticsearch.dto.ListResponseDto;
import com.project.elasticsearch.dto.elastic.ElasticDto;
import com.project.elasticsearch.enums.ElasticFieldEnum;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.collections4.MapUtils;
import org.apache.commons.lang3.StringUtils;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.modelmapper.convention.MatchingStrategies;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

public interface ElasticSearchService<T> {

    static final Logger logger = LoggerFactory.getLogger(ElasticSearchService.class);

    Type getTargetListType();

    <T> T getTargetType();

    String getIndexName();

    String[] getSearchablefields();

    ElasticSearchClient getClient();

    PropertyMap getPropertyMapElastic();

    default ListResponseDto search(String searchText, int page, int size, Map<String, List<Object>> filters, String sortField, Boolean asc) {
        try {
            ElasticSearchClient esClient = getClient();
            SearchRequest sr = SearchRequest.of(s -> s
                .index(getIndexName())
                .from(size * page)
                .size(size)
                .trackTotalHits(b -> b.enabled(true))
                .query(createSearchQuery(searchText, filters))
                .sort(createSorting(sortField, asc))
                .preference(_LOCAL)
            );
            SearchResponse<ElasticDto> searchResponse =
                esClient.initClientAndExecute(
                    client ->
                        client.search(sr, getTargetType())
                );
            return createResponse(searchResponse, size);
        } catch (Exception e) {
            logger.error(ERROR_DURING_TEXT_SEARCH_FOR_INDEX, getIndexName(), e);
        }
        return new ListResponseDto();
    }

    default Object findByEmail(String id) {
        try {
            ElasticSearchClient esClient = getClient();
            SearchRequest sr = SearchRequest.of(s -> s
                .index(getIndexName())
                .size(1)
                .trackTotalHits(b -> b.enabled(true))
                .query(q -> q.term(t -> t.field(EMAIL_ANALYZED).value(id)))
                .preference(_LOCAL)
            );
            SearchResponse<ElasticDto> searchResponse = esClient.initClientAndExecute(client -> client.search(sr, getTargetType()));
            return createResponse(searchResponse);
        } catch (Exception e) {
            logger.error(ERROR_DURING_TEXT_SEARCH_FOR_INDEX, getIndexName(), e);
        }
        return null;
    }

    default Object findById(String id) {
        try {
            ElasticSearchClient esClient = getClient();
            SearchRequest sr = SearchRequest.of(s -> s
                .index(getIndexName())
                .size(1)
                .trackTotalHits(b -> b.enabled(true))
                .query(q -> q.term(t -> t.field(ID_ANALYZED).value(id)))
                .preference(_LOCAL)
            );
            SearchResponse<ElasticDto> searchResponse = esClient.initClientAndExecute(client -> client.search(sr, getTargetType()));
            return createResponse(searchResponse);
        } catch (Exception e) {
            logger.error(ERROR_DURING_TEXT_SEARCH_FOR_INDEX, getIndexName(), e);
        }
        return null;
    }

    default Query createSearchQuery(String searchText, Map<String, List<Object>> filters) {
        if (StringUtils.isBlank(searchText) && MapUtils.isEmpty(filters)) {
            return Query.of(q -> q.matchAll(ma -> ma));
        }
        Query searchTermQuery = null;
        Query filterQuery = null;
        if (StringUtils.isNotBlank(searchText)) {
            String[] searchTerms = searchText.split(StringUtils.SPACE);
            List<Query> searchFiledUpperQueries = new ArrayList<>();
            List<Query> searchFiledLowerQueries = new ArrayList<>();
            for (String searchField : getSearchablefields()) {
                List<Query> upperCaseQueries = new ArrayList<>();
                List<Query> lowerCaseQueries = new ArrayList<>();
                for (String searchTerm : searchTerms) {
                    upperCaseQueries.add(Query.of(q -> q.regexp(re -> re.field(searchField).value(".*" + searchTerm.toUpperCase() + ".*"))));
                    lowerCaseQueries.add(Query.of(q -> q.regexp(re -> re.field(searchField).value(".*" + searchTerm.toLowerCase() + ".*"))));
                }
                searchFiledUpperQueries.add(Query.of(q -> q.bool(b -> b.must(upperCaseQueries))));
                searchFiledLowerQueries.add(Query.of(q -> q.bool(b -> b.must(lowerCaseQueries))));
            }
            searchTermQuery = Query.of(q -> q.bool(b -> b.should(
                List.of(Query.of(q1 -> q1.bool(b1 -> b1.should(searchFiledUpperQueries))),
                    Query.of(q2 -> q2.bool(b2 -> b2.should(searchFiledLowerQueries)))))));
        }
        if (MapUtils.isNotEmpty(filters)) {
            List<Query> filterQueries = new ArrayList<>();
            for (Map.Entry<String, List<Object>> entry : filters.entrySet()) {
                if (StringUtils.isBlank(entry.getKey()) || CollectionUtils.isEmpty(entry.getValue())) {
                    continue;
                }
                ElasticFieldEnum elEnum = ElasticFieldEnum.findById(entry.getKey());
                if (Objects.nonNull(elEnum) && StringUtils.isNotBlank(elEnum.getNestedPath())) {
                    filterQueries.add(
                        Query.of(q -> q
                            .nested(n -> n
                                .path(elEnum.getNestedPath())
                                .query(q1 -> q1
                                    .terms(t -> t
                                        .field(entry.getKey())
                                        .terms(terms -> terms
                                            .value(entry.getValue().stream().map(String::valueOf).map(FieldValue::of).collect(Collectors.toList())))
                                    )
                                )
                            )
                        )
                    );
                } else {
                    filterQueries.add(Query.of(q -> q
                            .terms(t -> t
                                .field(entry.getKey())
                                .terms(terms -> terms
                                    .value(entry.getValue().stream().map(String::valueOf).map(FieldValue::of).collect(Collectors.toList())))
                            )
                        )
                    );
                }
            }
            if (CollectionUtils.isNotEmpty(filterQueries)) {
                filterQuery = Query.of(q -> q.bool(b -> b.must(filterQueries)));
            }
        }
        Query searchQuery;
        Query finalSearchTermQuery = searchTermQuery;
        Query finalFilterQuery = filterQuery;
        if (Objects.nonNull(finalSearchTermQuery) && Objects.nonNull(finalFilterQuery)) {
            searchQuery = Query.of(q -> q.bool(b -> b.must(List.of(finalSearchTermQuery, finalFilterQuery))));
        } else if (Objects.nonNull(finalFilterQuery)) {
            searchQuery = Query.of(q -> q.bool(b -> b.must(finalFilterQuery)));
        } else if (Objects.nonNull(finalSearchTermQuery)) {
            searchQuery = Query.of(q -> q.bool(b -> b.must(finalSearchTermQuery)));
        } else {
            searchQuery = Query.of(q -> q.matchAll(ma -> ma));
        }
        return searchQuery;
    }

    default SortOptions createSorting(String sortField, Boolean asc) {
        if (StringUtils.isNotBlank(sortField)) {
            if (Boolean.TRUE.equals(asc)) {
                return SortOptions.of(so -> so.field(f -> f.field(sortField).order(SortOrder.Asc)));
            } else {
                return SortOptions.of(so -> so.field(f -> f.field(sortField).order(SortOrder.Desc)));
            }
        } else {
            return SortOptions.of(so -> so.field(f -> f.field(ID_ANALYZED).order(SortOrder.Asc)));
        }
    }

    @SuppressWarnings("unchecked")
    default ListResponseDto createResponse(SearchResponse<ElasticDto> searchResponse, int size) {
        ListResponseDto response = new ListResponseDto();
        if (Objects.isNull(searchResponse.hits()) || searchResponse.hits().hits().size() <= 0) {
            response.setResults(new ArrayList<>());
            return response;
        }
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        modelMapper.getConfiguration().setAmbiguityIgnored(true);
        if (Objects.nonNull(getPropertyMapElastic())) {
            modelMapper.addMappings(getPropertyMapElastic());
        }
        response.setResults(
            modelMapper.map(searchResponse.hits().hits().stream().map(Hit::source).collect(Collectors.toList()), getTargetListType()));
        response.setCount(Objects.nonNull(searchResponse.hits().total()) ? searchResponse.hits().total().value() : 0);
        response.setTotalPages((int) (response.getCount() / size) + 1);
        return response;
    }

    default Object createResponse(SearchResponse<ElasticDto> searchResponse) {
        if (Objects.isNull(searchResponse.hits()) || searchResponse.hits().hits().size() <= 0) {
            return null;
        }
        return searchResponse.hits().hits().get(0).source();
    }
}
