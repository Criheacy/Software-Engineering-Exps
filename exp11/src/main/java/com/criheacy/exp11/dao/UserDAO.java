package com.criheacy.exp11.dao;

import com.criheacy.exp11.entity.UserDO;

import java.util.List;

public interface UserDAO {
  int createUser(UserDO user);
  UserDO getAccountByUsername(String name);
  List<UserDO> listAllUsers();
}
