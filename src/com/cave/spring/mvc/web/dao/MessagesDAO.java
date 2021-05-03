package com.cave.spring.mvc.web.dao;

import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@Transactional
public class MessagesDAO {

	@Autowired
	private SessionFactory sessionFactory;

	public Session session() {
		return sessionFactory.getCurrentSession();
	}

	public List<Message> getMessages() {
		CriteriaBuilder builder = session().getCriteriaBuilder();
		CriteriaQuery<Message> criteria = builder.createQuery(Message.class);
		Root<Message> messageRoot = criteria.from(Message.class);
		criteria.select(messageRoot);

		return session().createQuery(criteria).list();
	}

	public void create(Message message) {
		session().save(message);
	}

	public List<Message> getMessages(User user) {
		CriteriaBuilder builder = session().getCriteriaBuilder();
		CriteriaQuery<Message> criteria = builder.createQuery(Message.class);
		Root<Message> messageRoot = criteria.from(Message.class);
		criteria.select(messageRoot);

		criteria.where(builder.equal(messageRoot.get("userId"),
				user.getUserId()));
		List<Message> messages = session().createQuery(criteria).list();

		return messages;
	}
}
