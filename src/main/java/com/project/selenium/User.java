package com.project.selenium;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class User {

	public static final int TYLKO_EMAIL_I_HASLO = 2;
	public static final int DANE_MINIMALNE = 13;

	private String email;
	private String password;
	private Sex gender;
	private String firstName;
	private String lastName;
	private LocalDate birthDate;
	private String address;
	private String city;
	private String state;
	private String zipCode;
	private String country;
	private String mobile;
	private String alias;

	public enum Sex {
		Male, Female;
	}

	public User() {

	}

	public User(String[] stringTab) {
		if (stringTab != null && stringTab.length > 1) {
			switch (stringTab.length) {
			case TYLKO_EMAIL_I_HASLO:
				email = (String) stringTab[0];
				password = (String) stringTab[1];
				break;
			case DANE_MINIMALNE:
				email = (String) stringTab[0];
				password = (String) stringTab[1];
				gender = stringTab[2] == "M" ? Sex.Male : Sex.Female;
				firstName = stringTab[3];
				lastName = stringTab[4];
				DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
				birthDate = LocalDate.parse(stringTab[5], dtf);
				address = stringTab[6];
				city = stringTab[7];
				state = stringTab[8];
				zipCode = stringTab[9];
				country = stringTab[10];
				mobile = stringTab[11];
				alias = stringTab[12];
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

	public String getPassword() {
		return password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String toString() {
		return "User email: " + email + " pass: " + password;
	}

	public Sex getGender() {
		return gender;
	}

	public void setGender(Sex gender) {
		this.gender = gender;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public LocalDate getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(LocalDate birthDate) {
		this.birthDate = birthDate;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getZipCode() {
		return zipCode;
	}

	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getAlias() {
		return alias;
	}

	public void setAlias(String alias) {
		this.alias = alias;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}
}
