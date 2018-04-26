package com.blank.customer.domain;

import java.io.Serializable;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.blank.common.domain.AddressField;
import com.blank.common.domain.ImageField;
import com.blank.domain.BaseEntity;

@Entity
@Table(name = "customer")
public class Customer extends BaseEntity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3905084491326592179L;
	private String name;
	private String phone;
	@Embedded
	private AddressField addressField;
	@Embedded
	private ImageField avatar;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public AddressField getAddressField() {
		return addressField;
	}

	public void setAddressField(AddressField addressField) {
		this.addressField = addressField;
	}

	public ImageField getAvatar() {
		return avatar;
	}

	public void setAvatar(ImageField avatar) {
		this.avatar = avatar;
	}

}
