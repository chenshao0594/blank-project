package com.blank.common.domain.frw.page;

import java.util.LinkedList;
import java.util.List;

public class PageMeta {
	private String template;
	private List<PageFieldMeta> basicFields = new LinkedList<PageFieldMeta>();
	private List<SubPageMeta> subItems = new LinkedList<SubPageMeta>();

	public String getTemplate() {
		return template;
	}

	public void setTemplate(String template) {
		this.template = template;
	}

	public List<PageFieldMeta> getBasicFields() {
		return basicFields;
	}

	public void setBasicFields(List<PageFieldMeta> basicFields) {
		this.basicFields = basicFields;
	}

	public List<SubPageMeta> getSubItems() {
		return subItems;
	}

	public void setSubItems(List<SubPageMeta> subItems) {
		this.subItems = subItems;
	}

	@Override
	public String toString() {
		return "PageMeta [template=" + template + ", basicFields=" + basicFields + ", subItems=" + subItems + "]";
	}

}
