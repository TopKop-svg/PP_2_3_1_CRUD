package web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import web.model.User;
import web.repository.UserRepository;

import java.util.ArrayList;
import java.util.List;

//@Controller
public class HelloController {

    /*private final UserRepository userRepository;

    @Autowired
    public HelloController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping("/")
    public String index(Model model) {
        List<User> users = userRepository.findAll();
        model.addAttribute("users", users);
        return "index";
    }

    @GetMapping("/create")
    public String createUser( Model model) {
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
        return "redirect:/users";
    }

    @PostMapping("/up")
    public String upUser(@ModelAttribute User user, Model model) {
        userRepository.save(user);
        return "redirect:/users";
    }*/


	/*@RequestMapping(value = "/get", method = RequestMethod.GET)
	@ResponseBody
	public String getUser(ModelMap model) {
		return "createMockUser()";
	}

	private User createMockUser() {
		User user = new User();
		user.setId(Long.valueOf(1));
		user.setName("Alex");
		user.setMail("loookintome@gmail.com");
		return user;
	}
*/}