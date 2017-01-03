package com.project.selenium;

public class User {

	public static final int TYLKO_LOGIN_I_HASLO = 2;

	private String username;
	private String email;
	private String password;

	public User() {

	}

	public User(String[] stringTab) {
		if (stringTab != null && stringTab.length > 1) {
			switch (stringTab.length) {
			case TYLKO_LOGIN_I_HASLO:
				this.email = (String) stringTab[0];
				this.password = (String) stringTab[1];
				break;
			default:
				System.out.println("Incorrect number of paramethers - " + stringTab.length);
				break;
			}
		}
	}

	public User(String email, String password) {
		this.email = email;
		this.password = password;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String toString() {
		return "User email: " + email + " pass: " + password;
	}
}
