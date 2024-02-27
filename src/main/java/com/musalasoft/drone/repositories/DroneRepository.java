package com.musalasoft.drone.repositories;

import com.musalasoft.drone.enums.State;
import com.musalasoft.drone.models.Drone;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface DroneRepository  extends JpaRepository<Drone, String> {
	Optional<Drone> findBySerialNumber(String serialNumber);


	@Query("SELECT d FROM Drone d WHERE d.state = 'IDLE' AND d.batteryLevel > 24")
	List<Drone> findAllAvailableDrone();


}