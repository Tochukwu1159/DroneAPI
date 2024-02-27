package com.musalasoft.drone.payloads.requests;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;


@Getter
@NoArgsConstructor
@AllArgsConstructor
public class MedicationDto {
	@NotNull(message = "Name cannot be Null")
	@Pattern(regexp = "^[a-zA-Z0-9-_]+$", message = "Invalid name format. Accepts only Uppercase Letters,numbers, - and _")
	private String name;
	@NotNull
	private Double weight;
	@NotNull
	@Pattern(regexp = "^[A-Z0-9_]+$", message = "Invalid code format. Accepts only Uppercase Letters,numbers and underscore")
	private String code;
	@NotNull
	private String image;
}