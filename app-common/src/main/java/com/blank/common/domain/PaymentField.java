package com.blank.common.domain;

import javax.persistence.Embeddable;

@Embeddable
public class PaymentField {
	private String type;
	private String cardName;
	private String cardNumber;
	private String Expiration;
	private String CVV;

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getCardName() {
		return cardName;
	}

	public void setCardName(String cardName) {
		this.cardName = cardName;
	}

	public String getCardNumber() {
		return cardNumber;
	}

	public void setCardNumber(String cardNumber) {
		this.cardNumber = cardNumber;
	}

	public String getExpiration() {
		return Expiration;
	}

	public void setExpiration(String expiration) {
		Expiration = expiration;
	}

	public String getCVV() {
		return CVV;
	}

	public void setCVV(String cVV) {
		CVV = cVV;
	}

}
