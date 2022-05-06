package com.project.elasticsearch.model;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "address")
@Getter
@Setter
public class Address implements Serializable {

    @Id
    @GeneratedValue
    private Long id;
    private String street;
    private String street2;
    private String street3;
    private Integer streetNo;
    private String county;
    private String zipCode;
    private String city;
    private String country;


    public Address(Address address) {
        this(address.getStreet(), address.getStreet2(), address.getStreet3(), address.getStreetNo(), address.getCounty(), address.getStreet(),
            address.getCity(), address.getCountry());
    }

    public Address(String street, String street2, String street3, Integer streetNo, String county, String zipCode, String city, String country) {
        this.street = street;
        this.street2 = street2;
        this.street3 = street3;
        this.streetNo = streetNo;
        this.county = county;
        this.zipCode = zipCode;
        this.city = city;
        this.country = country;
    }

    public Address() {

    }
}
