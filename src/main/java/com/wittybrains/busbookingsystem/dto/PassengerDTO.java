package com.wittybrains.busbookingsystem.dto;

public class PassengerDTO {

   
    private String name;

   
    private String contactNumber;

    
    private String email;

    
    private String password;

    public PassengerDTO() {
    }

    public PassengerDTO(String name, String contactNumber, String email, String password) {
        this.setName(name);
        this.setContactNumber(contactNumber);
        this.setEmail(email);
        this.setPassword(password);
    }

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getContactNumber() {
		return contactNumber;
	}

	public void setContactNumber(String contactNumber) {
		this.contactNumber = contactNumber;
	}

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

    // getters and setters
}
