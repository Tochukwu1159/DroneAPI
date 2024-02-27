package com.musalasoft.drone.payloads.requests;


import com.musalasoft.drone.enums.Model;
import com.musalasoft.drone.enums.State;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DroneDto {

	@NotNull
	@Size(min =  1, max =  100)
	private String serialNumber;
	@NotNull
	@Enumerated(EnumType.STRING)
	private Model model;
	@NotNull
	private BigDecimal batteryLevel;
	@NotNull
	private double weightLimit;
}