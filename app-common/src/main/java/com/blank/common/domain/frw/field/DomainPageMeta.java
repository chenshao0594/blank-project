package com.blank.common.domain.frw.field;

import java.util.Set;

import com.blank.common.domain.frw.page.PageMeta;

public class DomainPageMeta {

	private final Class<?> domain;

	private String name;
	private String pluralName;

	private PageMeta listPage = new PageMeta();
	private PageMeta dialogPage = new PageMeta();
	private PageMeta detailPage = new PageMeta();
	private Set<String> serachFields;

	public DomainPageMeta(Class<?> domain) {
		this.domain = domain;
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

	public Set<String> getSerachFields() {
		return serachFields;
	}

	public void setSerachFields(Set<String> serachFields) {
		this.serachFields = serachFields;
	}

	public Class<?> getDomain() {
		return domain;
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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((domain == null) ? 0 : domain.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		DomainPageMeta other = (DomainPageMeta) obj;
		if (domain == null) {
			if (other.domain != null)
				return false;
		} else if (!domain.equals(other.domain))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "DomainPageMeta [domain=" + domain + ", listPage=" + listPage + ", dialogPage=" + dialogPage
				+ ", detailPage=" + detailPage + ", serachFields=" + serachFields + "]";
	}

}
