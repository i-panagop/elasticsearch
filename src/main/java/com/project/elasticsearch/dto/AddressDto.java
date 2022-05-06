package com.project.elasticsearch.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@JsonInclude(Include.NON_NULL)
@Getter
@Setter
public class AddressDto implements Serializable {

  private Long id;
  private String street;
  private String street2;
  private String street3;
  private Integer streetNo;
  private String county;
  private String zipCode;
  private String city;
  private String country;

}
