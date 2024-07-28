package com.example.wipro.demo.Controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.wipro.demo.Service.CustomerService;
import com.example.wipro.demo.entity.Booking;
import com.example.wipro.demo.entity.Cab;
import com.example.wipro.demo.entity.Customer;

@RestController
@RequestMapping("/customer")
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    // Customer sign up
    @PostMapping("/signup")
    public ResponseEntity<String> signUp(
            @RequestParam String username,
            @RequestParam String password,
            @RequestParam String email,
            @RequestParam String phoneNumber) {
        try {
            Customer customer = customerService.signUp(username, password, email, phoneNumber);
            return ResponseEntity.ok("Customer registered successfully with ID: " + customer.getCustomerId());
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    // Customer login
    @PostMapping("/login")
    public ResponseEntity<String> login(
            @RequestParam String username,
            @RequestParam String password) {
        Optional<Customer> customerOpt = customerService.login(username, password);
        return customerOpt.isPresent() ?
                ResponseEntity.ok("Login successful") :
                ResponseEntity.status(401).body("Invalid credentials");
    }

    // Update customer profile
    @PutMapping("/profile/{id}")
    public ResponseEntity<String> updateProfile(
            @PathVariable Long id,
            @RequestBody Customer updatedCustomer) {
        try {
            Customer customer = customerService.updateProfile(id, updatedCustomer);
            return ResponseEntity.ok("Profile updated successfully for customer ID: " + customer.getCustomerId());
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    // View available cabs
    @GetMapping("/cabs")
    public ResponseEntity<List<Cab>> viewCabs() {
        List<Cab> cabs = customerService.viewCabs();
        return ResponseEntity.ok(cabs);
    }

    // View booking history
    @GetMapping("/bookings/{customerId}")
    public ResponseEntity<List<Booking>> viewBookingsHistory(@PathVariable Long customerId) {
        List<Booking> bookings = customerService.viewBookingsHistory(customerId);
        return ResponseEntity.ok(bookings);
    }

    // Book a cab
    @PostMapping("/bookings")
    public ResponseEntity<String> bookCab(
            @RequestParam Long cabId,
            @RequestParam Long customerId) {
        try {
            Booking booking = customerService.bookCab(cabId, customerId);
            return ResponseEntity.ok("Cab booked successfully with Booking ID: " + booking.getId());
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    // Cancel a booking
    @PostMapping("/bookings/{id}/cancel")
    public ResponseEntity<String> cancelBooking(@PathVariable Long id) {
        customerService.cancelBooking(id);
        return ResponseEntity.ok("Booking cancelled successfully");
    }
}