package com.example.wipro.demo.Controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.wipro.demo.Service.DriverService;
import com.example.wipro.demo.entity.Booking;
import com.example.wipro.demo.entity.Driver;

@RestController
@RequestMapping("/driver")
public class DriverController {

    @Autowired
    private DriverService driverService;

    // Driver sign up
    @PostMapping("/signup")
    public ResponseEntity<String> signUp(
            @RequestParam String username,
            @RequestParam String password,
            @RequestParam String email,
            @RequestParam String phoneNumber) {
        try {
            Driver driver = driverService.signUp(username, password, email, phoneNumber);
            return ResponseEntity.ok("Driver registered successfully with ID: " + driver.getId());
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    // Driver login
    @PostMapping("/login")
    public ResponseEntity<String> login(
            @RequestParam String username,
            @RequestParam String password) {
        Optional<Driver> driverOpt = driverService.login(username, password);
        return driverOpt.isPresent() ?
                ResponseEntity.ok("Login successful") :
                ResponseEntity.status(401).body("Invalid credentials");
    }

    // Update driver approval status
    @PutMapping("/approval/{id}")
    public ResponseEntity<String> updateApprovalStatus(@PathVariable Long id) {
        try {
            Driver driver = driverService.approvalStatus(id);
            return ResponseEntity.ok("Driver approval status updated successfully");
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    // View booking history
    @GetMapping("/bookings/{driverId}")
    public ResponseEntity<List<Booking>> viewBookingHistory(@PathVariable Long driverId) {
        List<Booking> bookings = driverService.viewBookingHistory(driverId);
        return ResponseEntity.ok(bookings);
    }

    // Get booking details
    @GetMapping("/bookings/details/{id}")
    public ResponseEntity<Booking> getBookingDetails(@PathVariable Long id) {
        try {
            Booking booking = driverService.getBookingDetails(id);
            return ResponseEntity.ok(booking);
        } catch (RuntimeException e) {
            return ResponseEntity.status(404).body(null);
        }
    }
}