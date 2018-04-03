package com.snow.test;

import java.util.HashMap;
import java.util.Map;

public class DomainMeta {

	private String name;

	private String pural;
	private Map<String, AbstractField> fields;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPural() {
		return pural;
	}

	public void setPural(String pural) {
		this.pural = pural;
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
		result = prime * result + ((pural == null) ? 0 : pural.hashCode());
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
		if (pural == null) {
			if (other.pural != null)
				return false;
		} else if (!pural.equals(other.pural))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "DomainMeta [name=" + name + ", pural=" + pural + ", fields=" + fields + "]";
	}

}
