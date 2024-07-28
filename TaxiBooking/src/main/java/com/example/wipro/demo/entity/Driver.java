package com.example.wipro.demo.entity;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity

public class Driver {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	 private Long driverId;
    private String username;
    private String password;
    private String email;
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private String licenseNumber;
    private Boolean approvalStatus;
    	
    @OneToMany(mappedBy = "driver")
    private List<Cab> cab;
    
    @OneToMany(mappedBy = "driver")
    private List<Booking>booking;
    
    public Driver() {}

	public Driver(Long driverId, String username, String password, String email, String firstName, String lastName,
			String phoneNumber, String licenseNumber, Boolean approvalStatus, List<Cab> cab,
			List<Booking> booking) {
		super();
		this.driverId = driverId;
		this.username = username;
		this.password = password;
		this.email = email;
		this.firstName = firstName;
		this.lastName = lastName;
		this.phoneNumber = phoneNumber;
		this.licenseNumber = licenseNumber;
		this.approvalStatus = approvalStatus;
		this.cab = cab;
		this.booking = booking;
	}

	public Long getId() {
		return driverId;
	}

	public void setId(Long driverId) {
		this.driverId = driverId;
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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
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

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getLicenseNumber() {
		return licenseNumber;
	}

	public void setLicenseNumber(String licenseNumber) {
		this.licenseNumber = licenseNumber;
	}

	public Boolean getApprovalStatus() {
		return approvalStatus;
	}

	public void setApprovalStatus(Boolean approvalStatus) {
		this.approvalStatus = approvalStatus;
	}

	public List<Cab> getCab() {
		return cab;
	}

	public void setCab(List<Cab> cab) {
		this.cab = cab;
	}

	public List<Booking> getBooking() {
		return booking;
	}

	public void setBooking(List<Booking> booking) {
		this.booking = booking;
	}

	@Override
	public String toString() {
		return "Driver [driverId=" + driverId + ", username=" + username + ", password=" + password + ", email=" + email
				+ ", firstName=" + firstName + ", lastName=" + lastName + ", phoneNumber=" + phoneNumber
				+ ", licenseNumber=" + licenseNumber + ", approvalStatus=" + approvalStatus + ", cab=" + cab
				+ ", booking=" + booking + "]";
	}

	
    
    
	
}
