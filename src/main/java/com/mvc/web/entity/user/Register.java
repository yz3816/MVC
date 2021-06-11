package com.mvc.web.entity.user;

public class Register {

	private String id;
	private String pass;
	private String name;
	private String email;

	@Override
	public String toString() {
		return "Register [id=" + id + ", pass=" + pass + ", name=" + name + ", email=" + email + "]";
	}

	public Register() {

	}

	public Register(String id, String pass, String name, String email) {

		this.id = id;
		this.pass = pass;
		this.name = name;
		this.email = email;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPass() {
		return pass;
	}

	public void setPass(String pass) {
		this.pass = pass;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

}
