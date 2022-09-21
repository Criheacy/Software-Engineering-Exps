package com.criheacy.exp2.controller;

import com.criheacy.exp2.service.MyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MyController {
  @Autowired
  MyService myService;

  @GetMapping("/")
  public String greetings() {
    return myService.greetingsToUser();
  }
}
