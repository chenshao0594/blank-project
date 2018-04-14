package com.blank.common.domain.frw.field;

public abstract class AbstractField {
	private final String name;
	private final Class<?> type;
	private String translationKey;
	private Object defaultValue;
	private boolean isComplex;

	public AbstractField(String name, Class<?> type) {
		this.name = name;
		this.type = type;
		this.isComplex = false;
	}

	public String getName() {
		return name;
	}

	public Class<?> getType() {
		return type;
	}

	public String getTranslationKey() {
		return translationKey;
	}

	public void setTranslationKey(String translationKey) {
		this.translationKey = translationKey;
	}

	public boolean getIsComplex() {
		return isComplex;
	}

	public void setIsComplex(boolean isComplex) {
		this.isComplex = isComplex;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((type == null) ? 0 : type.hashCode());
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
		AbstractField other = (AbstractField) obj;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (type == null) {
			if (other.type != null)
				return false;
		} else if (!type.equals(other.type))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "AbstractField [name=" + name + ", type=" + type + ", translationKey=" + translationKey
				+ ", defaultValue=" + defaultValue + "]";
	}

}
