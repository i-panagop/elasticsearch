package com.project.elasticsearch.dto.elastic;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@JsonInclude(Include.NON_NULL)
public class ElasticDto implements Serializable {

    @JsonProperty("id")
    private String id;

    public ElasticDto(){}

    public ElasticDto(String id){
        this.id = id;
    }

}
