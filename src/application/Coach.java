package application;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class Coach {
	
	private final SimpleIntegerProperty id;
	private final SimpleStringProperty firstName;
	private final SimpleStringProperty lastName;
	private final SimpleStringProperty email;
	private final SimpleStringProperty phone;
	private final SimpleStringProperty username;
	private final SimpleStringProperty password;
	private final SimpleStringProperty licenceNumber;
	
	
	public Coach(int id,String fName, String lName, String email, String phone, String username, String password, String licenceNumber) {
		this.id = new SimpleIntegerProperty(id);
		this.firstName = new SimpleStringProperty(fName);
		this.lastName = new SimpleStringProperty(lName);
		this.email = new SimpleStringProperty(email);
		this.phone = new SimpleStringProperty(phone);
		this.username = new SimpleStringProperty(username);
		this.password = new SimpleStringProperty(password);
		this.licenceNumber = new SimpleStringProperty(licenceNumber);
	}


	public SimpleStringProperty getUsername() {
		return username;
	}

	public SimpleIntegerProperty getId() {
		return id;
	}

	public SimpleStringProperty getPassword() {
		return password;
	}

	public SimpleStringProperty getEmail() {
		return email;
	}


	public SimpleStringProperty getFirstName() {
		return firstName;
	}


	public SimpleStringProperty getLastName() {
		return lastName;
	}


	public SimpleStringProperty getLincenceNumber() {
		return licenceNumber;
	}


	public SimpleStringProperty getPhone() {
		return phone;
	}
	
	public int getIdInteger() {
		return id.get();
	}
	
	public String getFirstNameString() {
		return firstName.get();
	}
	
	public String getLastNameString() {
		return lastName.get();
	}
	
	public String getEmailString() {
		return email.get();
	}
	
	public String getPhoneNumberString() {
		return phone.get();
	}
	
	public String getUsernameString() {
		return username.get();
	}

	public String getPasswordString() {
		return password.get();
	}
	
	public String getLicenceNumberString() {
		return licenceNumber.get();
	}
}
