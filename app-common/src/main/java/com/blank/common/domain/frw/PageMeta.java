package com.blank.common.domain.frw;

import java.util.LinkedList;
import java.util.List;

public class PageMeta {
	private String template;
	private List<FieldMeta> basicFields = new LinkedList<FieldMeta>();
	private List<SubItemMeta> subItems = new LinkedList<SubItemMeta>();

	public String getTemplate() {
		return template;
	}

	public void setTemplate(String template) {
		this.template = template;
	}

	public List<FieldMeta> getBasicFields() {
		return basicFields;
	}

	public void setBasicFields(List<FieldMeta> basicFields) {
		this.basicFields = basicFields;
	}

	public List<SubItemMeta> getSubItems() {
		return subItems;
	}

	public void setSubItems(List<SubItemMeta> subItems) {
		this.subItems = subItems;
	}

	@Override
	public String toString() {
		return "PageMeta [template=" + template + ", basicFields=" + basicFields + ", subItems=" + subItems + "]";
	}

}
