package com.project.elasticsearch.dto.elastic;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.project.elasticsearch.model.ContactInfo;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@JsonInclude(Include.NON_NULL)
public class ContactInfoElasticDto implements Serializable {

    @JsonProperty("id")
    private String id;
    @JsonProperty("email")
    private String email;
    @JsonProperty("phone")
    private String phone;
    @JsonProperty("website")
    private String website;
    @JsonProperty("instagram")
    private String instagram;

    public ContactInfoElasticDto(){}

    public ContactInfoElasticDto(ContactInfo contactInfo){
        this.id = String.valueOf(contactInfo.getId());
        this.email = contactInfo.getEmail();
        this.phone = contactInfo.getPhone();
        this.website = contactInfo.getWebsite();
        this.instagram = contactInfo.getInstagram();
    }

}
