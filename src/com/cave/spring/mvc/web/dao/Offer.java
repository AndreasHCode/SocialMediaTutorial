package com.cave.spring.mvc.web.dao;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.Size;

@Entity
@Table(name = "offer")
public class Offer implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int offerId;

	@Size(min = 5, message = "Text must have 5 characters")
	private String text;

	@ManyToOne
	@JoinColumn(name = "userId")
	private User user;

	public Offer() {

	}

	public Offer(String text) {
		this.text = text;
	}

	public Offer(int offerId, User user, String text) {
		this.offerId = offerId;
		this.user = user;
		this.text = text;
	}

	public int getOfferId() {
		return offerId;
	}

	public void setOfferId(int offerId) {
		this.offerId = offerId;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public int getUserId() {
		return user.getUserId();
	}

	@Override
	public String toString() {
		return "Offer [id=" + offerId + ", text=" + text + ", user=" + user
				+ "]";
	}

}
