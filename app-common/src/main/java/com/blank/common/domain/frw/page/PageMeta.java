package com.blank.common.domain.frw.page;

import java.util.HashSet;
import java.util.Set;

public class PageMeta {
	private String template;
	private Set<PageFieldMeta> basicFields = new HashSet<PageFieldMeta>();
	private Set<SubPageMeta> subItems = new HashSet<SubPageMeta>();

	public String getTemplate() {
		return template;
	}

	public void setTemplate(String template) {
		this.template = template;
	}

	public Set<PageFieldMeta> getBasicFields() {
		return basicFields;
	}

	public void setBasicFields(Set<PageFieldMeta> basicFields) {
		this.basicFields = basicFields;
	}

	public Set<SubPageMeta> getSubItems() {
		return subItems;
	}

	public void setSubItems(Set<SubPageMeta> subItems) {
		this.subItems = subItems;
	}

	@Override
	public String toString() {
		return "PageMeta [template=" + template + ", basicFields=" + basicFields + ", subItems=" + subItems + "]";
	}

}
