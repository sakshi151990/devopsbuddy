package com.devopsbuddy.persistence;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.devopsbuddy.enums.RoleEnum;

@Entity
public class Role implements Serializable {

	/**
	 * 
	 */

	public Role() {
	}

	public Role(RoleEnum roleenum) {
		this.id = roleenum.getId();
		this.name = roleenum.getRolename();
	}

	private static final long serialVersionUID = 1L;

	@Id
	private int id;

	private String name;

	@OneToMany(mappedBy = "role", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private Set<UserRole> userrole = new HashSet<>();

	public String getName() {
		return name;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Set<UserRole> getUserrole() {
		return userrole;
	}

	public void setUserrole(Set<UserRole> userrole) {
		this.userrole = userrole;
	}

	public void setName(String name) {
		this.name = name;
	}
}
