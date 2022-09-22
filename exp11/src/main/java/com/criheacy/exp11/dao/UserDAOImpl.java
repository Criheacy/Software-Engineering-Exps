package com.criheacy.exp11.dao;

import com.criheacy.exp11.entity.UserDO;
import com.criheacy.exp11.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UserDAOImpl implements UserDAO {

  @Autowired UserMapper userMapper;

  @Override
  public int createUser(UserDO user) {
    return userMapper.createUser(user);
  }

  @Override
  public UserDO getAccountByUsername(String name) {
    return userMapper.getAccountByUsername(name);
  }

  @Override
  public List<UserDO> listAllUsers() {
    return userMapper.listAllUsers();
  }
}
