package com.wittybrains.busbookingsystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.wittybrains.busbookingsystem.model.Conductor;


public interface ConductorRepository extends JpaRepository<Conductor, Long> {
}

