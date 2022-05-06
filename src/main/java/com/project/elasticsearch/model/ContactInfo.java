package com.project.elasticsearch.model;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "contact_info")
@Getter
@Setter
public class ContactInfo implements Serializable {

    @Id
    @GeneratedValue
    private Long id;
    private String email;
    private String phone;
    private String website;
    private String instagram;

    public ContactInfo() {
    }

    public ContactInfo(ContactInfo ci) {
        this(ci.getEmail(), ci.getPhone(), ci.getWebsite(), ci.getInstagram());
    }

    public ContactInfo(String email, String phone, String website, String instagram) {
        this.email = email;
        this.phone = phone;
        this.website = website;
        this.instagram = instagram;
    }
}
