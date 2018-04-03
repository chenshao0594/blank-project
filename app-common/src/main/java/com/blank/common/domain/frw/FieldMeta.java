package com.blank.common.domain.frw;

import java.io.Serializable;

public class FieldMeta implements Serializable {
	private String name;
	private String type;
	private Class<?> clazz;
	private Object defaultValue;
	private String translationKey;
	private long colLength = 6;
	private boolean required = false;

	public FieldMeta(String name, String type) {
		this.name = name;
		this.type = type;
	}

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

	@Override
	public String toString() {
		return "FieldMeta [name=" + name + ", type=" + type + ", defaultValue=" + defaultValue + ", translationKey="
				+ translationKey + ", colLength=" + colLength + ", required=" + required + "]";
	}

}
