package com.project.elasticsearch.dto.elastic;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

@Getter
@Setter
@JsonInclude(Include.NON_NULL)
public class LightArtworkElasticDto implements Serializable {

    @JsonProperty("id")
    private String id;
    @JsonProperty("title")
    private String title;
    @JsonProperty("year")
    private Integer year;
    @JsonProperty("photo")
    private String photo;
    @JsonProperty("description")
    private String description;
    @JsonProperty("height")
    private Double height;
    @JsonProperty("length")
    private Double length;
    @JsonProperty("depth")
    private Double depth;
    @JsonProperty("artist")
    private LightArtistElasticDto artist;
    @JsonProperty("show")
    private LightShowElasticDto show;
    @JsonProperty("approved")
    private String approved;
    @JsonProperty("creationDate")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy hh:mm:ss")
    private Date creationDate;
    @JsonProperty("rejectionReason")
    private String rejectionReason;

    public LightArtworkElasticDto() {
    }
}
