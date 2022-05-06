package com.project.elasticsearch.dto.user;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.project.elasticsearch.dto.AddressDto;
import com.project.elasticsearch.model.User;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Setter
@Getter
@JsonInclude(Include.NON_NULL)
public class UserDto extends LightUserDto implements Serializable {

  @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
  private String password;
  //  @NotBlank(message = "Password cannot be empty.")

  @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
  private String matchingPassword;
  private String birthDate;
  private String biography;
  private AddressDto address;

  public UserDto() {
  }

  public UserDto(User u) {
    this.setId(u.getId());
    this.setEmail(u.getEmail());
    this.setFirstName(u.getFirstName());
    this.setLastName(u.getLastName());
    this.setRole(u.getRole());
  }
}
