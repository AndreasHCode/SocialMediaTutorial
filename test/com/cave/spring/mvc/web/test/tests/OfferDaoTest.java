package com.cave.spring.mvc.web.test.tests;

import static org.junit.Assert.assertEquals;

import javax.sql.DataSource;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.cave.spring.mvc.web.dao.Offer;
import com.cave.spring.mvc.web.dao.OffersDAO;
import com.cave.spring.mvc.web.dao.User;
import com.cave.spring.mvc.web.dao.UsersDAO;

@ActiveProfiles("dev")
@ContextConfiguration(locations = {
		"classpath:com/cave/spring/mvc/web/config/dao-context.xml",
		"classpath:com/cave/spring/mvc/web/config/security-context.xml",
		"classpath:com/cave/spring/mvc/web/test/config/datasource.xml" })
@RunWith(SpringJUnit4ClassRunner.class)
public class OfferDaoTest {

	@Autowired
	private UsersDAO usersDAO;

	@Autowired
	private OffersDAO offersDAO;

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
	public void testCreateOffer() {
		String name1 = "Claus";
		User user1 = new User(name1, "clauspw", "create@mail.com", true,
				"ROLE_USER");
		String name2 = "Dolf";
		User user2 = new User(name2, "dolfpw", "create@mail2.com", false,
				"ROLE_USER");
		String name3 = "Elf";
		User user3 = new User(name3, "Elfpw", "cElf@mail.com", true,
				"ROLE_USER");

		usersDAO.create(user1);
		usersDAO.create(user2);
		usersDAO.create(user3);

		Offer offer1 = new Offer("New offer from claus");
		Offer offer2 = new Offer(
				"Newest offer from claus Much better than the first one");
		Offer offer3 = new Offer("Offer from Dolf weird name, great offer!");
		Offer offer4 = new Offer("Active offer from elf");

		offersDAO.create(offer1, name1);
		offersDAO.create(offer2, name1);
		offersDAO.create(offer3, name2);
		offersDAO.create(offer4, name3);

		offersDAO.getOffers();
		assertEquals("Must be 3", 3, offersDAO.getOffers().size());

		assertEquals("Must be 2", 2, offersDAO.getOffersFromUser(name1).size());
		assertEquals("Must be 0", 0, offersDAO.getOffersFromUser(name2).size());
		assertEquals("Must be 1", 1, offersDAO.getOffersFromUser(name3).size());

		offersDAO.delete(offer2.getOfferId());
		assertEquals("Must be 1 after deletion", 1, offersDAO
				.getOffersFromUser(name1).size());

		offer1.setText("Newer text i hope it works");
		offersDAO.update(offer1, name1);

		offersDAO.delete(offer2.getOfferId());

		offersDAO.create(offer1, name1);
		offersDAO.create(offer2, name1);
		offersDAO.create(offer3, name2);
		offersDAO.create(offer4, name3);

	}
}
