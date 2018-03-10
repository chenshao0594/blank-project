package com.blank.common.domain;

import java.util.Arrays;
import java.util.List;

public class DomainMetadata {
	private String name;
	private String pluralName;
	private List<FieldMeta> metaFields;
	private List<String> listFields;
	private List<FieldMeta> dialogFields;
	private List<String> detailFields;
	private String[] serachFields;
	private PermissionMeta permissionInfo;

	public List<String> getListFields() {
		return listFields;
	}

	public void setListFields(List<String> listFields) {
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

	public List<String> getDetailFields() {
		return detailFields;
	}

	public void setDetailFields(List<String> detailFields) {
		this.detailFields = detailFields;
	}

	public List<FieldMeta> getDialogFields() {
		return dialogFields;
	}

	public void setDialogFields(List<FieldMeta> dialogFields) {
		this.dialogFields = dialogFields;
	}

	@Override
	public String toString() {
		return "DomainMetadata [name=" + name + ", pluralName=" + pluralName + ", metaFields=" + metaFields
				+ ", listFields=" + listFields + ", dialogFields=" + dialogFields + ", detailFields=" + detailFields
				+ ", serachFields=" + Arrays.toString(serachFields) + ", permissionInfo=" + permissionInfo + "]";
	}

}