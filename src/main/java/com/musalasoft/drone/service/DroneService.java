package com.musalasoft.drone.service;

import com.musalasoft.drone.payloads.requests.DroneDeliveryDto;
import com.musalasoft.drone.payloads.requests.DroneDto;
import com.musalasoft.drone.payloads.responses.BatteryHistoryResponse;
import com.musalasoft.drone.payloads.responses.DroneResponse;

import java.util.List;

public interface DroneService {

	List<DroneResponse>  getAllDrones();
	DroneResponse registerDrone(DroneDto droneDto);
	List<DroneResponse> getAvailableDrones();
	DroneResponse getADrone(String serialNo);

	String deliverMedication(DroneDeliveryDto droneDeliveryDto);

	void checkBatteryLevelsAndSaveLogHistory();
	DroneResponse loadDroneWithMedication(String droneSerialNo, String medCode);

	List<BatteryHistoryResponse> getDroneHistory(String serialNumber);

}