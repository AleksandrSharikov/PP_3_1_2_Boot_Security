package ru.kata.spring.boot_security.demo.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.service.UserService;

@RestController
@RequestMapping(value = "/user")
public class UserController {

    private UserService userService;
    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }


    @RequestMapping(value = "/page")
    public ModelAndView mainPage(){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("/user");
        return modelAndView;
    }

    @GetMapping("/")
    public User getUser(Authentication authentication) {
        System.out.println(userService.getById(((User) authentication.getPrincipal()).getId()));
        System.out.println("get user 2");
        System.out.println(userService.getById(2L));
        return userService.getById(((User) authentication.getPrincipal()).getId());
    }


    @PutMapping(path = "/{id}",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE    )
    public User editUser(@PathVariable Long id, @RequestBody User editUser){
        userService.editUser(editUser,id);
        System.out.println("Edit");
        System.out.println(userService.getById(2L));
        return editUser;
    }

}
