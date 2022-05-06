package com.project.elasticsearch.dto;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Setter
@Getter
public class ListResponseDto implements Serializable {

  Object results;
  int totalPages;
  long count;

}
