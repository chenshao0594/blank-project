package com.blank.common.domain.frw.page;

import java.io.Serializable;

public class PageFieldMeta implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5135890642322428908L;
	private String name;
	private String type;
	private String path;
	private Class<?> clazz;
	private Object defaultValue;
	private String translationKey;
	private long colLength = 6;
	private boolean required = false;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getTranslationKey() {
		return translationKey;
	}

	public void setTranslationKey(String translationKey) {
		this.translationKey = translationKey;
	}

	public long getColLength() {
		return colLength;
	}

	public void setColLength(long colLength) {
		this.colLength = colLength;
	}

	public boolean isRequired() {
		return required;
	}

	public void setRequired(boolean required) {
		this.required = required;
	}

	public Object getDefaultValue() {
		return defaultValue;
	}

	public void setDefaultValue(Object defaultValue) {
		this.defaultValue = defaultValue;
	}

	public Class<?> getClazz() {
		return clazz;
	}

	public void setClazz(Class<?> clazz) {
		this.clazz = clazz;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	@Override
	public String toString() {
		return "PageFieldMeta [name=" + name + ", type=" + type + ", path=" + path + ", clazz=" + clazz
				+ ", defaultValue=" + defaultValue + ", translationKey=" + translationKey + ", colLength=" + colLength
				+ ", required=" + required + "]";
	}

}
