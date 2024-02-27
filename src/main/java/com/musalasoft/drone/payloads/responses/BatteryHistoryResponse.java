package com.musalasoft.drone.payloads.responses;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.math.BigDecimal;
import java.time.LocalDateTime;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class BatteryHistoryResponse {
    private Long id;
    private BigDecimal batteryLevel;
    @CreationTimestamp
    private LocalDateTime timestamp;
}
