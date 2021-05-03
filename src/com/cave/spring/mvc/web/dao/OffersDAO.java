package com.cave.spring.mvc.web.dao;

import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Root;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@Transactional
public class OffersDAO {

	@Autowired
	private SessionFactory sessionFactory;

	public Session session() {
		return sessionFactory.getCurrentSession();
	}

	public List<Offer> getOffers() {
		CriteriaBuilder builder = session().getCriteriaBuilder();
		CriteriaQuery<Offer> criteria = builder.createQuery(Offer.class);
		Root<Offer> offerRoot = criteria.from(Offer.class);
		Join<Offer, User> user = offerRoot.join("user", JoinType.INNER);
		criteria.select(offerRoot);
		criteria.where(builder.equal(user.get("enabled"), true));
		List<Offer> offers = session().createQuery(criteria).list();

		return offers;
	}

	public List<Offer> getOffersFromUser(String name) {
		CriteriaBuilder builder = session().getCriteriaBuilder();
		CriteriaQuery<Offer> criteria = builder.createQuery(Offer.class);
		Root<Offer> offerRoot = criteria.from(Offer.class);
		Join<Offer, User> user = offerRoot.join("user", JoinType.INNER);
		criteria.select(offerRoot);
		criteria.where(builder.equal(user.get("enabled"), true),
				builder.equal(user.get("name"), name));
		List<Offer> offers = session().createQuery(criteria).list();

		return offers;
	}

	public void create(Offer offer, String name) {
		CriteriaBuilder builder = session().getCriteriaBuilder();
		CriteriaQuery<User> criteria = builder.createQuery(User.class);
		Root<User> userRoot = criteria.from(User.class);
		criteria.select(userRoot);

		criteria.where(builder.equal(userRoot.get("name"), name));
		User user = session().createQuery(criteria).getSingleResult();
		offer.setUser(user);

		session().save(offer);
	}

	public void update(Offer offer, String name) {
		CriteriaBuilder builder = session().getCriteriaBuilder();
		CriteriaQuery<User> criteria = builder.createQuery(User.class);
		Root<User> userRoot = criteria.from(User.class);
		criteria.select(userRoot);

		criteria.where(builder.equal(userRoot.get("name"), name));
		User user = session().createQuery(criteria).getSingleResult();
		offer.setUser(user);

		session().update(offer);
	}

	public void delete(int offerId) {
		Query<?> query = session().createQuery(
				"delete from Offer where offerId=:offerId");
		query.setParameter("offerId", offerId);
		query.executeUpdate();
	}

	public Offer getOffer(int offerId) {
		CriteriaBuilder builder = session().getCriteriaBuilder();
		CriteriaQuery<Offer> criteria = builder.createQuery(Offer.class);
		Root<Offer> userOffer = criteria.from(Offer.class);
		criteria.select(userOffer);

		criteria.where(builder.equal(userOffer.get("offerId"), offerId));
		Offer offer = session().createQuery(criteria).getSingleResult();

		return offer;
	}
}
