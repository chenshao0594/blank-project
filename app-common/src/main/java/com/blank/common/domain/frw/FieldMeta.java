package com.blank.common.domain.frw;

public class FieldMeta {
	private String name;
	private String type;
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

	@Override
	public String toString() {
		return "FieldMeta [name=" + name + ", type=" + type + ", translationKey=" + translationKey + ", colLength="
				+ colLength + ", required=" + required + "]";
	}

}
