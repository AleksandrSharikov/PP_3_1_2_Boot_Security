package ru.kata.spring.boot_security.demo.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.model.Role;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.service.RoleService;
import ru.kata.spring.boot_security.demo.service.UserService;

import java.util.HashSet;
import java.util.Set;

//@RestController
@Controller
@RequestMapping(value = "/admin")
public class AdminController {
    private UserService userService;
    @Autowired
    public AdminController(UserService userService) {
        this.userService = userService;
    }

   @GetMapping(value = "/main")
   public String mainTable(Model model){

        model.addAttribute("userlist", userService.getUserList());
        return "/admin/main";
    }
    @GetMapping(value = "/new")
    public String newUser(Model model){
        model.addAttribute("user", new User());
        model.addAttribute("abox");
        model.addAttribute("ubox");

        return "admin/newUserForm";
    }

    @PostMapping("/")
    public String saveUser(@ModelAttribute("user") User user,
                @ModelAttribute("abox") String abox,
                @ModelAttribute("ubox") String ubox)  {
        Set<Role> roleSet = new HashSet<>();
        if(abox.equals("on")) {
            roleSet.add(new Role(2L,"ROLE_ADMIN"));
        }
        if(ubox.equals("on")) {
            roleSet.add(new Role(1L,"ROLE_USER"));
        }
        user.setRoles(roleSet);
        userService.addUser(user);
        return "redirect:/admin/main";
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
