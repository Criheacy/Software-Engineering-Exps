package com.criheacy.exp11.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class RegisterRequestDTO {
  private String name;
  private String gender;
  private String location;
  private String password;
  private String description;
}
