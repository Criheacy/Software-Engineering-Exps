package com.criheacy.exp11.interceptor;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.criheacy.exp11.annotation.AuthRequired;
import com.criheacy.exp11.config.JwtSecretConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
@Component
public class AuthInterceptor implements HandlerInterceptor {

  @Autowired JwtSecretConfig.JwtSecret jwtSecret;

  @Override
  public boolean preHandle(
      HttpServletRequest request, HttpServletResponse response, Object handler) {
    if (!(handler instanceof HandlerMethod)) {
      return true;
    }

    // no authorization is required
    if (!((HandlerMethod) handler).getMethod().isAnnotationPresent(AuthRequired.class)) {
      return true;
    }

    log.info(
        "Checking authentication on @AuthRequired method: {}",
        ((HandlerMethod) handler).getMethod().getName());

    String authHeader = request.getHeader(HttpHeaders.AUTHORIZATION);
    // check if token exist
    if (authHeader == null) {
      log.info("Token is not provided, authentication failed");
      sendFailedResponse(response);
      return false;
    }
    // check if token's format is valid
    if (!authHeader.startsWith("Bearer ")) {
      log.info("Invalid header format: {}", authHeader);
      sendFailedResponse(response);
      return false;
    }
    String token = authHeader.split(" ")[1].trim();
    log.info("Authentication token: {}", token);

    try {
      JWT.require(Algorithm.HMAC256(jwtSecret.getSecret())).build().verify(token);
    } catch (JWTVerificationException e) {
      log.info("Token verification failed, authentication terminated");
      sendFailedResponse(response);
      return false;
    }
    log.info("Authentication passed");
    return true;
  }

  private void sendFailedResponse(HttpServletResponse response) {
    try {
      response.sendError(HttpStatus.UNAUTHORIZED.value());
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }
}
