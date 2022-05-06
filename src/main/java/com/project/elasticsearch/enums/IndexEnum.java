package com.project.elasticsearch.enums;


import com.project.elasticsearch.utils.Constants;

public enum IndexEnum {

    ARTIST(Constants.ARTIST_INDEX, Constants.ARTIST_MAPPING_JSON),
    ARTWORK(Constants.ARTWORK_INDEX, Constants.ARTWORK_MAPPING_JSON),
    CURATOR_ARTWORK(Constants.CURATOR_ARTWORK_INDEX, Constants.CURATOR_ARTWORK_MAPPING_JSON),
    CURATOR(Constants.CURATOR_INDEX, Constants.CURATOR_MAPPING_JSON),
    GALLERY(Constants.GALLERY_INDEX, Constants.GALLERY_MAPPING_JSON),
    LOCATION(Constants.LOCATION_INDEX, Constants.LOCATION_MAPPING_JSON),
    SHOW(Constants.SHOW_INDEX, Constants.SHOW_MAPPING_JSON),
    USER(Constants.USER_INDEX, Constants.USER_MAPPING_JSON);

    private String indexName;
    private String indexMapping;

    IndexEnum(String indexName, String indexMapping) {
        this.indexName = indexName;
        this.indexMapping = indexMapping;
    }

    public String getIndexName() {
        return indexName;
    }

    public void setIndexName(String indexName) {
        this.indexName = indexName;
    }

    public String getIndexMapping() {
        return indexMapping;
    }

    public void setIndexMapping(String indexMapping) {
        this.indexMapping = indexMapping;
    }
}
