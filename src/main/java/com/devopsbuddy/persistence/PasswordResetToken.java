package com.devopsbuddy.persistence;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Entity
public class PasswordResetToken implements Serializable {

	/**
	 * 
	 */

	private static final Logger log = LoggerFactory.getLogger(PasswordResetToken.class);

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;

	@Column(unique = true)
	private String token;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "user_id")
	private User user;

	public PasswordResetToken() {
		super();
	}

	@Convert(converter = LocalDataTImeAttributeConverter.class)
	@Column(name = "expiry_date")
	private LocalDateTime expiryDate;

	public PasswordResetToken(String token, User user, LocalDateTime creationDate, int expirationMinute) {

		if ((token == null) || (user == null) || (creationDate == null)) {
			throw new IllegalArgumentException("illeagal arguments in password reset token");
		}
		if (expirationMinute == 0) {
			log.warn("The token expiration length is 0");
			expirationMinute = 120;
		}

		this.token = token;

		this.user = user;
		expiryDate = creationDate.plusMinutes(expirationMinute);

	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public LocalDateTime getExpiryDate() {
		return expiryDate;
	}

	public void setExpiryDate(LocalDateTime expiryDate) {
		this.expiryDate = expiryDate;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (id ^ (id >>> 32));
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		PasswordResetToken other = (PasswordResetToken) obj;
		if (id != other.id)
			return false;
		return true;
	}

}
