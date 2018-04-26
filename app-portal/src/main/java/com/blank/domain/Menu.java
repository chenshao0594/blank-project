package com.blank.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "menu")
public class Menu extends BaseEntity implements Serializable {

	private static final long serialVersionUID = 312320260898988300L;
	private Long parentId;
	private String url;
	private String name;
	@Column(name = "menu_order")
	private Integer order;
	private String type;// module：模块 ; page：页面 ; button：功能

	public Long getParentId() {
		return parentId;
	}

	public void setParentId(Long parentId) {
		this.parentId = parentId;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getOrder() {
		return order;
	}

	public void setOrder(int order) {
		this.order = order;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

}
