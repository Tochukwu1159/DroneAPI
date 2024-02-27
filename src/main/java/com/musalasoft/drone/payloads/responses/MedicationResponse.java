package com.musalasoft.drone.payloads.responses;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MedicationResponse {
    @NotNull
     private String name;
    @NotNull
    private Double weight;
    @NotNull
    private String code;
    @NotNull
    private String image;

}
