package com.wittybrains.busbookingsystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.wittybrains.busbookingsystem.model.Passenger;

@Repository
public interface PassengerRepository extends JpaRepository<Passenger, Long> {
    
}
