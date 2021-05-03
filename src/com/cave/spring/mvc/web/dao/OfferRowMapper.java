package com.cave.spring.mvc.web.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

public class OfferRowMapper implements RowMapper<Offer> {

	@Override
	public Offer mapRow(ResultSet rs, int rowNum) throws SQLException {
		Offer offer = new Offer();
		User user = new User();

		user.setUserId(rs.getInt("userId"));
		user.setName(rs.getString("name"));
		user.setEmail(rs.getString("email"));
		user.setEnabled(true);
		user.setAuthority(rs.getString("authority"));

		offer.setOfferId(rs.getInt("offerId"));
		offer.setText(rs.getString("text"));
		offer.setUser(user);

		return offer;
	}

}
