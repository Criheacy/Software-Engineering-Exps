package com.criheacy.exp11.dto;

import com.criheacy.exp11.entity.UserDO;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UserInfoResponseDTO {
  private Integer id;
  private String name;
  private String gender;
  private String location;
  private String description;

  public static UserInfoResponseDTO from(UserDO user) {
    return new UserInfoResponseDTO(
        user.getId(), user.getName(), user.getGender(), user.getLocation(), user.getDescription());
  }
}
