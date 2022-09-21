package com.criheacy.exp3.dao;

import com.criheacy.exp3.entity.MyUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UserDAOImpl implements UserDAO {

  public static final String getTemplate = "SELECT * FROM user";
  public static final String saveTemplate = "INSERT INTO user VALUES (?,?,?)";
  public static final String removeTemplate = "DELETE FROM user";

  @Autowired
  JdbcTemplate jdbcTemplate;

  @Override
  public List<MyUser> get() {
    return jdbcTemplate.query(getTemplate, (result, rowNum) ->
      new MyUser(
        result.getInt("id"),
        result.getString("username"),
        result.getString("gender")
      )
    );
  }

  @Override
  public int save(MyUser user) {
    return jdbcTemplate.update(saveTemplate, user.getId(), user.getUsername(), user.getGender());
  }

  @Override
  public int remove() {
    return jdbcTemplate.update(removeTemplate);
  }
}
