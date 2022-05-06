package com.project.elasticsearch.dto.elastic;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@JsonInclude(Include.NON_NULL)
public class ArtworkElasticDto extends ElasticDto implements Serializable {

    @JsonProperty("show")
    private LightShowElasticDto show;
    @JsonProperty("artist")
    private LightArtistElasticDto artist;
    @JsonProperty("title")
    private String title;
    @JsonProperty("year")
    private Integer year;
    @JsonProperty("medium")
    private String medium;
    @JsonProperty("genre")
    private List<String> genre;
    @JsonProperty("height")
    private Double height;
    @JsonProperty("length")
    private Double length;
    @JsonProperty("depth")
    private Double depth;
    @JsonProperty("photo")
    private String photo;
    @JsonProperty("description")
    private String description;
    @JsonProperty("provenance")
    private List<String> provenance;
    @JsonProperty("galleries")
    private List<LightGalleryElasticDto> galleries;
    @JsonProperty("toUpdate")
    private Long toUpdate;
    @JsonProperty("rejectionReason")
    private String rejectionReason;
    @JsonProperty("exhibited")
    private List<LightShowElasticDto> exhibited;
    @JsonProperty("literature")
    private List<String> literature;
    @JsonProperty("visible")
    private Boolean visible;
    @JsonProperty("approved")
    private String approved;
    @JsonProperty("approvedBy")
    private LightUserElasticDto approvedBy;
    @JsonProperty("approvedDate")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy hh:mm:ss")
    private Date approvedDate;
    @JsonProperty("state")
    private String state;
    @JsonProperty("submittedBy")
    private LightUserElasticDto submittedBy;
    @JsonProperty("submittedDate")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy hh:mm:ss")
    private Date submittedDate;
    @JsonProperty("creationDate")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy hh:mm:ss")
    private Date creationDate;
    @JsonProperty("dimensionUnits")
    private String dimensionUnits;

    public ArtworkElasticDto(){}

}
