package com.blank.common.domain.config;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "domain-meta")
@XmlAccessorType(XmlAccessType.FIELD)
public class DomainMetaConfig {
	@XmlElement
	private String name;
	@XmlElement
	private String pluralName;
	@XmlElement
	private String listFields;
	@XmlElement
	private String dialogFields;
	@XmlElement
	private String searchFields;

	@XmlElement(name = "permissions")
	private Permission[] permissions;
	@XmlElement
	private PageConfig detailPage;

	@XmlElement
	private PageConfig dialogPage;

	@XmlElement
	private PageConfig listPage;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPluralName() {
		return pluralName;
	}

	public void setPluralName(String pluralName) {
		this.pluralName = pluralName;
	}

	public String getListFields() {
		return listFields;
	}

	public void setListFields(String listFields) {
		this.listFields = listFields;
	}

	public String getSearchFields() {
		return searchFields;
	}

	public void setSearchFields(String searchFields) {
		this.searchFields = searchFields;
	}

	public Permission[] getPermissions() {
		return permissions;
	}

	public void setPermissions(Permission[] permissions) {
		this.permissions = permissions;
	}

	public String getDialogFields() {
		return dialogFields;
	}

	public void setDialogFields(String dialogFields) {
		this.dialogFields = dialogFields;
	}

	public PageConfig getDialogPage() {
		return dialogPage;
	}

	public void setDialogPage(PageConfig dialogPage) {
		this.dialogPage = dialogPage;
	}

	public PageConfig getListPage() {
		return listPage;
	}

	public void setListPage(PageConfig listPage) {
		this.listPage = listPage;
	}

	public PageConfig getDetailPage() {
		return detailPage;
	}

	public void setDetailPage(PageConfig detailPage) {
		this.detailPage = detailPage;
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

}
