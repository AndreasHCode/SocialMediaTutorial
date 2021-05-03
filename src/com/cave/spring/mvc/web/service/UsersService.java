package com.cave.spring.mvc.web.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Service;

import com.cave.spring.mvc.web.dao.Message;
import com.cave.spring.mvc.web.dao.MessagesDAO;
import com.cave.spring.mvc.web.dao.User;
import com.cave.spring.mvc.web.dao.UsersDAO;

@Service
public class UsersService {

	@Autowired
	private UsersDAO usersDAO;

	@Autowired
	private MessagesDAO messagesDAO;

	public void createUser(User user) {
		usersDAO.create(user);
	}

	public boolean exists(String username) {
		return usersDAO.exists(username);
	}

	@Secured("ROLE_ADMIN")
	public List<User> getAllUsers() {
		return usersDAO.getAllUsers();
	}

	public void sendMessage(Message message) {
		messagesDAO.create(message);
	}

	public User getUser(String name) {
		return usersDAO.getUser(name);
	}

	public List<Message> getMessages(String name) {
		User user = usersDAO.getUser(name);

		return messagesDAO.getMessages(user);
	}

}
