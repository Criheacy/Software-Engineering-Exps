package com.criheacy.exp3.service;

import com.criheacy.exp3.entity.MyUser;

import java.util.List;

public interface UserService {
  boolean register(String username, String gender);

  List<MyUser> listUsers();

  boolean clearAllUsers();
}
