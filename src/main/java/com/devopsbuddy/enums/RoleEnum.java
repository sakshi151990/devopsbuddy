package com.devopsbuddy.enums;

public enum RoleEnum {

	BASIC(1, "ROLE_BASIC"), PRO(2, "ROLE_PRO"), ADMIN(3, "ROLE_ADMIN");

	private final int id;
	private final String rolename;

	public int getId() {
		return id;
	}

	public String getRolename() {
		return rolename;
	}

	private RoleEnum(int id, String rolename) {
		this.id = id;
		this.rolename = rolename;
	}

}
