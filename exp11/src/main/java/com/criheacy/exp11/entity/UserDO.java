package com.criheacy.exp11.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDO {
  private Integer id;
  private String name;
  private String gender;
  private String location;
  private String passwordEnc;
  private String description;
}
