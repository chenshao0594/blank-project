package com.blank.common.domain;

import java.util.List;

public class DomainMetadata {
	private String name;
	private String pluralName;
	private List<FieldMeta> metaFields;
	private String[] listFields;
	private String[] serachFields;
	private PermissionMeta permissionInfo;

	public String[] getListFields() {
		return listFields;
	}

	public void setListFields(String[] listFields) {
		this.listFields = listFields;
	}

	public String[] getSerachFields() {
		return serachFields;
	}

	public void setSerachFields(String[] serachFields) {
		this.serachFields = serachFields;
	}

	public List<FieldMeta> getMetaFields() {
		return metaFields;
	}

	public void setMetaFields(List<FieldMeta> metaFields) {
		this.metaFields = metaFields;
	}

	public PermissionMeta getPermissionInfo() {
		return permissionInfo;
	}

	public void setPermissionInfo(PermissionMeta permissionInfo) {
		this.permissionInfo = permissionInfo;
	}

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

}
