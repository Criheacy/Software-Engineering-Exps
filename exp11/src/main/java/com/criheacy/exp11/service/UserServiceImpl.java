package com.criheacy.exp11.service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.criheacy.exp11.config.JwtSecretConfig;
import com.criheacy.exp11.dao.UserDAO;
import com.criheacy.exp11.dto.LoginRequestDTO;
import com.criheacy.exp11.dto.LoginResponseDTO;
import com.criheacy.exp11.dto.RegisterRequestDTO;
import com.criheacy.exp11.dto.UserInfoResponseDTO;
import com.criheacy.exp11.entity.UserDO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class UserServiceImpl implements UserService {

  @Autowired UserDAO userDAO;

  @Autowired PasswordEncoder passwordEncoder;

  @Autowired JwtSecretConfig.JwtSecret jwtSecret;

  @Override
  public HttpEntity<UserInfoResponseDTO> register(RegisterRequestDTO requestDTO) {
    log.info("Register info: {}", requestDTO);

    String passwordEnc = passwordEncoder.encode(requestDTO.getPassword());

    UserDO user =
        new UserDO(
            null,
            requestDTO.getName(),
            requestDTO.getGender(),
            requestDTO.getLocation(),
            passwordEnc,
            requestDTO.getDescription());

    log.info("Creating result: {}", userDAO.createUser(user));

    return new HttpEntity<>(UserInfoResponseDTO.from(user));
  }

  @Override
  public HttpEntity<LoginResponseDTO> login(LoginRequestDTO requestDTO) {
    log.info("Login with info: {}", requestDTO);
    UserDO user = userDAO.getAccountByUsername(requestDTO.getName());
    if (user == null) {
      log.info("User with name {} is not found", requestDTO.getName());
      return ResponseEntity.notFound().build();
    }

    if (!passwordEncoder.matches(requestDTO.getPassword(), user.getPasswordEnc())) {
      log.info("Password incorrect: {}", requestDTO.getPassword());
      return ResponseEntity.badRequest().build();
    }

    String token =
        JWT.create()
            .withJWTId(String.valueOf(user.getId()))
            .withClaim("name", user.getName())
            .sign(Algorithm.HMAC256(jwtSecret.getSecret()));

    log.info("Logged in, generating token: {}", token);
    return ResponseEntity.ok(new LoginResponseDTO(user.getId(), user.getName(), token));
  }

  @Override
  public HttpEntity<List<UserInfoResponseDTO>> listUsersInfo() {
    return ResponseEntity.ok(
        userDAO.listAllUsers().stream().map(UserInfoResponseDTO::from).toList());
  }
}
