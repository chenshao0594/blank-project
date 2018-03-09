package com.blank.common.domain;

public class PermissionMeta {
	private String role;
	private String operator;

	public PermissionMeta(String role, String operator) {
		this.role = role;
		this.operator = operator;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String getOperator() {
		return operator;
	}

	public void setOperator(String operator) {
		this.operator = operator;
	}

}
