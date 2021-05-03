package com.cave.spring.mvc.web.test.tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

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

import com.cave.spring.mvc.web.dao.User;
import com.cave.spring.mvc.web.dao.UsersDAO;

@ActiveProfiles("dev")
@ContextConfiguration(locations = {
		"classpath:com/cave/spring/mvc/web/config/dao-context.xml",
		"classpath:com/cave/spring/mvc/web/config/security-context.xml",
		"classpath:com/cave/spring/mvc/web/test/config/datasource.xml" })
@RunWith(SpringJUnit4ClassRunner.class)
public class UserDaoTest {

	@Autowired
	private UsersDAO usersDAO;

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

		List<User> users = usersDAO.getAllUsers();
		assertEquals("Number should be 1", 1, users.size());

		assertTrue("User should exist", usersDAO.exists(user.getName()));

		assertEquals("Created User should be identical to retrieved", user,
				users.get(0));

	}

}
