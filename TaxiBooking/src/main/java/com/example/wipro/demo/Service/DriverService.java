package com.example.wipro.demo.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.wipro.demo.Repository.BookingRepository;
import com.example.wipro.demo.Repository.DriverRepository;
import com.example.wipro.demo.entity.Booking;
import com.example.wipro.demo.entity.Driver;
@Service
public class DriverService {
	@Autowired
	private DriverRepository dr;
	@Autowired
	private BookingRepository br;
	
	
	public Driver signUp(String username,String password,String email,String phoneNumber)
	{
		if(dr.findByUsername(username).isPresent())
		{
			throw new RuntimeException("Driver already exists");
			
		}
		
		Driver driver=new Driver();
		driver.setUsername(username);
		driver.setPassword(password);
		driver.setEmail(email);
		driver.setPhoneNumber(phoneNumber);
		
		return dr.save(driver);
	}
	
	public Driver approvalStatus(Long id)
	{
		Driver driver=dr.findById(id).orElse(null);
		if(driver!=null) {
			driver.setApprovalStatus(false);
			
		}
		return dr.save(driver);
	}
	
	public Optional<Driver> login(String username,String password)
	{
		Driver driver=dr.findByUsername(username).orElse(null);
		if(driver!=null && driver.getPassword().equals(password)) {
			return Optional.of(driver);
		}
		return Optional.empty();
	}
	
	public List<Booking> viewBookingHistory(Long driverId)
	{
		return br.findByDriver_DriverId(driverId);
	}
	
	public Booking getBookingDetails(Long id)
	{
		return br.findById(id).orElseThrow(()->new RuntimeException("Booking not found"));
	}
	

	
	
}
