package com.blank.common.domain.frw;

import java.util.Arrays;
import java.util.List;

public class DomainMetadata {
	private String name;
	private String pluralName;
	private String listPage;
	private String dialogPage;
	private String detailPage;
	private List<FieldMeta> metaFields;
	private List<FieldMeta> listFields;
	private List<FieldMeta> dialogFields;
	private List<FieldMeta> detailFields;
	private String[] serachFields;
	private PermissionMeta permissionInfo;

	public List<FieldMeta> getListFields() {
		return listFields;
	}

	public void setListFields(List<FieldMeta> listFields) {
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

	public List<FieldMeta> getDialogFields() {
		return dialogFields;
	}

	public void setDialogFields(List<FieldMeta> dialogFields) {
		this.dialogFields = dialogFields;
	}

	public List<FieldMeta> getDetailFields() {
		return detailFields;
	}

	public void setDetailFields(List<FieldMeta> detailFields) {
		this.detailFields = detailFields;
	}

	public String getListPage() {
		return listPage;
	}

	public void setListPage(String listPage) {
		this.listPage = listPage;
	}

	public String getDialogPage() {
		return dialogPage;
	}

	public void setDialogPage(String dialogPage) {
		this.dialogPage = dialogPage;
	}

	public String getDetailPage() {
		return detailPage;
	}

	public void setDetailPage(String detailPage) {
		this.detailPage = detailPage;
	}

	@Override
	public String toString() {
		return "DomainMetadata [name=" + name + ", pluralName=" + pluralName + ", listPage=" + listPage
				+ ", dialogPage=" + dialogPage + ", detailPage=" + detailPage + ", metaFields=" + metaFields
				+ ", listFields=" + listFields + ", dialogFields=" + dialogFields + ", detailFields=" + detailFields
				+ ", serachFields=" + Arrays.toString(serachFields) + ", permissionInfo=" + permissionInfo + "]";
	}

}
