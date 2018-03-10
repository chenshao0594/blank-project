package com.blank.common.domain;

import java.util.Arrays;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "domain-meta")
public class DomainMetaConfig {

	private String name;
	private String pluralName;
	private String listFields;
	private String searchFields;
	private Permission[] permissions;

	public String getName() {
		return name;
	}

	@XmlElement
	public void setName(String name) {
		this.name = name;
	}

	public String getPluralName() {
		return pluralName;
	}

	@XmlElement
	public void setPluralName(String pluralName) {
		this.pluralName = pluralName;
	}

	public String getListFields() {
		return listFields;
	}

	@XmlElement
	public void setListFields(String listFields) {
		this.listFields = listFields;
	}

	public String getSearchFields() {
		return searchFields;
	}

	@XmlElement
	public void setSearchFields(String searchFields) {
		this.searchFields = searchFields;
	}

	public Permission[] getPermissions() {
		return permissions;
	}

	@XmlElement
	public void setPermissions(Permission[] permissions) {
		this.permissions = permissions;
	}

	@XmlRootElement
	static class Permission {
		private String role;
		private String operation;

		public String getRole() {
			return role;
		}

		@XmlElement
		public void setRole(String role) {
			this.role = role;
		}

		public String getOperation() {
			return operation;
		}

		@XmlElement
		public void setOperation(String operation) {
			this.operation = operation;
		}

		@Override
		public String toString() {
			return "Permission [role=" + role + ", operation=" + operation + "]";
		}

	}

	@Override
	public String toString() {
		return "DomainMetaConfig [name=" + name + ", pluralName=" + pluralName + ", listFields=" + listFields
				+ ", searchFields=" + searchFields + ", permissions=" + Arrays.toString(permissions) + "]";
	}

}
