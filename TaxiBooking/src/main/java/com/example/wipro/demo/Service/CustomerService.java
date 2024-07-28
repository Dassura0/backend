package com.example.wipro.demo.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.wipro.demo.Repository.BookingRepository;
import com.example.wipro.demo.Repository.CabRepository;
import com.example.wipro.demo.Repository.CustomerRepository;
import com.example.wipro.demo.entity.Booking;
import com.example.wipro.demo.entity.Cab;
import com.example.wipro.demo.entity.Customer;
@Service
public class CustomerService {
	@Autowired
	private  CustomerRepository cr;
	@Autowired
	private CabRepository cbr;
	@Autowired
	private BookingRepository br;
	public Customer signUp(String username,String password,String email,String phoneNumber)
	{
		if(cr.findByUsername(username).isPresent())
		{
			throw new RuntimeException("Username already exists");
		}
		
		Customer customer= new Customer();
		customer.setUsername(username);
		customer.setPassword(password);
		customer.setEmail(email);
		customer.setPhoneNumber(phoneNumber);
		
		return cr.save(customer);
	}
	
	public Optional<Customer> login(String username,String password){
		Customer customer=cr.findByUsername(username).orElse(null);
		if(customer!=null && customer.getPassword().equals(password)) {
			return Optional.of(customer);
		}
		return Optional.empty();
	}
	
	public Customer updateProfile(Long id,Customer updatedCustomer) {
		Customer customer=cr.findById(id).orElseThrow(()->new RuntimeException("Customer not found"));
		customer.setUsername(updatedCustomer.getUsername());
		customer.setPassword(updatedCustomer.getPassword());
		customer.setAddress(updatedCustomer.getAddress());
		customer.setEmail(updatedCustomer.getEmail());
		customer.setPhoneNumber(updatedCustomer.getPhoneNumber());
		return cr.save(customer);
	}
	
	public List<Cab> viewCabs()
	{
		return cbr.findAll();
	}
	
	public List<Booking> viewBookingsHistory(Long customerId)
	{
		return br.findByCustomer_CustomerId(customerId);
	}
	
	public Booking bookCab(Long CabId,Long customerId)
	{
		Customer customer=cr.findById(customerId).orElseThrow(()->new RuntimeException("Customer not found"));
		Cab cab=cbr.findById(CabId).orElseThrow(()->new RuntimeException("Cab not found"));
		if(!cab.getAvailable()) {
			throw new RuntimeException("Cab not available");
		}
		cab.setAvailable(false);
		cbr.save(cab);
		
		Booking booking=new Booking();
		booking.setCustomer(customer);
		booking.setCab(cab);
		return br.save(booking);
	}
	
	public void cancelBooking(Long id)
	{
		Booking booking = br.findById(id).orElse(null);
		if(booking!=null)
		{
			booking.setCancelled(true);
			br.save(booking);
		}
	}
		
}