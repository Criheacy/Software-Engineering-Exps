package com.criheacy.exp2.service;

import com.criheacy.exp2.dao.MyDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MyServiceImpl implements MyService {

  @Autowired
  MyDAO myDAO;

  @Override
  public String greetingsToUser() {
    return String.format("Greetings, %s!", myDAO.getUsername());
  }
}
