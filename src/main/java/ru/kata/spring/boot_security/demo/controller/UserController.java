package ru.kata.spring.boot_security.demo.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
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




    @GetMapping("/")
    public ResponseEntity<User> getUser(Authentication authentication) {

        return new ResponseEntity<>((User) authentication.getPrincipal(), HttpStatus.OK);
    }


    @PutMapping(path = "/{id}")
    public ResponseEntity<User> editUser(@PathVariable Long id, @RequestBody User editUser){
        userService.editUser(editUser,id);

        return new ResponseEntity<>(editUser,HttpStatus.OK);
    }

}
