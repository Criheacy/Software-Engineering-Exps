package com.criheacy.exp11.service;

import com.criheacy.exp11.dto.LoginRequestDTO;
import com.criheacy.exp11.dto.LoginResponseDTO;
import com.criheacy.exp11.dto.RegisterRequestDTO;
import com.criheacy.exp11.dto.UserInfoResponseDTO;
import org.springframework.http.HttpEntity;

import java.util.List;

public interface UserService {
  HttpEntity<UserInfoResponseDTO> register(RegisterRequestDTO requestDTO);

  HttpEntity<LoginResponseDTO> login(LoginRequestDTO requestDTO);

  HttpEntity<List<UserInfoResponseDTO>> listUsersInfo();
}
