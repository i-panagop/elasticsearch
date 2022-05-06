package com.project.elasticsearch.dto.elastic;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.project.elasticsearch.model.User;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

@Getter
@Setter
@JsonInclude(Include.NON_NULL)
public class UserElasticDto extends ElasticDto implements Serializable {

    @JsonProperty("email")
    private String email;
    @JsonProperty("firstName")
    private String firstName;
    @JsonProperty("middleName")
    private String middleName;
    @JsonProperty("lastName")
    private String lastName;
    @JsonProperty("photo")
    private String photo;
    @JsonProperty("companyName")
    private String companyName;
    @JsonProperty("birthDate")
    private String birthDate;
    @JsonProperty("nickname")
    private String nickname;
    @JsonProperty("displayNickName")
    private Boolean displayNickName;
    @JsonProperty("contactInfo")
    private ContactInfoElasticDto contactInfo;
    @JsonProperty("biography")
    private String biography;
    @JsonProperty("password")
    private String password;
    @JsonProperty("role")
    private String role;
    @JsonProperty("address")
    private AddressElasticDto address;
    @JsonProperty("active")
    private Boolean active;
    @JsonProperty("creationDate")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy hh:mm:ss")
    private Date creationDate;
    @JsonProperty("lastLoginDate")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy hh:mm:ss")
    private Date lastLoginDate;
    @JsonProperty("rejectionReason")
    private String rejectionReason;

    public UserElasticDto() {
    }

    public UserElasticDto(User user) {
        super(String.valueOf(user.getId()));
        this.email = user.getEmail();
        this.firstName = user.getFirstName();
        this.middleName = user.getMiddleName();
        this.lastName = user.getLastName();
        this.photo = user.getPhoto();
        this.companyName = user.getCompanyName();
        this.birthDate = user.getBirthDate();
        this.nickname = user.getNickname();
        this.displayNickName = user.isDisplayNickName();
        if (Objects.nonNull(user.getContactInfo())) {
            this.contactInfo = new ContactInfoElasticDto(user.getContactInfo());
        }
        this.biography = user.getBiography();
        this.password = user.getPassword();
        this.role = Objects.nonNull(user.getRole()) ? user.getRole().toString() : null;
        if (Objects.nonNull(user.getAddress())) {
            this.address = new AddressElasticDto(user.getAddress());
        }
        this.active = user.getActive();
        this.creationDate = user.getCreationDate();
        this.lastLoginDate = user.getLastLoginDate();
        this.rejectionReason = user.getRejectionReason();
    }
}
