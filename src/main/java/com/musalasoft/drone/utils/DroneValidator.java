package com.musalasoft.drone.utils;

import com.musalasoft.drone.exceptions.InvalidDroneException;
import com.musalasoft.drone.payloads.requests.DroneDto;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class DroneValidator {

    private static final BigDecimal MAX_BATTERY_LEVEL = new BigDecimal("100");
    private static final BigDecimal MAX_WEIGHT_LIMIT = BigDecimal.valueOf(500);
    private static final int MAX_SERIAL_NUMBER_LENGTH = 100;

    public static void validateDrone(DroneDto droneDto) {
        if (!isWeightLimitValid(droneDto.getWeightLimit())) {
            throw new InvalidDroneException(" Maximum weight cannot exceed 500 grams");
        }
        if (!isBatteryValid(droneDto.getBatteryLevel())) {
            throw new InvalidDroneException("Battery level cannot exceed 100");
        }
        if (!isSerialNumberValid(droneDto.getSerialNumber())) {
            throw new InvalidDroneException("Length of Serial Number cannot exceed 100");
        }
    }

    private static boolean isBatteryValid(BigDecimal batteryLevel) {
        return batteryLevel.compareTo(BigDecimal.ZERO) >= 0 && batteryLevel.compareTo(MAX_BATTERY_LEVEL) <= 0;
    }

    private static boolean isSerialNumberValid(String serialNumber) {
        return serialNumber.length() <= MAX_SERIAL_NUMBER_LENGTH;
    }

    private static boolean isWeightLimitValid(double weightLimit) {
        return weightLimit <= MAX_WEIGHT_LIMIT.doubleValue();
    }
}

