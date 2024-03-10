package web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import web.dao.UserDao;
import web.model.User;
import web.repository.UserRepository;

//@Controller
//@RequestMapping("/usr")
public class OtherController {
    /*@Autowired
    private UserRepository userRepository;
    @Autowired
    public OtherController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @PostMapping()
    public String create(@RequestParam("name") String name, @RequestParam("lastname") String lastname
            ,@RequestParam("email") String email, Model model) {
        User user = new User();
        user.setName(name);
        user.setLastName(lastname);
        user.setMail(email);
        model.addAttribute("user", user);
        return "successPage";
    }*/
    private final UserDao userDao;
    //@Autowired
    public OtherController(UserDao userDao) {
        this.userDao = userDao;
    }

    //@GetMapping()
    public String index(Model model) {
        model.addAttribute("users", userDao.listUsers());
        return "users/index";
    }
    //@GetMapping("/{id}")
    public String show(@PathVariable("id") int id, Model model) {
        model.addAttribute("user", userDao.getUser(id));
        return "people/show";
    }
}
