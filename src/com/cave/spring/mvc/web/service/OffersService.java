package com.cave.spring.mvc.web.service;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Service;

import com.cave.spring.mvc.web.dao.Offer;
import com.cave.spring.mvc.web.dao.OffersDAO;

@Service
public class OffersService {

	private OffersDAO offersDAO;

	@Autowired
	public void setOffersDAO(OffersDAO offersDAO) {
		this.offersDAO = offersDAO;
	}

	public List<Offer> getCurrent() {
		List<Offer> offers = offersDAO.getOffers();

		for (Offer anOffer : offers) {
			if (anOffer.getText().length() > 40) {
				anOffer.setText(anOffer.getText().substring(0, 40) + "...");
			}
		}

		return offers;
	}

	@Secured({ "ROLE_USER", "ROLE_ADMIN" })
	public void createOffer(@Valid Offer offer, String user) {
		offersDAO.create(offer, user);
	}

	public boolean hasOffer(String name) {
		if (name == null) {
			return false;
		}

		List<Offer> offers = offersDAO.getOffersFromUser(name);

		if (offers.size() == 0) {
			return false;
		}

		return true;
	}

	public Offer getOffer(String name) {
		if (name == null) {
			return null;
		}

		List<Offer> offers = offersDAO.getOffersFromUser(name);

		if (offers.size() == 0) {
			return null;
		}

		return offers.get(0);
	}

	public void saveOrUpdateOffer(@Valid Offer offer, String name) {
		if (offer.getOfferId() != 0) {
			offersDAO.update(offer, name);
		} else {
			offersDAO.create(offer, name);
		}
	}

	public void delete(int id) {
		offersDAO.delete(id);
	}
}
