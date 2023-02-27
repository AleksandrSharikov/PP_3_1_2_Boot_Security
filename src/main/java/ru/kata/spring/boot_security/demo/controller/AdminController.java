package ru.kata.spring.boot_security.demo.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.service.RoleService;
import ru.kata.spring.boot_security.demo.service.UserService;

import java.util.List;

@RestController
@RequestMapping(value = "/admin")
public class AdminController {
    private UserService userService;
    private RoleService roleService;
    @Autowired
    public AdminController(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

   @RequestMapping(value = "/main")
   public ModelAndView mainPage(){
       ModelAndView modelAndView = new ModelAndView();
       modelAndView.setViewName("/admin/main");
       return modelAndView;
    }



    @GetMapping("/")
    public List<User> getList() {
        return userService.getUserList();
    }



    @PostMapping(path = "/",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE    )
    public User create(@RequestBody User newUser ) {
        userService.addUser(newUser);
        return newUser;
    }

    @DeleteMapping(path = "/{id}")
    public String deletUser(@PathVariable Long id) {
        userService.deletUser(id);
        return "Deleted";
    }
    @PutMapping(path = "/{id}",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE    )
    public User editUser(@PathVariable Long id, @RequestBody User editUser){
        userService.editUser(editUser,id);
        return editUser;
    }


}
