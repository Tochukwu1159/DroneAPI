package com.musalasoft.drone.service.impl;
import com.musalasoft.drone.enums.State;
import com.musalasoft.drone.exceptions.*;
import com.musalasoft.drone.models.BatteryHistory;
import com.musalasoft.drone.models.DeliveryDetails;
import com.musalasoft.drone.models.Drone;
import com.musalasoft.drone.models.Medication;
import com.musalasoft.drone.objectMapper.BatterHistoryMapper;
import com.musalasoft.drone.objectMapper.DroneMapper;
import com.musalasoft.drone.payloads.requests.DroneDeliveryDto;
import com.musalasoft.drone.payloads.requests.DroneDto;
import com.musalasoft.drone.payloads.responses.BatteryHistoryResponse;
import com.musalasoft.drone.payloads.responses.DroneResponse;
import com.musalasoft.drone.repositories.BatteryHistoryRepository;
import com.musalasoft.drone.repositories.DeliveryRepository;
import com.musalasoft.drone.repositories.DroneRepository;
import com.musalasoft.drone.repositories.MedicationRepository;
import com.musalasoft.drone.service.DroneService;
import com.musalasoft.drone.utils.DroneValidator;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;


import java.math.BigDecimal;
import java.util.List;
import java.util.logging.Logger;
import java.util.stream.Collectors;


@Service
@Transactional
@RequiredArgsConstructor
@Slf4j
public class DroneServiceImpl implements DroneService {
	private final BatteryHistoryRepository batteryHistoryRepository;
	private final DeliveryRepository deliveryRepository;

	private final DroneRepository droneRepository;
	private final MedicationRepository medicationRepository;

	private final DroneMapper droneMapper;

	private final BatterHistoryMapper batterHistoryMapper;

	static final Logger LOGGER = Logger.getLogger(DroneServiceImpl.class.getName());





	@Override
	public DroneResponse registerDrone(DroneDto droneDto) {
		try{
			DroneValidator.validateDrone(droneDto);
			Drone newDrone = droneMapper.mapToDrone(droneDto);
			newDrone.setState(State.IDLE);
			droneRepository.save(newDrone);
			System.out.println(newDrone);
			return droneMapper.mapToDroneResponse(newDrone);

		}catch (Exception e){
			throw  new CustomInternalServerException("Error creating drone "+ e.getMessage());
		}
	}

	@Override
	public List<DroneResponse> getAvailableDrones() {
		try {
			List<Drone> drones = droneRepository.findAllAvailableDrone();
			if (drones.isEmpty())
				throw new BatteryAndDroneStateException("No available drones") ;
			return drones.stream().map(droneMapper::mapToDroneResponse).collect(Collectors.toList());
		}catch (Exception e){
			throw new CustomInternalServerException("Error fetching available drones" + e.getMessage());
		}

	}

	@Override
	public List<DroneResponse> getAllDrones() {
		try{
			List<Drone> drones = droneRepository.findAll();
			return drones.stream().map(droneMapper::mapToDroneResponse).collect(Collectors.toList());
		}catch (Exception e){
			throw new CustomInternalServerException("Error fetching all drones" + e.getMessage());
		}
	}
@Override
public DroneResponse getADrone(String serialNo) {
	Drone drone = findDroneBySerialNumber(serialNo);
	return droneMapper.mapToDroneResponse(drone);
}

	@Override
	public DroneResponse loadDroneWithMedication(String droneSerialNo, String medCode) {
		Drone drone = findDroneByProvidedSerialNumber(droneSerialNo);
		Medication medication = findMedicationByCode(medCode);

		validateMedicationLoad(drone, medication);

		loadMedication(drone, medication);

		return droneMapper.mapToDroneResponse(drone);
	}

	private Drone findDroneByProvidedSerialNumber(String serialNo) {
		return droneRepository.findBySerialNumber(serialNo)
				.orElseThrow(() -> new InvalidDroneException("Drone with the serial number: " + serialNo + " was not found"));
	}

	private Medication findMedicationByCode(String medCode) {
		return medicationRepository.findByCode(medCode)
				.orElseThrow(() -> new MedicationNotFoundException("Medication with the supplied code was not found"));
	}

	private void validateMedicationLoad(Drone drone, Medication medication) {
		double currentTotalWeight = calculateCurrentTotalWeight(drone);
		double totalWeightAfterAdding = currentTotalWeight + medication.getWeight();
		if (totalWeightAfterAdding > drone.getWeightLimit()) {
			throw new DroneLimitException(String.format("Drone weight limit exceeded. Current weight is: %.2f, your medication weight is: %.2f, and maximum weight limit is %.2f",
					currentTotalWeight, medication.getWeight(), drone.getWeightLimit()));
		}
		if (drone.getMedication().contains(medication)) {
			throw new MedicineDetailsAlreadyExistException("Medication already loaded onto the drone");
		}
	}

	private double calculateCurrentTotalWeight(Drone drone) {
		return drone.getMedication().stream()
				.mapToDouble(Medication::getWeight)
				.sum();
	}

	private void loadMedication(Drone drone, Medication medication) {
		medication.setDrone(drone);
		medicationRepository.save(medication);
		drone.getMedication().add(medication);
		drone.setState(State.LOADED);
		droneRepository.save(drone);
	}





	@Override
	public String deliverMedication(DroneDeliveryDto droneDeliveryDto) {
		Drone drone = findDroneBySerialNumber(droneDeliveryDto.getSerialNumber());
		validateDroneState(drone);
		drone.setState(State.DELIVERING);
		droneRepository.save(drone);
		saveDeliveryDetails(droneDeliveryDto);
		return prepareSuccessMessage(droneDeliveryDto.getSerialNumber());
	}

	private Drone findDroneBySerialNumber(String serialNumber) {
		return droneRepository.findBySerialNumber(serialNumber)
				.orElseThrow(() -> new DroneNotFoundException("Drone Not found"));
	}

	private void validateDroneState(Drone drone) {
		if (!drone.getState().equals(State.LOADED)) {
			throw new DroneNotFoundException("Drone with serialNumber: " + drone.getSerialNumber() + " Not loaded");
		}
	}

	private void saveDeliveryDetails(DroneDeliveryDto droneDeliveryDto) {
		DeliveryDetails deliveryDetails = DeliveryDetails.builder()
				.serialNumber(droneDeliveryDto.getSerialNumber())
				.source(droneDeliveryDto.getSource())
				.destination(droneDeliveryDto.getDestination())
				.build();
		deliveryRepository.save(deliveryDetails);
	}

	private String prepareSuccessMessage(String serialNumber) {
		return "Drone with serial number " + serialNumber + " is out to deliver medication";
	}


	@Override
	@Scheduled(fixedRate = 200000) // Execute every 200 seconds
	public void checkBatteryLevelsAndSaveLogHistory() {
		List<Drone> drones = droneRepository.findAll();

		for (Drone drone : drones) {
			String logMessage = generateBatteryLogMessage(drone.getBatteryLevel());
			saveBatteryHistory(drone, logMessage);
			logBatteryLevel(drone, logMessage);
		}
	}

	private String generateBatteryLogMessage(BigDecimal batteryLevel) {
		if (batteryLevel.compareTo(BigDecimal.valueOf(50)) >= 0) {
			return "High Battery Level";
		} else if (batteryLevel.compareTo(BigDecimal.valueOf(25)) >= 0) {
			return "Mid Battery Level";
		} else {
			return "Low Battery Level";
		}
	}

	private void saveBatteryHistory(Drone drone, String logMessage) {
		BatteryHistory history = new BatteryHistory(drone.getBatteryLevel(), drone);
		batteryHistoryRepository.save(history);
	}

	private void logBatteryLevel(Drone drone, String logMessage) {
		log.info("{} for Drone {} is {}", logMessage, drone.getSerialNumber(), drone.getBatteryLevel());
	}

	@Override
	public List<BatteryHistoryResponse> getDroneHistory(String serialNumber) {
		List<BatteryHistory> batteryHistoryList = batteryHistoryRepository.findAllByDrone_SerialNumber(serialNumber);
		return batteryHistoryList.stream().map(batterHistoryMapper::mapToBatteryHistoryResponse).collect(Collectors.toList());
	}

}