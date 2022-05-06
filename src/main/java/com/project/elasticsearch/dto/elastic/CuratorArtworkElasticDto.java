package com.project.elasticsearch.dto.elastic;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Objects;

@Getter
@Setter
@JsonInclude(Include.NON_NULL)
public class CuratorArtworkElasticDto extends ElasticDto implements Serializable {

    @JsonProperty("artwork")
    private LightArtworkElasticDto artwork;
    @JsonProperty("curator")
    private LightCuratorElasticDto curator;

    public CuratorArtworkElasticDto() {
    }

}
