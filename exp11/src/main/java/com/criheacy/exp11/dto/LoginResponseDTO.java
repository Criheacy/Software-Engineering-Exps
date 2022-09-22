package com.criheacy.exp11.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class LoginResponseDTO {
  private Integer id;
  private String name;
  private String token;
}
