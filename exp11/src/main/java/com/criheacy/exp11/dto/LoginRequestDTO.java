package com.criheacy.exp11.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class LoginRequestDTO {
  private String name;
  private String password;
}
