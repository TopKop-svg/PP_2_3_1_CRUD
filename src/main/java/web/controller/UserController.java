package web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import web.model.User;
import web.repository.UserRepository;

import java.util.List;

@Controller
public class UserController {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping("/")
    public String userTable(Model model) {
        List<User> users = userRepository.findAll();
        model.addAttribute("users", users);
        return "new";
    }

    /*@GetMapping("/adduser")
    public String addUserPage(Model model) {
        return "adduser";
    }*/

    @PostMapping("adduser")
    public String addUser(@RequestParam String name, @RequestParam String lastname, @RequestParam String email) {
        User user = new User();
        user.setName(name);
        user.setLastName(lastname);
        user.setMail(email);
        userRepository.save(user);
        return "adduser";
    }

    @PostMapping("/users/{id}/delete")
    public String deleteUser(@PathVariable Long id) {
        userRepository.deleteById(id);
        return "new";
    }
/*
    @PostMapping("/users/{id}/update")
    public String updateUser(@PathVariable Long id, @RequestParam String name, @RequestParam String lastname, @RequestParam String email) {
        User user = userRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid user Id:" + id));
        user.setName(name);
        user.setLastName(lastname);
        user.setMail(email);
        userRepository.save(user);
        return "redirect:/index";
    }*/
}