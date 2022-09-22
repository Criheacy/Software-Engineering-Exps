package com.criheacy.exp11.config;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.*;
import java.security.SecureRandom;

@Slf4j
@Configuration
public class JwtSecretConfig {

  @Value("${application.jwt-secret-path}")
  private String jwtSecretPath;

  @Bean
  public JwtSecret jwtSecret() {
    String secret;
    try {
      secret = readSecret();
    } catch (IOException e1) {
      SecureRandom generator = new SecureRandom();
      byte[] randomBytes = new byte[16];
      generator.nextBytes(randomBytes);
      secret = new String(randomBytes);
      try {
        writeSecret(secret);
      } catch (IOException e2) {
        throw new RuntimeException(e2);
      }
    }

    return new JwtSecret(secret);
  }

  private String readSecret() throws IOException {
    try {
      return new BufferedReader(new FileReader(jwtSecretPath)).readLine();
    } catch (IOException e) {
      log.error("Jwt Reading Error", e);
      throw e;
    }
  }

  private void writeSecret(String secret) throws IOException {
    File secretFile = new File(jwtSecretPath);
    if (!secretFile.createNewFile()) {
      throw new IOException("JWT secret file already exist");
    }
    PrintWriter writer = new PrintWriter(secretFile);
    writer.print(secret);
    writer.close();
  }

  @AllArgsConstructor
  public static class JwtSecret {
    @Getter private String secret;
  }
}
