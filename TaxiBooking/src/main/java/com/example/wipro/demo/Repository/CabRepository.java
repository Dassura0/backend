package com.example.wipro.demo.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.wipro.demo.entity.Cab;

public interface CabRepository extends JpaRepository<Cab, Long> {
	
}
