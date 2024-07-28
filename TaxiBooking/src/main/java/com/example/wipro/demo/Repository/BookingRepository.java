package com.example.wipro.demo.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.wipro.demo.entity.Booking;
import com.example.wipro.demo.entity.Customer;
import com.example.wipro.demo.entity.Driver;

public interface BookingRepository extends JpaRepository<Booking,Long > {
	List<Booking>findByCustomer(Customer customer);
	List<Booking>findByDriver(Driver driver);
	 List<Booking> findByCustomer_CustomerId(Long customerId);
	 List<Booking> findByDriver_DriverId(Long driverId);

}
