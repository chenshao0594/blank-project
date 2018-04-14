package com.blank.common.domain.frw;

import java.util.HashMap;
import java.util.Map;

import com.blank.common.domain.frw.field.AbstractField;

public class DomainMeta {

	private final String name;

	private String plural;
	private Map<String, AbstractField> fields;

	public DomainMeta(String name) {
		this.name = name;
		this.plural = name;
	}

	public String getName() {
		return name;
	}

	public String getPlural() {
		return plural;
	}

	public void setPlural(String plural) {
		this.plural = plural;
	}

	public Map<String, AbstractField> getFields() {
		return fields == null ? (fields = new HashMap<String, AbstractField>()) : fields;
	}

	public void setFields(Map<String, AbstractField> fields) {
		this.fields = fields;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((plural == null) ? 0 : plural.hashCode());
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
		DomainMeta other = (DomainMeta) obj;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (plural == null) {
			if (other.plural != null)
				return false;
		} else if (!plural.equals(other.plural))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "DomainMeta [name=" + name + ", plural=" + plural + ", fields=" + fields + "]";
	}

}
