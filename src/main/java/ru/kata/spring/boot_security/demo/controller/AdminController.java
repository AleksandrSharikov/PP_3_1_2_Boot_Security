package ru.kata.spring.boot_security.demo.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.service.UserService;

@Controller
@RequestMapping(value = "/")
public class AdminController {
    private UserService userService;
    @Autowired
    public AdminController(UserService userService) {
        this.userService = userService;
    }

   @RequestMapping(value = "/admin/main", method = RequestMethod.GET)
   public String mainTable(Model model){

        model.addAttribute("userlist", userService.getUserList());
        return "/admin/main";
    }
    @RequestMapping(value = "/admin/new", method = RequestMethod.GET)
    public String newUser(Model model){
        model.addAttribute("user", new User());

        return "admin/newUserForm";
    }

    @RequestMapping()
    public String saveUser(@ModelAttribute("user") User user) {
        userService.addUser(user);
        return "redirect:/admin/main";
    }


    @RequestMapping(value = "/admin/{id}/edit", method = RequestMethod.GET)
    public String editUser(@PathVariable("id") int id, Model model) {
        model.addAttribute("user", userService.getById(id));
        return "admin/editUserForm";
    }

    @PostMapping("/admin/{id}")
    public String updateUser(@ModelAttribute("user") User user){
        userService.editUser(user, user.getId());
        return "redirect:/admin/main";
    }

    @PostMapping("admin/{id}/delet")
    public String deleteUser(@PathVariable("id") int id) {
        userService.deletUser(id);
        return "redirect:/admin/main";
    }


}
