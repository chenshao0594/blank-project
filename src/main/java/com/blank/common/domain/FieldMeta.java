package com.blank.common.domain;

public class FieldMeta {
	private String name;
	private String type;
	private String translationKey;

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

	@Override
	public String toString() {
		return "FieldMeta [name=" + name + ", type=" + type + ", translationKey=" + translationKey + "]";
	}

}
