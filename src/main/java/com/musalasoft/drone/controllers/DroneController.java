package com.musalasoft.drone.controllers;
import com.musalasoft.drone.payloads.requests.DroneDeliveryDto;
import com.musalasoft.drone.payloads.requests.DroneDto;
import com.musalasoft.drone.payloads.responses.BatteryHistoryResponse;
import com.musalasoft.drone.payloads.responses.DroneResponse;
import com.musalasoft.drone.service.DroneService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import java.util.List;

import static org.springframework.http.HttpStatus.OK;

@Validated
@RestController
@RequestMapping("/api/v1/drone")
@RequiredArgsConstructor
public class DroneController {

	private final DroneService droneService;

	@GetMapping
	public ResponseEntity<List<DroneResponse>> getAllDrone() {
		return new ResponseEntity<>(droneService.getAllDrones(), HttpStatus.OK);
	}
	@GetMapping("/{serialNo}")
	public ResponseEntity<DroneResponse> getADrone(@PathVariable String serialNo){
		return new ResponseEntity<>(droneService.getADrone(serialNo), HttpStatus.OK);
	}

	@PostMapping("/droneDelivery")
	public ResponseEntity<String> DeliverMedication( @RequestBody DroneDeliveryDto dto){
		return new ResponseEntity<>(droneService.deliverMedication(dto), HttpStatus.OK);
	}


	@GetMapping("/battery-history/{drone_no}")
	public ResponseEntity<List<BatteryHistoryResponse>> getDroneHistory(@PathVariable(value="drone_no") String serialNumber) {
		return new ResponseEntity<>(droneService.getDroneHistory(serialNumber), OK);
	}


	@PostMapping("/register")
	public ResponseEntity<DroneResponse> registerDrone(@RequestBody @Valid DroneDto droneDto) {
		return new ResponseEntity<>(droneService.registerDrone(droneDto), HttpStatus.CREATED);
	}
	@GetMapping("/availableDrones")
	public ResponseEntity<List<DroneResponse>> getAvailableDrones(){
		return new ResponseEntity<>(droneService.getAvailableDrones(), HttpStatus.OK);
	}

	@PostMapping("/load-drone/{drone-serial-number}/{med-code}")
	public ResponseEntity<?> loadDroneWithMedication(@PathVariable(value="drone-serial-number") String serialNumber, @PathVariable(value="med-code")String code){
		return new ResponseEntity<>(droneService.loadDroneWithMedication(serialNumber, code), HttpStatus.OK);
	}

}