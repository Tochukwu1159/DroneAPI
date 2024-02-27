package com.musalasoft.drone.utils;

import com.musalasoft.drone.exceptions.InvalidMedicationException;
import com.musalasoft.drone.payloads.requests.MedicationDto;
import org.springframework.stereotype.Component;

import java.util.regex.Pattern;

@Component
public class MedicationValidator {

    private static final String NAME_REGEX = "^[a-zA-Z0-9-_]+$";
    private static final String CODE_REGEX = "^[A-Z0-9_]+$";
    private static final Pattern NAME_PATTERN = Pattern.compile(NAME_REGEX);
    private static final Pattern CODE_PATTERN = Pattern.compile(CODE_REGEX);

    public static void validateMedication(MedicationDto medicationDto) {
        if (!isValidMedication(medicationDto)) {
            throw new InvalidMedicationException("Invalid medication");
        }
    }

    private static boolean isValidMedication(MedicationDto medicationDto) {
        return isValidName(medicationDto.getName()) && isValidCode(medicationDto.getCode());
    }

    private static boolean isValidName(String name) {
        return NAME_PATTERN.matcher(name).matches();
    }

    private static boolean isValidCode(String code) {
        return CODE_PATTERN.matcher(code).matches();
    }
}

