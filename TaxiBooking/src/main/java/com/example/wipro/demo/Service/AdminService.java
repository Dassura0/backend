package com.example.wipro.demo.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.wipro.demo.Repository.BookingRepository;
import com.example.wipro.demo.Repository.CabRepository;
import com.example.wipro.demo.Repository.CustomerRepository;
import com.example.wipro.demo.Repository.DriverRepository;
import com.example.wipro.demo.entity.Booking;
import com.example.wipro.demo.entity.Cab;
import com.example.wipro.demo.entity.Customer;
import com.example.wipro.demo.entity.Driver;

@Service
public class AdminService {
	
	@Autowired
	private BookingRepository br;
	
	@Autowired
	private CustomerRepository cr;
	
	@Autowired
	private DriverRepository dr;
	
	@Autowired
	private CabRepository cbr;
	
	
	private static final String adminUserName="admin";
	private static final String adminPassword="admin123";
	
	public boolean authenticate(String username,String password) {
		return adminUserName.equals(username)&&adminPassword.equals(password);
	}
	
	public Driver signUp(Long driverId)
	{
		Driver driver=dr.findById(driverId).orElse(null);
		if(driver!=null) {
			driver.setApprovalStatus(true);
			
		}
		return dr.save(driver);
	}
	
	public void rejectDriver(Long driverId) {
		dr.deleteById(driverId);
	}
	
	public Cab addCab(Cab cab)
	{
		return cbr.save(cab);
	}
	
	public List<Cab> readCab()
	{
		return cbr.findAll();
	}
	
	public Cab readCab(Long cabId)
	{
		Optional<Cab>temp=cbr.findById(cabId);
		Cab c=null;
		if(temp.isPresent()) {
			c=temp.get();
		}
		return c;
	}
	
	public Cab updateCab(Cab cab)
	{
		Cab temp=readCab(cab.getCabId());
			if(temp!=null)
			{
				temp=cab;
				cbr.save(temp);
				
			}
					
		return temp;
	}
	
	public Cab deleteCab(Long cabId)
	{	Cab temp=readCab(cabId);
		if(temp!=null)
		{
		cbr.deleteById(cabId);
		}
		return temp;
	}
	
	public Driver addDriver(Driver driver)
	{
		return dr.save(driver);
	}
	
	public Driver readDriver(Long driverId)
	{
		Optional<Driver>temp=dr.findById(driverId);
		Driver d=null;
		if(temp.isPresent())
		{
			d=temp.get();
		}
		return d;
	}
	
	public Driver updateDriver(Driver driver)
	{
		Driver temp=readDriver(driver.getId());
		if(temp!=null)
		{
			temp=driver;
			dr.save(temp);
		}
		return temp;
		
	}
	
	public Driver deleteDriver(Long driverId)
	{	Driver temp=readDriver(driverId);
		if(temp!=null)
		{
		cbr.deleteById(driverId);
		}
		return temp;
	}
	
	public List<Booking> viewBookings()
	{
		return br.findAll();
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
		
	public Customer addCustomer(Customer customer)
	{
		return cr.save(customer);
	}
	
	public List<Customer> readCustomer()
	{
		return cr.findAll();
	}
	
	public Customer readCustomer(Long customerId)
	{
		Optional<Customer>temp=cr.findById(customerId);
		Customer c=null;
		if(temp.isPresent()) {
			c=temp.get();
		}
		return c;
	}
	
	public Customer updateCustomer(Customer customer)
	{
		Customer temp=readCustomer(customer.getCustomerId());
			if(temp!=null)
			{
				temp=customer;
				cr.save(temp);
				
			}
					
		return temp;
	}
	
	public Customer deleteCustomer(Long customerId)
	{	Customer temp=readCustomer(customerId);
		if(temp!=null)
		{
		cr.deleteById(customerId);
		}
		return temp;
	}
	
	
	
}
