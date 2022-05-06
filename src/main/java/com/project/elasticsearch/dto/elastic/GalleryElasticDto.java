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
public class GalleryElasticDto extends UserElasticDto implements Serializable {

    @JsonProperty("name")
    private String name;
    @JsonProperty("contactPerson")
    private String contactPerson;
    @JsonProperty("contactPersonTitle")
    private String contactPersonTitle;
    @JsonProperty("contactPersonEmail")
    private String contactPersonEmail;
    @JsonProperty("additionalContactPerson")
    private String additionalContactPerson;
    @JsonProperty("additionalContactPersonTitle")
    private String additionalContactPersonTitle;
    @JsonProperty("additionalContactPersonEmail")
    private String additionalContactPersonEmail;
    @JsonProperty("contactDetails")
    private String contactDetails;
    @JsonProperty("myArtists")
    private List<LightArtistElasticDto> myArtists;
    @JsonProperty("myCurators")
    private List<LightCuratorElasticDto> myCurators;
    @JsonProperty("myArtworks")
    private List<LightArtworkElasticDto> myArtworks;
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
    @JsonProperty("eshopUrl")
    private String eshopUrl;


    public GalleryElasticDto(){}

}
