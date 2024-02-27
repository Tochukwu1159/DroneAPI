package com.musalasoft.drone.service.impl;

import com.musalasoft.drone.exceptions.CustomInternalServerException;
import com.musalasoft.drone.models.Medication;
import com.musalasoft.drone.objectMapper.MedicationMapper;
import com.musalasoft.drone.payloads.requests.MedicationDto;
import com.musalasoft.drone.payloads.responses.MedicationResponse;
import com.musalasoft.drone.repositories.MedicationRepository;
import com.musalasoft.drone.service.MedicationService;
import com.musalasoft.drone.utils.MedicationValidator;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
@Transactional
@RequiredArgsConstructor
public class MedicationServiceImpl  implements MedicationService {

	private final MedicationRepository medicationRepository;
	private final MedicationMapper medicationMapper;

	@Override
	public MedicationResponse registerMedication(MedicationDto medicationDto) {
		try {
			MedicationValidator.validateMedication(medicationDto);
			Medication medication = medicationMapper.mapToMedication(medicationDto);
			medicationRepository.save(medication);
			return medicationMapper.mapToMedicationResponse(medication);

		} catch (Exception e) {
			throw new CustomInternalServerException("Error creating medication: " + e.getMessage());
		}


	}

	@Override
	public List<MedicationResponse> getAllMedication() {
		try {
			List<MedicationResponse> medicationResponseList = medicationRepository.findAll().stream().map(medicationMapper::mapToMedicationResponse).collect(Collectors.toList());
			return medicationResponseList;
		} catch (Exception e) {
			throw new CustomInternalServerException("Error fetching medication list " + e.getMessage());
		}
	}
}