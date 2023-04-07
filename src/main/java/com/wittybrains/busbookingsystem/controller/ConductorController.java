package com.wittybrains.busbookingsystem.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.wittybrains.busbookingsystem.dto.ConductorDTO;
import com.wittybrains.busbookingsystem.model.Conductor;
import com.wittybrains.busbookingsystem.repository.BusRepository;
import com.wittybrains.busbookingsystem.repository.UserRepository;
import com.wittybrains.busbookingsystem.service.ConductorService;

/**
 * 
 * REST controller to handle CRUD operations for Conductors.
 */
@RestController
@RequestMapping("/conductors")
public class ConductorController {

	private static final Logger LOGGER = LoggerFactory.getLogger(ConductorController.class);

	private final ConductorService conductorService;
	private final BusRepository busRepository;
	private final UserRepository userRepository;

	public ConductorController(ConductorService conductorService, BusRepository busRepository,
			UserRepository userRepository) {
		this.conductorService = conductorService;
		this.busRepository = busRepository;
		this.userRepository = userRepository;
	}

	/**
	 * 
	 * Endpoint to create a new conductor
	 * 
	 * @param conductorDTO the conductor object to be created
	 * @return ResponseEntity object with the created Conductor object and HTTP
	 *         status
	 */
	@PostMapping
	public ResponseEntity<Conductor> createConductor(@RequestBody ConductorDTO conductorDTO) {
		LOGGER.info("Creating conductor: {}", conductorDTO);
		Conductor conductor = conductorService.createConductor(conductorDTO);
		LOGGER.info("Conductor created successfully with id: {}", conductor.getId());
		return ResponseEntity.status(HttpStatus.CREATED).body(conductor);
	}

	/**
	 * 
	 * Endpoint to get all conductors
	 * 
	 * @return ResponseEntity object with the list of Conductors and HTTP status
	 */
	@GetMapping
	public ResponseEntity<List<Conductor>> getAllConductors() {
		LOGGER.info("Retrieving all conductors");
		List<Conductor> conductors = conductorService.getAllConductors();
		LOGGER.info("Retrieved {} conductors", conductors.size());
		return ResponseEntity.ok().body(conductors);
	}

	/**
	 * 
	 * Endpoint to get a conductor by ID
	 * 
	 * @param id the ID of the conductor to be retrieved
	 * @return ResponseEntity object with the retrieved Conductor object and HTTP
	 *         status
	 */
	@GetMapping("/{id}")
	public ResponseEntity<Conductor> getConductorById(@PathVariable Long id) {
		LOGGER.info("Retrieving conductor with id: {}", id);
		Conductor conductor = conductorService.getConductorById(id);
		if (conductor == null) {
			LOGGER.warn("Conductor with id {} not found", id);
			return ResponseEntity.notFound().build();
		}
		LOGGER.info("Retrieved conductor: {}", conductor);
		return ResponseEntity.ok().body(conductor);
	}

	/**
	 * 
	 * Endpoint to update a conductor
	 * 
	 * @param id           the ID of the conductor to be updated
	 * @param conductorDTO the updated conductor object
	 * @return ResponseEntity object with the updated Conductor object and HTTP
	 *         status
	 */
	@PutMapping("/{id}")
	public ResponseEntity<Conductor> updateConductor(@PathVariable Long id, @RequestBody ConductorDTO conductorDTO) {
		LOGGER.info("Updating conductor with id: {}", id);
		Conductor conductor = conductorService.getConductorById(id);
		if (conductor == null) {
			LOGGER.warn("Conductor with id {} not found", id);
			return ResponseEntity.notFound().build();
		}
		conductor.setName(conductorDTO.getName());
		conductor.setBus(busRepository.findById(conductorDTO.getBusDTO().getId())
				.orElseThrow(() -> new RuntimeException("Bus not found")));
		conductor.setUser(userRepository.findById(conductorDTO.getUserDTO().getId())
				.orElseThrow(() -> new RuntimeException("User not found")));
		conductor = conductorService.updateConductor(id, conductorDTO);
		LOGGER.info("Updated conductor: {}", conductor);
		return ResponseEntity.ok(conductor);
	}
}