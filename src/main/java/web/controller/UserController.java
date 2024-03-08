package web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import web.model.User;
import web.repository.UserRepository;

import java.util.List;

@Controller
public class UserController {
    @Autowired
    private UserRepository userRepository;

    @GetMapping("/users")
    public String getUsers(Model model) {
        List<User> users = userRepository.findAll();
        model.addAttribute("users", users);
        return "users";
    }

    @PostMapping("/users")
    public String addUser(@RequestParam String name, @RequestParam String lastname, @RequestParam String email) {
        User user = new User();
        user.setName(name);
        user.setLastName(lastname);
        user.setMail(email);
        userRepository.save(user);
        return "redirect:/users";
    }

    @PostMapping("/users/{id}/delete")
    public String deleteUser(@PathVariable Long id) {
        userRepository.deleteById(id);
        return "redirect:/users";
    }

    @PostMapping("/users/{id}/update")
    public String updateUser(@PathVariable Long id, @RequestParam String name, @RequestParam String lastname, @RequestParam String email) {
        User user = userRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid user Id:" + id));
        user.setName(name);
        user.setLastName(lastname);
        user.setMail(email);
        userRepository.save(user);
        return "redirect:/users";
    }
}