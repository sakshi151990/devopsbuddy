package com.devopsbuddy.enums;

public enum PlansEnum {

	BASIC(1, "BASIC"), PRO(2, "PRO");

	private final String name;
	private final int id;

	public String getName() {
		return name;
	}

	public int getId() {
		return id;
	}

	private PlansEnum(int id, String name) {
		this.name = name;
		this.id = id;
	}
}
