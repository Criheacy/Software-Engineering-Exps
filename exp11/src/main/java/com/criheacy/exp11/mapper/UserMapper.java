package com.criheacy.exp11.mapper;

import com.criheacy.exp11.entity.UserDO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UserMapper {
  int createUser(UserDO user);

  UserDO getAccountByUsername(String name);

  List<UserDO> listAllUsers();
}
