package com.musalasoft.drone.utils;

import com.musalasoft.drone.exceptions.InvalidMedicationException;
import com.musalasoft.drone.payloads.requests.MedicationDto;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

class MedicationValidatorTest {
    @Test
    void testValidateMedication() {
        MedicationDto medicationDto = new MedicationDto("U", 10.0d, "U", "U");

        MedicationValidator.validateMedication(medicationDto);
        assertEquals("U", medicationDto.getCode());
        assertEquals(10.0d, medicationDto.getWeight().doubleValue());
        assertEquals("U", medicationDto.getName());
        assertEquals("U", medicationDto.getImage());
    }

    @Test
    void testValidateMedication2() {
        MedicationDto medicationDto = mock(MedicationDto.class);
        when(medicationDto.getCode()).thenReturn("Code");
        when(medicationDto.getName()).thenReturn("Name");
        assertThrows(InvalidMedicationException.class, () -> MedicationValidator.validateMedication(medicationDto));
        verify(medicationDto).getCode();
        verify(medicationDto).getName();
    }

}

