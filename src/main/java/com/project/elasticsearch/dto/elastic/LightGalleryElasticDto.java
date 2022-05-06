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
public class LightGalleryElasticDto implements Serializable {

    @JsonProperty("id")
    private String id;
    @JsonProperty("name")
    private String name;
    @JsonProperty("contactPerson")
    private String contactPerson;
    @JsonProperty("contactPersonTitle")
    private String contactPersonTitle;
    @JsonProperty("contactPersonEmail")
    private String contactPersonEmail;
    @JsonProperty("contactDetails")
    private String contactDetails;

    public LightGalleryElasticDto(){}

}
