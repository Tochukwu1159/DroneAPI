package com.musalasoft.drone.models;

import jakarta.persistence.*;
import lombok.*;
import lombok.extern.slf4j.Slf4j;
import org.antlr.v4.runtime.misc.NotNull;

@Entity
@Table(name = "delivery_details")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Slf4j
@Builder
public class DeliveryDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull
    private String serialNumber;
    @NotNull
    private String source;
    @NotNull
    private String destination;

    private String medicationCode;

}