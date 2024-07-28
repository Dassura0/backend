package com.example.wipro.demo.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.wipro.demo.entity.Driver;

public interface DriverRepository extends JpaRepository<Driver,Long> {
	Optional<Driver> findByUsername(String username);

}
