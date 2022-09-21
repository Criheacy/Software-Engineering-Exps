package com.criheacy.exp3.controller;

import com.criheacy.exp3.entity.MyUser;
import com.criheacy.exp3.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {

  @Autowired
  UserService userService;

  @GetMapping("user")
  public List<MyUser> listUsers() {
    return userService.listUsers();
  }

  @PostMapping("user")
  public ResponseEntity<?> register(@RequestBody MyUser user) {
    return userService.register(user.getUsername(), user.getGender())
      ? ResponseEntity.ok().build()
      : ResponseEntity.badRequest().build();
  }

  @DeleteMapping("user")
  public ResponseEntity<?> clearAllUsers() {
    return userService.clearAllUsers()
      ? ResponseEntity.ok().build()
      : ResponseEntity.badRequest().build();
  }
}
