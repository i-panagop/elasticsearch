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
public class CuratorElasticDto extends UserElasticDto implements Serializable {

    @JsonProperty("galleries")
    private List<LightGalleryElasticDto> galleries;
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

    public CuratorElasticDto(){}
}
