package com.target.trak.system.security.domain;

import java.io.Serializable;

public class TargetTrakPrivilege implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long id;
	private String name;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "Privilege [name=" + name + "]";
	}
	
	
}
