package com.blank.common.domain.config;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import com.blank.common.domain.frw.page.PageFieldMeta;

@XmlRootElement()
@XmlAccessorType(XmlAccessType.FIELD)
public class PageConfig {
	@XmlElement(name = "template")
	private String template;
	@XmlElement(name = "basic-info")
	private String basicInfo;
	@XmlElement(name = "sub-item")
	private SubItem[] subItems;
	private List<PageFieldMeta> basicFields = new LinkedList<PageFieldMeta>();

	public String getBasicInfo() {
		return basicInfo;
	}

	public void setBasicInfo(String basicInfo) {
		this.basicInfo = basicInfo;
	}

	public SubItem[] getSubItems() {
		return subItems;
	}

	public void setSubItems(SubItem[] subItems) {
		this.subItems = subItems;
	}

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

	@Override
	public String toString() {
		return "DetailPageConfig [template=" + template + ", basicInfo=" + basicInfo + ", subItems="
				+ Arrays.toString(subItems) + ", basicFields=" + basicFields + "]";
	}

}
