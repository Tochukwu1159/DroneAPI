package com.musalasoft.drone.objectMapper;

import com.musalasoft.drone.models.Medication;
import com.musalasoft.drone.payloads.requests.MedicationDto;
import com.musalasoft.drone.payloads.responses.MedicationResponse;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class MedicationMapper {
    private final ModelMapper modelMapper;
    public Medication mapToMedication(MedicationDto driverRequest) {return modelMapper.map(driverRequest, Medication.class);
    }

    public MedicationResponse mapToMedicationResponse(Medication medication) {return modelMapper.map(medication, MedicationResponse.class);
    }
}
