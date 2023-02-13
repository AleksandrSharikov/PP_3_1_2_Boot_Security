package ru.kata.spring.boot_security.demo.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.model.Role;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.service.RoleService;
import ru.kata.spring.boot_security.demo.service.UserService;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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

   @GetMapping(value = "/main")
   public List<User> allUsers(){

        return userService.getUserList();
    }
    @GetMapping(value = "/new")
    public String newUser(Model model){
        model.addAttribute("user", new User());
        model.addAttribute("abox");
        model.addAttribute("ubox");

        return "admin/newUserForm";
    }

    @PostMapping(path = "/",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public void create(@RequestBody User newUser) {
        System.out.println("create");
        userService.addUser(newUser);
    }


    @GetMapping(value = "/{id}/edit")
    public String editUser(@PathVariable("id") Long id, Model model) {
        User tmpUser = userService.getById(id);
        model.addAttribute("user", tmpUser);
        if(tmpUser.getRoles().contains(new Role(1L,"ROLE_USER"))){
            model.addAttribute("ubox", true);
            }
        if(tmpUser.getRoles().contains(new Role(2L,"ROLE_ADMIN"))){
            model.addAttribute("abox",true);
            }

        return "admin/editUserForm";
    }

    @PostMapping("/{id}")
    public String updateUser(@ModelAttribute("user") User user,
                             @ModelAttribute("abox") String abox,
                             @ModelAttribute("ubox") String ubox) {
        Set<Role> roleSet = new HashSet<>();
        if(abox.equals("on")){
            roleSet.add(new Role(2L,"ROLE_ADMIN"));
            }
        if(ubox.equals("on")){
            roleSet.add(new Role(1L,"ROLE_USER"));
            }
        System.out.println(roleSet);
        user.setRoles(roleSet);
        userService.editUser(user, user.getId());
        return "redirect:/admin/main";
    }

    @PostMapping("/{id}/delet")
    public String deleteUser(@PathVariable("id") Long id) {
        userService.deletUser(id);
        return "redirect:/admin/main";
    }


}
