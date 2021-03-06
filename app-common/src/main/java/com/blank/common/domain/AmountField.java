package com.blank.common.domain;

import java.math.BigDecimal;
import java.util.Currency;

import javax.persistence.Embeddable;

@Embeddable
public class AmountField {
	private BigDecimal amount;
	private Currency currency;

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	public Currency getCurrency() {
		return currency;
	}

	public void setCurrency(Currency currency) {
		this.currency = currency;
	}

}
