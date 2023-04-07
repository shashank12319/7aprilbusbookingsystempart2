package com.wittybrains.busbookingsystem.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.wittybrains.busbookingsystem.dto.ConductorDTO;
import com.wittybrains.busbookingsystem.model.Bus;
import com.wittybrains.busbookingsystem.model.Conductor;
import com.wittybrains.busbookingsystem.model.User;
import com.wittybrains.busbookingsystem.repository.BusRepository;
import com.wittybrains.busbookingsystem.repository.ConductorRepository;
import com.wittybrains.busbookingsystem.repository.UserRepository;

@Service
public class ConductorService {

    private static final Logger logger = LoggerFactory.getLogger(ConductorService.class);

    private final ConductorRepository conductorRepository;
    private final BusRepository busRepository;
    private final UserRepository userRepository;

    public ConductorService(ConductorRepository conductorRepository, BusRepository busRepository, UserRepository userRepository) {
        this.conductorRepository = conductorRepository;
        this.busRepository = busRepository;
        this.userRepository = userRepository;
    }

    public Conductor createConductor(ConductorDTO conductorDTO) {
        logger.info("Creating conductor with name {}", conductorDTO.getName());
        Conductor conductor = new Conductor();
        conductor.setName(conductorDTO.getName());

        if (conductorDTO.getBusDTO() != null) {
            Bus bus = busRepository.findById(conductorDTO.getBusDTO().getId())
                    .orElseThrow(() -> new RuntimeException("Bus not found"));
            conductor.setBus(bus);
        }

        if (conductorDTO.getUserDTO() != null) {
            User user = userRepository.findById(conductorDTO.getUserDTO().getId())
                    .orElseThrow(() -> new RuntimeException("User not found"));
            conductor.setUser(user);
        }

        Conductor savedConductor = conductorRepository.save(conductor);
        logger.info("Created conductor with ID {}", savedConductor.getId());
        return savedConductor;
    }

    public List<Conductor> getAllConductors() {
        logger.info("Fetching all conductors");
        return conductorRepository.findAll();
    }

    public Conductor getConductorById(Long id) {
        logger.info("Fetching conductor with ID {}", id);
        return conductorRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));
    }

    public Conductor updateConductor(Long id, ConductorDTO conductorDTO) {
        logger.info("Updating conductor with ID {}", id);
        Conductor conductor = conductorRepository.findById(id).orElseThrow();

        conductor.setName(conductorDTO.getName());

        Bus bus = busRepository.findById(conductorDTO.getBusDTO().getId())
                .orElseThrow(() -> new RuntimeException("Bus not found"));
        conductor.setBus(bus);

        User user = userRepository.findById(conductorDTO.getUserDTO().getId())
                .orElseThrow(() -> new RuntimeException("User not found"));
        conductor.setUser(user);

        Conductor updatedConductor = conductorRepository.save(conductor);
        logger.info("Updated conductor with ID {}", updatedConductor.getId());
        return updatedConductor;
    }

    public void deleteConductor(Long id) {
        logger.info("Deleting conductor with ID {}", id);
        Conductor conductor = conductorRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));

        conductorRepository.delete(conductor);
        logger.info("Deleted conductor with ID {}", id);
    }
}
