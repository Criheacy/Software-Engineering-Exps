package com.criheacy.exp2.dao;

import org.springframework.stereotype.Repository;

@Repository
public class MyDAOImpl implements MyDAO {

  @Override
  public String getUsername() {
    return "Criheacy";
  }
}
