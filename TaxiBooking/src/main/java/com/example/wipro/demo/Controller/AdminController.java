package com.example.wipro.demo.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.wipro.demo.Service.AdminService;
import com.example.wipro.demo.entity.Booking;
import com.example.wipro.demo.entity.Cab;
import com.example.wipro.demo.entity.Customer;
import com.example.wipro.demo.entity.Driver;

@RestController
@RequestMapping("/admin")
public class AdminController {
	@Autowired
	private AdminService as;
	
	//login endpoint
	@PostMapping("/login")
	public ResponseEntity<String> login(@RequestParam String username,@RequestParam String password)
	{
		boolean isAuthenticated=as.authenticate(username, password);
		return isAuthenticated ? ResponseEntity.ok("Login successful"):ResponseEntity.status(401).body("Invalid Credentials");
	}
	
	//Driver sign up and rejection
	@PostMapping("/drivers/{driverId}/approve")
	public ResponseEntity<String>approveDriver(@PathVariable Long driverId){
		Driver approvedDriver=as.signUp(driverId);
		if(approvedDriver!=null) {
			return ResponseEntity.ok("Driver approved");
			
		}
		else {
			return ResponseEntity.status(404).body("Driver not found");
		}
	}
	
	@PostMapping("/drivers/{driversId}/reject")
	public ResponseEntity<String>rejectDriver(@PathVariable Long driverId){
		as.rejectDriver(driverId);
		return ResponseEntity.ok("Driver rejected");
	}
	
	@PostMapping("/cabs")
	public ResponseEntity<Cab> addCab(@RequestBody Cab cab)
	{
		Cab newcab=as.addCab(cab);
		return ResponseEntity.ok(newcab);
	}
	
	@GetMapping("/cabs")
	public ResponseEntity<List<Cab>>readCabs()
	{
		List<Cab> cabs=as.readCab();
		return ResponseEntity.ok(cabs);
	}
	
	@GetMapping("/cabs/{cabId}")
    public ResponseEntity<Cab> readCab(@PathVariable Long cabId) {
        Cab cab = as.readCab(cabId);
        if (cab != null) {
            return ResponseEntity.ok(cab);
        } else {
            return ResponseEntity.status(404).body(null);
        }
    }

    @PutMapping("/cabs/{cabId}")
    public ResponseEntity<Cab> updateCab(@PathVariable Long cabId, @RequestBody Cab cab) {
        cab.setCabId(cabId);
        Cab updatedCab = as.updateCab(cab);
        if (updatedCab != null) {
            return ResponseEntity.ok(updatedCab);
        } else {
            return ResponseEntity.status(404).body(null);
        }
    }

    @DeleteMapping("/cabs/{cabId}")
    public ResponseEntity<String> deleteCab(@PathVariable Long cabId) {
        Cab deletedCab = as.deleteCab(cabId);
        if (deletedCab != null) {
            return ResponseEntity.ok("Cab deleted successfully");
        } else {
            return ResponseEntity.status(404).body("Cab not found");
        }
    }

    // Manage Drivers
    @PostMapping("/drivers")
    public ResponseEntity<Driver> addDriver(@RequestBody Driver driver) {
        Driver newDriver = as.addDriver(driver);
        return ResponseEntity.ok(newDriver);
    }

    @GetMapping("/drivers/{driverId}")
    public ResponseEntity<Driver> readDriver(@PathVariable Long driverId) {
        Driver driver = as.readDriver(driverId);
        if (driver != null) {
            return ResponseEntity.ok(driver);
        } else {
            return ResponseEntity.status(404).body(null);
        }
    }

    @PutMapping("/drivers/{driverId}")
    public ResponseEntity<Driver> updateDriver(@PathVariable Long driverId, @RequestBody Driver driver) {
        driver.setId(driverId);
        Driver updatedDriver = as.updateDriver(driver);
        if (updatedDriver != null) {
            return ResponseEntity.ok(updatedDriver);
        } else {
            return ResponseEntity.status(404).body(null);
        }
    }

    @DeleteMapping("/drivers/{driverId}")
    public ResponseEntity<String> deleteDriver(@PathVariable Long driverId) {
        Driver deletedDriver = as.deleteDriver(driverId);
        if (deletedDriver != null) {
            return ResponseEntity.ok("Driver deleted successfully");
        } else {
            return ResponseEntity.status(404).body("Driver not found");
        }
    }

    // Manage Bookings
    @GetMapping("/bookings")
    public ResponseEntity<List<Booking>> viewBookings() {
        List<Booking> bookings = as.viewBookings();
        return ResponseEntity.ok(bookings);
    }

    @PostMapping("/bookings/{bookingId}/cancel")
    public ResponseEntity<String> cancelBooking(@PathVariable Long bookingId) {
        as.cancelBooking(bookingId);
        return ResponseEntity.ok("Booking cancelled");
    }

    // Manage Customers
    @PostMapping("/customers")
    public ResponseEntity<Customer> addCustomer(@RequestBody Customer customer) {
        Customer newCustomer = as.addCustomer(customer);
        return ResponseEntity.ok(newCustomer);
    }

    @GetMapping("/customers")
    public ResponseEntity<List<Customer>> readCustomers() {
        List<Customer> customers = as.readCustomer();
        return ResponseEntity.ok(customers);
    }

    @GetMapping("/customers/{customerId}")
    public ResponseEntity<Customer> readCustomer(@PathVariable Long customerId) {
        Customer customer = as.readCustomer(customerId);
        if (customer != null) {
            return ResponseEntity.ok(customer);
        } else {
            return ResponseEntity.status(404).body(null);
        }
    }

    @PutMapping("/customers/{customerId}")
    public ResponseEntity<Customer> updateCustomer(@PathVariable Long customerId, @RequestBody Customer customer) {
        customer.setCustomerId(customerId);
        Customer updatedCustomer = as.updateCustomer(customer);
        if (updatedCustomer != null) {
            return ResponseEntity.ok(updatedCustomer);
        } else {
            return ResponseEntity.status(404).body(null);
        }
    }

    @DeleteMapping("/customers/{customerId}")
    public ResponseEntity<String> deleteCustomer(@PathVariable Long customerId) {
        Customer deletedCustomer = as.deleteCustomer(customerId);
        if (deletedCustomer != null) {
            return ResponseEntity.ok("Customer deleted successfully");
        } else {
            return ResponseEntity.status(404).body("Customer not found");
        }
    }
	
}
