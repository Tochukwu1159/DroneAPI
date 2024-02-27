package com.musalasoft.drone.repositories;

import com.musalasoft.drone.models.Drone;
import com.musalasoft.drone.models.Medication;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface  MedicationRepository extends JpaRepository<Medication, String> {
	Optional<Medication> findByCode(String medicineCode);
}
