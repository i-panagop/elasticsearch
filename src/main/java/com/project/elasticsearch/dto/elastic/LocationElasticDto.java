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
public class LocationElasticDto extends ElasticDto implements Serializable {

    @JsonProperty("name")
    private String name;
    @JsonProperty("address")
    private AddressElasticDto address;
    @JsonProperty("description")
    private String description;
    @JsonProperty("url")
    private String url;
    @JsonProperty("year")
    private Integer year;
    @JsonProperty("toUpdate")
    private Long toUpdate;
    @JsonProperty("approved")
    private String approved;
    @JsonProperty("approvedBy")
    private LightUserElasticDto approvedBy;
    @JsonProperty("rejectionReason")
    private String rejectionReason;
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

    public LocationElasticDto(){}

}
