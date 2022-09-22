package com.criheacy.exp11.controller;

import com.criheacy.exp11.annotation.AuthRequired;
import com.criheacy.exp11.dao.UserDAO;
import com.criheacy.exp11.dto.LoginRequestDTO;
import com.criheacy.exp11.dto.LoginResponseDTO;
import com.criheacy.exp11.dto.RegisterRequestDTO;
import com.criheacy.exp11.dto.UserInfoResponseDTO;
import com.criheacy.exp11.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
public class UserController {

  @Autowired UserDAO userDAO;

  @Autowired UserService userService;

  @PostMapping("user")
  public HttpEntity<UserInfoResponseDTO> register(@RequestBody RegisterRequestDTO requestDTO) {
    return userService.register(requestDTO);
  }

  @PostMapping("login")
  public HttpEntity<LoginResponseDTO> login(@RequestBody LoginRequestDTO requestDTO) {
    return userService.login(requestDTO);
  }

  @AuthRequired
  @GetMapping("user")
  public HttpEntity<List<UserInfoResponseDTO>> getUsersInfo() {
    return userService.listUsersInfo();
  }
}
