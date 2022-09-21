package com.criheacy.exp4.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Slf4j
@RestController
public class WebController {

  @GetMapping("/")
  public ModelAndView home() {
    ModelAndView mav = new ModelAndView("home");
    return mav;
  }

  @GetMapping("login")
  public ModelAndView login() {
    ModelAndView mav = new ModelAndView("login");
    return mav;
  }

  @PostMapping(value = "login", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
  public ModelAndView loginCheck(@RequestParam String username, @RequestParam String password) {
    log.info("Logging with credentials: username={} password={}", username, password);
    if ("Criheacy".equals(username) && "123456".equals(password)) {
      return new ModelAndView("redirect:authorized?username=" + username);
    } else {
      return new ModelAndView("redirect:login");
    }
  }

  @GetMapping("authorized")
  public ModelAndView authorized(@RequestParam String username) {
    ModelAndView mav = new ModelAndView("authorized");
    mav.addObject("username", username);
    return mav;
  }
}
