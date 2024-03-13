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
//@RequestMapping("/new")
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

    /*@GetMapping("/up")
    public String createUpdate(Model model) {
        return "update";
    }*/

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
    @GetMapping("{id}/update")
    public String upUser(@ModelAttribute("user") @Valid User userToUpdate, @PathVariable("id") Long id, Model model) {
        User user = userRepository.findById(id).orElseThrow(()
                -> new EntityNotFoundException("User not found with id " + id));
        userToUpdate.setId(id);
        model.addAttribute("name", user.getName());
        model.addAttribute("lastname", user.getLastName());
        model.addAttribute("email", user.getEmail());
        //model.addAttribute("users", userRepository.findAll());
        //userRepository.save(userToUpdate);
        return "/update";
    }

    @PostMapping("/{id}")
    public String edit(@PathVariable("id") Long id, @ModelAttribute("name") String name,
                       @ModelAttribute("lastname") String lastname, @ModelAttribute("email") String email) {
        User newUser = userRepository.findById(id).orElseThrow(()
                -> new EntityNotFoundException("User not found with id " + id));
        newUser.setId(id);
        newUser.setName(name);
        newUser.setLastName(lastname);
        newUser.setEmail(email);
        userRepository.save(newUser);
        return "redirect:/";
    }

    @GetMapping("/users/{id}/delete")
    public String deleteUser(@PathVariable Long id, Model model) {
        userRepository.deleteById(id);
        List<User> users = userRepository.findAll();
        model.addAttribute("users", users);
        return "new";
    }

}