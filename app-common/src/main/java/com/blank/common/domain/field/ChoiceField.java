package com.blank.common.domain.field;

import java.util.HashMap;
import java.util.Map;

public class ChoiceField extends FieldMeta {
	private Map<String, String> values = new HashMap<String, String>();

	public Map<String, String> getValues() {
		return values;
	}

	public void setValues(Map<String, String> values) {
		this.values = values;
	}

}
