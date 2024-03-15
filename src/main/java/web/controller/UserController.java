package web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;
import web.model.User;
import web.service.UserService;
import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import javax.validation.Valid;
import java.util.List;

@Controller
public class UserController {

    private final UserService userService;
    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/")
    public String userTable(Model model) {
        List<User> users = userService.index();
        model.addAttribute("users", users);
        return "new";
    }

    @GetMapping("/create")
    public String create() {
        return "add";
    }


   @PostMapping("/add")
    public String addUser(@RequestParam String name, @RequestParam String lastname
            , @RequestParam String email, Model model) {
        User user = new User();
        user.setName(name);
        user.setLastName(lastname);
        user.setEmail(email);
        userService.save(user);
        model.addAttribute("users", userService.index());
        return "new";
    }

    @GetMapping("{id}/update")
    public String upUser(@ModelAttribute("user") @Valid User userToUpdate, @PathVariable("id") Long id, Model model) {
        User user = userService.show(id);
        model.addAttribute("name", user.getName());
        model.addAttribute("lastname", user.getLastName());
        model.addAttribute("email", user.getEmail());
        return "/update";
    }

    @PostMapping("/{id}")
    public String edit(@PathVariable("id") Long id, @ModelAttribute("name") String name,
                       @ModelAttribute("lastname") String lastname,@ModelAttribute("email") @Valid String email,
                       BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "/" + id;
        }
        User user = userService.show(id);
        user.setId(id);
        user.setName(name);
        user.setLastName(lastname);
        user.setEmail(email);
        userService.update(id, user);
        return "redirect:/";
    }

    @GetMapping("/users/{id}/delete")
    public String deleteUser(@PathVariable Long id, Model model) {
        userService.delete(id);
        List<User> users = userService.index();
        model.addAttribute("users", users);
        return "new";
    }

}