package com.musalasoft.drone.service;


import com.musalasoft.drone.models.Medication;
import com.musalasoft.drone.payloads.requests.MedicationDto;
import com.musalasoft.drone.payloads.responses.MedicationResponse;

import java.util.List;

public interface MedicationService {

	MedicationResponse registerMedication(MedicationDto medicationDto);

	List<MedicationResponse> getAllMedication();
}