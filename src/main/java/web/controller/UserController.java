package web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;
import web.model.User;
import web.repository.UserRepository;


import javax.persistence.EntityNotFoundException;
import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@Controller
//@RequestMapping("/users")
public class UserController {
    private final UserRepository userRepository;

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
    @GetMapping("/create")
    public String create(Model model) {
        return "add";
    }

   @PostMapping("/add")
    public String addUser(@RequestParam String name, @RequestParam String lastname
            , @RequestParam String email, Model model) {
        User user = new User();
        user.setName(name);
        user.setLastName(lastname);
        user.setEmail(email);
        userRepository.save(user);
        model.addAttribute("users", userRepository.findAll());
        return "new";
    }

    /*@PostMapping("/up/{id}")
    public String upUser(@RequestParam Long id, @RequestParam String name, @RequestParam String lastname
            , @RequestParam String email, Model model) {
        User user = new User();
        user.setId(id);
        user.setName(name);
        user.setLastName(lastname);
        user.setEmail(email);
        userRepository.save(user);
        model.addAttribute("users", userRepository.findAll());
        return "new";
    }*/
    @PostMapping("/up/{id}")
    public String upUser(@Valid @ModelAttribute("user") User user, @PathVariable("id") Long id, Model model) {
        User userToUpdate = userRepository.findById(id).orElseThrow(()
                -> new EntityNotFoundException("User not found with id " + id));
        userToUpdate.setId(id);
        model.addAttribute("name", userToUpdate.getName());
        model.addAttribute("lastname", userToUpdate.getLastName());
        model.addAttribute("email", userToUpdate.getEmail());
        model.addAttribute("users", userRepository.findAll());
        userRepository.save(userToUpdate);
        return "update";
    }

    @GetMapping("/users/{id}/delete")
    public String deleteUser(@PathVariable Long id, Model model) {
        userRepository.deleteById(id);
        List<User> users = userRepository.findAll();
        model.addAttribute("users", users);
        return "new";
    }

}