package com.blank.common.domain.frw.page;

import java.io.Serializable;

public class PageFieldMeta implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5135890642322428908L;
	private String type = "string";
	private final String path;
	private Object defaultValue;
	private String translationKey;
	private FieldValueTypeEnum valueType;

	private long colLength = 6;
	private boolean required = false;

	public PageFieldMeta(String path) {
		this.path = path;
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

	public String getPath() {
		return path;
	}

	public FieldValueTypeEnum getValueType() {
		return valueType;
	}

	public void setValueType(FieldValueTypeEnum valueType) {
		this.valueType = valueType;
	}

	@Override
	public String toString() {
		return "PageFieldMeta [type=" + type + ", path=" + path + ", defaultValue=" + defaultValue + ", translationKey="
				+ translationKey + ", colLength=" + colLength + ", required=" + required + "]";
	}

}
