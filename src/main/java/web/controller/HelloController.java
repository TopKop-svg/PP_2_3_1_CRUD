package web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import web.model.User;

import java.util.ArrayList;
import java.util.List;

//@Controller
public class HelloController {

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