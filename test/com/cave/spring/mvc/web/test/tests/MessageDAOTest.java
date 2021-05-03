package com.cave.spring.mvc.web.test.tests;

import static org.junit.Assert.assertEquals;

import java.util.List;

import javax.sql.DataSource;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.cave.spring.mvc.web.dao.Message;
import com.cave.spring.mvc.web.dao.MessagesDAO;
import com.cave.spring.mvc.web.dao.User;
import com.cave.spring.mvc.web.dao.UsersDAO;

@ActiveProfiles("dev")
@ContextConfiguration(locations = {
		"classpath:com/cave/spring/mvc/web/config/dao-context.xml",
		"classpath:com/cave/spring/mvc/web/config/security-context.xml",
		"classpath:com/cave/spring/mvc/web/test/config/datasource.xml" })
@RunWith(SpringJUnit4ClassRunner.class)
public class MessageDAOTest {

	@Autowired
	private UsersDAO usersDAO;

	@Autowired
	private MessagesDAO messageDAO;

	@Autowired
	private DataSource dataSource;

	@Before
	public void init() {
		JdbcTemplate jdbc = new JdbcTemplate(dataSource);

		jdbc.execute("delete from offer");
		jdbc.execute("delete from message");
		jdbc.execute("delete from user");
	}

	@Test
	public void testCreateUser() {
		User user = new User("Claus", "claus", "create@mail.com", true,
				"ROLE_USER");
		usersDAO.create(user);

		Message message = new Message("You suck", " so hard, ", " It's hard",
				"to stay", user.getUserId());

		messageDAO.create(message);
		messageDAO.create(message);
		List<Message> messages = messageDAO.getMessages();

		assertEquals("Should have two entries", 2, messages.size());
	}
}
