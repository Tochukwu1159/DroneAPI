package com.musalasoft.drone.models;

import jakarta.validation.constraints.Pattern;
import lombok.*;

import com.musalasoft.drone.enums.Model;
import com.musalasoft.drone.enums.State;
import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name="medication")
public class Medication {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(unique = true, nullable = false)
	@Pattern(
			regexp = "[A-Z0-9_]+",
			message = "only upper case letters, underscore, and numbers allowed"
	)
	private String code;

	@Column(nullable = false)
	@Pattern(
		regexp = "[a-zA-Z_0-9-]+",
		message = "only letters, numbers, underscore and hyphen allowed"
	)
	private String name;

	@Column
	private String image;

	@Column (nullable = false)
	private double weight;

	@ManyToOne  (fetch = FetchType.LAZY)
	@JoinColumn(name = "drone_id")
	private Drone drone;
}