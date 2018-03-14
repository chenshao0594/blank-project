package com.blank.common.domain.frw;

import java.util.LinkedList;
import java.util.List;

public class SubItemMeta {
	private final String name;
	private List<FieldMeta> fields = new LinkedList<FieldMeta>();
	private String actions;

	public SubItemMeta(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public String getActions() {
		return actions;
	}

	public void setActions(String actions) {
		this.actions = actions;
	}

	public List<FieldMeta> getFields() {
		return fields;
	}

	public void setFields(List<FieldMeta> fields) {
		this.fields = fields;
	}

	@Override
	public String toString() {
		return "SubItemMeta [name=" + name + ", fields=" + fields + ", actions=" + actions + "]";
	}

}
