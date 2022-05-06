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
public class LightShowElasticDto implements Serializable {

    @JsonProperty("id")
    private String id;
    @JsonProperty("title")
    private String title;
    @JsonProperty("photo")
    private String photo;
    @JsonProperty("description")
    private String description;
    @JsonProperty("locationId")
    private String locationId;
    @JsonProperty("locationName")
    private String locationName;
    @JsonProperty("locationAddress")
    private String locationAddress;
    @JsonProperty("locationDescription")
    private String locationDescription;
    @JsonProperty("locationUrl")
    private String locationUrl;

    public LightShowElasticDto(){}

}
