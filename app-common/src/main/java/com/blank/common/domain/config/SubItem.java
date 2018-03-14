package com.blank.common.domain.config;

import java.util.LinkedList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import com.blank.common.domain.frw.FieldMeta;

@XmlRootElement(name = "sub-item")
@XmlAccessorType(XmlAccessType.FIELD)
public class SubItem {
	@XmlElement(name = "name")
	private String name;
	@XmlElement(name = "fields")
	private String fields;
	private List<FieldMeta> fieldMetas = new LinkedList<FieldMeta>();

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getFields() {
		return fields;
	}

	public void setFields(String fields) {
		this.fields = fields;
	}

	public List<FieldMeta> getFieldMetas() {
		return fieldMetas;
	}

	public void setFieldMetas(List<FieldMeta> fieldMetas) {
		this.fieldMetas = fieldMetas;
	}

	@Override
	public String toString() {
		return "SubItem [name=" + name + ", fields=" + fields + ", fieldMetas=" + fieldMetas + "]";
	}

}
