package com.project.elasticsearch.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@JsonInclude(Include.NON_NULL)
public class ContactInfoDto implements Serializable {

  private String phone;
  private String email;
  private String website;
  private String instagram;
}
