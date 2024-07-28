package com.example.wipro.demo.entity;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;

@Entity

public class Cab {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long cabId;
	private String model;
	private String carType;
	private Boolean available;
	
	@ManyToOne
	@JoinColumn(name = "driver_id")
	private Driver driver;
	
	@OneToMany(mappedBy = "cab")
	private List<Booking> booking;
	
	public Cab(){}
	
	public Cab(Long cabId, String model, String carType, Boolean available, Driver driver,List<Booking> booking) {
		super();
		this.cabId = cabId;
		this.model = model;
		this.carType = carType;
		this.available = available;
		this.driver = driver;
		this.booking=booking;
	}

	public Long getCabId() {
		return cabId;
	}

	public void setCabId(Long cabId) {
		this.cabId = cabId;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public String getCarType() {
		return carType;
	}

	public void setCarType(String carType) {
		this.carType = carType;
	}

	public Boolean getAvailable() {
		return available;
	}

	public void setAvailable(Boolean available) {
		this.available = available;
	}

	public Driver getDriver() {
		return driver;
	}

	public void setDriver(Driver driver) {
		this.driver = driver;
	}
	
	public List<Booking> getBooking() {
		return booking;
	}
	
	public void setBooking(List<Booking> booking) {
		this.booking=booking;
	}

	@Override
	public String toString() {
		return "Cab [cabId=" + cabId + ", model=" + model + ", carType=" + carType + ", available=" + available
				+ ", driver=" + driver + ",booking="+ booking +"]";
	}
	
	
	
	
}
