package com.blank.common.domain.frw;

import java.util.LinkedList;
import java.util.List;

public class ChoiceField extends FieldMeta {

	private List<Choice> values = new LinkedList<Choice>();

	public ChoiceField(String name, String type) {
		super(name, "choice");
	}

	public List<Choice> getValues() {
		return values;
	}

	public void setValues(List<Choice> values) {
		this.values = values;
	}

}
