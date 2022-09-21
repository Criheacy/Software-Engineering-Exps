package com.criheacy.exp2;

import com.criheacy.exp2.service.MyService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class Exp2ApplicationTests {

  @Autowired
  MyService myService;

  @Test
  void contextLoads() {
    assert (myService.greetingsToUser().equals("Greetings, Criheacy!"));
  }

}
