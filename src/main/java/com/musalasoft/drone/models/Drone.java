package com.musalasoft.drone.models;

import com.musalasoft.drone.enums.Model;
import com.musalasoft.drone.enums.State;
import jakarta.persistence.*;
import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.math.BigDecimal;
import java.util.List;


@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "drone")
public class Drone {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(unique = true)
	@Size(max = 100, message = "Serial number should not exceed 100 characters")
	private String serialNumber;
	@DecimalMax(value = "500", message =" Drone cannot carry more than {value} grams")
	@Min(0)
	@Max(500)
	private double weightLimit;

	@Enumerated(EnumType.STRING)
	private Model model;

	@Enumerated(EnumType.STRING)
	private State state;
	@DecimalMax(value = "100", message = "Battery cannot be more than 100%")
	private BigDecimal batteryLevel;

	@OneToMany(mappedBy = "drone")
//    @JsonIgnore
	private List<Medication> medication;

}