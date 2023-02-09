package ru.kata.spring.boot_security.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping(value = "/")
public class LoginController {


   // public String rd() {        return "redirect:/login";    }
  //  @GetMapping(value = "/")
    @GetMapping(value = {"/login","/"})
    public String login() {
        System.out.println("login");
        return "login";}

}
