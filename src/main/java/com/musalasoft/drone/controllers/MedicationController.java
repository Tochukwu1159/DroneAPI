package com.musalasoft.drone.controllers;


import com.musalasoft.drone.models.Medication;
import com.musalasoft.drone.payloads.requests.MedicationDto;
import com.musalasoft.drone.payloads.responses.MedicationResponse;
import com.musalasoft.drone.service.MedicationService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Validated
@RestController
@RequestMapping("/api/v1/medication")
@RequiredArgsConstructor
public class MedicationController {

	private final MedicationService medicationService;

	@PostMapping("/register")
	public ResponseEntity<MedicationResponse> registerMedication(@RequestBody @Valid MedicationDto medicationDto) {
		MedicationResponse medicationResponse = medicationService.registerMedication(medicationDto);
		return new ResponseEntity<>(medicationResponse, HttpStatus.CREATED);
	}
@GetMapping("/allMedication")
	public ResponseEntity<List<MedicationResponse>> getAllMedication(){
		List<MedicationResponse> medicationResponseList = medicationService.getAllMedication();
		return new ResponseEntity<>(medicationResponseList, HttpStatus.OK);
}
}