package com.criheacy.exp3.dao;

import com.criheacy.exp3.entity.MyUser;

import java.util.List;

public interface UserDAO {

  List<MyUser> get();

  int save(MyUser user);

  int remove();
}
