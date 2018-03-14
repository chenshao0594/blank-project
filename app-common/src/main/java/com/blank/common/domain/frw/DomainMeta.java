package com.blank.common.domain.frw;

import java.util.Arrays;

public class DomainMeta {

	private final String name;
	private String pluralName;
	private PageMeta listPage = new PageMeta();
	private PageMeta dialogPage = new PageMeta();
	private PageMeta detailPage = new PageMeta();
	private String[] serachFields;
	private PermissionMeta permissionInfo;

	public DomainMeta(String name) {
		this.name = name;
		this.pluralName = name;
	}

	public String getPluralName() {
		return pluralName;
	}

	public void setPluralName(String pluralName) {
		this.pluralName = pluralName;
	}

	public PageMeta getListPage() {
		return listPage;
	}

	public void setListPage(PageMeta listPage) {
		this.listPage = listPage;
	}

	public PageMeta getDialogPage() {
		return dialogPage;
	}

	public void setDialogPage(PageMeta dialogPage) {
		this.dialogPage = dialogPage;
	}

	public PageMeta getDetailPage() {
		return detailPage;
	}

	public void setDetailPage(PageMeta detailPage) {
		this.detailPage = detailPage;
	}

	public String[] getSerachFields() {
		return serachFields;
	}

	public void setSerachFields(String[] serachFields) {
		this.serachFields = serachFields;
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

	@Override
	public String toString() {
		return "DomainMeta [name=" + name + ", pluralName=" + pluralName + ", listPage=" + listPage + ", dialogPage="
				+ dialogPage + ", detailPage=" + detailPage + ", serachFields=" + Arrays.toString(serachFields)
				+ ", permissionInfo=" + permissionInfo + "]";
	}

}
