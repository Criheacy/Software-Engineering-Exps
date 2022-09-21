package com.criheacy.exp3.service;

import com.criheacy.exp3.dao.UserDAO;
import com.criheacy.exp3.entity.MyUser;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service
@Transactional
public class UserServiceImpl implements UserService {

  @Autowired
  UserDAO userDAO;

  @Override
  public boolean register(String username, String gender) {
    MyUser newUser = new MyUser(null, username, gender);
    log.info("Creating user: {}", newUser);
    return 1 == userDAO.save(newUser);
  }

  @Override
  public List<MyUser> listUsers() {
    return userDAO.get();
  }

  @Override
  public boolean clearAllUsers() {
    return 0 == userDAO.remove();
  }
}
