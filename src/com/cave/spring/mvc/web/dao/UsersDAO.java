package com.cave.spring.mvc.web.dao;

import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
@Component
public class UsersDAO {

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	private SessionFactory sessionFactory;

	public Session session() {
		return sessionFactory.getCurrentSession();
	}

	public void create(User user) {
		user.setPassword(passwordEncoder.encode(user.getPassword()));

		session().save(user);
	}

	public boolean exists(String name) {
		CriteriaBuilder builder = session().getCriteriaBuilder();
		CriteriaQuery<User> criteria = builder.createQuery(User.class);
		Root<User> userRoot = criteria.from(User.class);
		criteria.select(userRoot);

		criteria.where(builder.equal(userRoot.get("name"), name));
		List<User> users = session().createQuery(criteria).list();

		return users.size() > 0;
	}

	public List<User> getAllUsers() {
		return session().createQuery("from User", User.class).list();
	}

	public User getUser(String name) {
		CriteriaBuilder builder = session().getCriteriaBuilder();
		CriteriaQuery<User> criteria = builder.createQuery(User.class);
		Root<User> userRoot = criteria.from(User.class);
		criteria.select(userRoot);

		criteria.where(builder.equal(userRoot.get("name"), name));
		List<User> users = session().createQuery(criteria).list();

		if (users.size() > 0) {
			return users.get(0);
		} else {
			return null;
		}
	}
}
