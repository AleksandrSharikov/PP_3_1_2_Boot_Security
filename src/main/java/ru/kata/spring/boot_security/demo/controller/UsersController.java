package ru.kata.spring.boot_security.demo.controller;

import com.springboot.model.User;
import com.springboot.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping(value = "/")
public class UsersController {
    private UserService userService;
    @Autowired
    public UsersController(UserService userService) {
        this.userService = userService;
    }

   @RequestMapping(value = "/", method = RequestMethod.GET)
   public String mainTable(Model model){

        model.addAttribute("userlist", userService.getUserList());
        return "index";
    }
    @RequestMapping(value = "/new", method = RequestMethod.GET)
    public String newUser(Model model){
        model.addAttribute("user", new User());

        return "newUserForm";
    }

    @RequestMapping()
    public String saveUser(@ModelAttribute("user") User user) {
        userService.addUser(user);
        return "redirect:/";
    }


    @RequestMapping(value = "{id}/edit", method = RequestMethod.GET)
    public String editUser(@PathVariable("id") int id, Model model) {
        model.addAttribute("user", userService.getById(id));
        return "editUserForm";
    }

    @PostMapping("/{id}")
    public String updateUser(@ModelAttribute("user") User user){
        userService.editUser(user, user.getId());
        return "redirect:/";
    }

    @PostMapping("{id}/delet")
    public String deleteUser(@PathVariable("id") int id) {
        userService.deletUser(id);
        return "redirect:/";
    }


}
