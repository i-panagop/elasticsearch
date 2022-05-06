package com.project.elasticsearch.dto.user;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.project.elasticsearch.dto.ContactInfoDto;
import com.project.elasticsearch.model.RoleEnum;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.StringUtils;

import java.io.Serializable;
import java.util.Date;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Setter
@Getter
@JsonInclude(Include.NON_NULL)
public class LightUserDto implements Serializable {

  private Long id;
  @NotNull
  @NotBlank(message = "Email address is not valid.")
  @Email(message = "No valid email format")
  private String email;
  @NotNull
  @NotBlank(message = "First name cannot be empty.")
  private String firstName;
  private String middleName;
  private String companyName;
  @NotNull
  @NotBlank(message = "Last name cannot be empty.")
  private String lastName;
  private String nickname;
  private boolean displayNickname;
  private ContactInfoDto contactInfo;
  private String rejectionReason;

  @NotNull
  private RoleEnum role;

  private String photo;
  private String thumb;
  @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
  private Date creationDate;

  public String getThumb() {
    if (!StringUtils.isEmpty(photo)) {
      return photo.replace("user/", "user/thumb/");
    }
    return null;
  }


}
