package com.musalasoft.drone.objectMapper;
import com.musalasoft.drone.models.BatteryHistory;
import com.musalasoft.drone.payloads.responses.BatteryHistoryResponse;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class BatterHistoryMapper {
    private final ModelMapper modelMapper;



    public BatteryHistoryResponse mapToBatteryHistoryResponse(BatteryHistory batteryHistory) {
    return modelMapper.map(batteryHistory, BatteryHistoryResponse.class );
    }
}
