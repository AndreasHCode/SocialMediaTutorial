package com.cave.spring.mvc.web.dao;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Entity
@Table(name = "message")
public class Message implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int messageId;

	@Size(min = 5)
	private String subject;

	@Size(min = 10)
	private String content;

	@Size(min = 3, max = 15)
	@Pattern(regexp = "^\\w{3,}$")
	private String name;

	@Email
	private String email;
	private int userId;

	public Message() {

	}

	public Message(String subject, String content, String name, String email,
			int userId) {
		this.subject = subject;
		this.content = content;
		this.name = name;
		this.email = email;
		this.userId = userId;
	}

	public int getMessageId() {
		return messageId;
	}

	public void setMessageId(int messageId) {
		this.messageId = messageId;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	@Override
	public String toString() {
		return "Message [messageId=" + messageId + ", subject=" + subject
				+ ", content=" + content + ", name=" + name + ", email="
				+ email + ", userId=" + userId + "]";
	}

}
