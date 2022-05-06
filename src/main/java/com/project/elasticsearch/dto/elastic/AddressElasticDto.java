package com.project.elasticsearch.dto.elastic;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.project.elasticsearch.model.Address;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@JsonInclude(Include.NON_NULL)
public class AddressElasticDto implements Serializable {

    @JsonProperty("id")
    private String id;
    @JsonProperty("street")
    private String street;
    @JsonProperty("street2")
    private String street2;
    @JsonProperty("street3")
    private String street3;
    @JsonProperty("streetNo")
    private Integer streetNo;
    @JsonProperty("county")
    private String county;
    @JsonProperty("zipCode")
    private String zipCode;
    @JsonProperty("city")
    private String city;
    @JsonProperty("country")
    private String country;

    public AddressElasticDto(){}

    public AddressElasticDto(Address address){
        this.id = String.valueOf(address.getId());
        this.street = address.getStreet();
        this.street2 = address.getStreet2();
        this.street3 = address.getStreet3();
        this.streetNo = address.getStreetNo();
        this.county = address.getCounty();
        this.zipCode = address.getZipCode();
        this.city = address.getCity();
        this.country = address.getCountry();
    }

}
