package com.cave.spring.mvc.web.controller;

import java.security.Principal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cave.spring.mvc.web.dao.Message;
import com.cave.spring.mvc.web.dao.User;
import com.cave.spring.mvc.web.service.UsersService;

@Controller
public class LoginController {

	private UsersService usersService;

	@Autowired
	public void setUsersService(UsersService userService) {
		this.usersService = userService;
	}

	@RequestMapping("/login")
	public String showLogin() {
		return "login";
	}

	@RequestMapping("/denied")
	public String showDenied() {
		return "denied";
	}

	@RequestMapping("/messages")
	public String showMessages() {
		return "messages";
	}

	@RequestMapping("/admin")
	public String showAdmin(Model model) {
		List<User> users = usersService.getAllUsers();
		model.addAttribute("users", users);

		return "admin";
	}

	@RequestMapping("/loggedout")
	public String showLoggedOut() {
		return "loggedout";
	}

	@RequestMapping("/newaccount")
	public String showNewAccount(Model model) {
		model.addAttribute("user", new User());

		return "newaccount";
	}

	@RequestMapping(value = "/createaccount", method = RequestMethod.POST)
	public String createAccount(@Valid User user, BindingResult result) {

		if (result.hasErrors()) {
			return "newaccount";
		}

		user.setAuthority("ROLE_USER");
		user.setEnabled(true);

		if (usersService.exists(user.getName())) {
			result.rejectValue("name", "DuplicateKey.user.name");
			return "newaccount";
		}

		try {
			usersService.createUser(user);
		} catch (DuplicateKeyException de) {
			result.rejectValue("name", "DuplicateKey.user.name");
			return "newaccount";
		}

		return "accountcreated";
	}

	@RequestMapping(value = "/getmessages", method = RequestMethod.GET, produces = "application/json")
	@ResponseBody
	public Map<String, Object> getMessages(Principal principal) {
		List<Message> messages = null;

		if (principal == null) {
			messages = new ArrayList<>();
		} else {
			String name = principal.getName();
			messages = usersService.getMessages(name);
		}

		Map<String, Object> data = new HashMap<>();
		data.put("messages", messages);
		data.put("number", messages.size());

		return data;
	}

	@RequestMapping(value = "/sendmessage", method = RequestMethod.POST, produces = "application/json")
	@ResponseBody
	public Map<String, Object> sendMessage(Principal principal,
			@RequestBody Map<String, Object> data) {
		String text = (String) data.get("text");
		String name = (String) data.get("name");
		String email = (String) data.get("email");
		Integer target = (Integer) data.get("target");

		System.out.println(name + email + text);

		Map<String, Object> rval = new HashMap<>();
		rval.put("success", true);
		rval.put("target", target);

		return rval;
	}
}
