package com.dje.parameters;

public class PostParameters {
	private String email;
	private String password;
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public PostParameters(String email, String password) {
		this.email = email;
		this.password = password;
	}
	public PostParameters() {
	}
	
}
