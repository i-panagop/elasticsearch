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
public class ShowElasticDto extends ElasticDto implements Serializable {

    @JsonProperty("creator")
    private LightUserElasticDto creator;
    @JsonProperty("photo")
    private String photo;
    @JsonProperty("title")
    private String title;
    @JsonProperty("dateFrom")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy hh:mm:ss")
    private Date dateFrom;
    @JsonProperty("dateTo")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy hh:mm:ss")
    private Date dateTo;
    @JsonProperty("inGallery")
    private Boolean inGallery;
    @JsonProperty("description")
    private String description;
    @JsonProperty("fair")
    private LightLocationElasticDto fair;
    @JsonProperty("artists")
    private List<LightArtistElasticDto> artists;
    @JsonProperty("curators")
    private List<LightUserElasticDto> curators;
    @JsonProperty("curatorArtworks")
    private List<LightCuratorArtworkElasticDto> curatorArtworks;
    @JsonProperty("lastUpdated")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy hh:mm:ss")
    private Date lastUpdated;
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

    public ShowElasticDto(){}

}
