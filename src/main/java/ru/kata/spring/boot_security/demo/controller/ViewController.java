package ru.kata.spring.boot_security.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ViewController {

    @RequestMapping(value = "admin/main")
    public ModelAndView adminPage(){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("/admin/main");
        return modelAndView;
    }

    @RequestMapping(value = "user/page")
    public ModelAndView userPage(){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("/user");
        return modelAndView;
    }

}
