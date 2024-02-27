//package com.musalasoft.drone.controller;
//
//
//import com.musalasoft.drone.controllers.DroneController;
//import com.musalasoft.drone.enums.Model;
//import com.musalasoft.drone.enums.State;
//import com.musalasoft.drone.models.Drone;
//import com.musalasoft.drone.models.Medication;
//import com.musalasoft.drone.payloads.requests.DroneDeliveryDto;
//import com.musalasoft.drone.payloads.requests.DroneDto;
//import com.musalasoft.drone.repositories.BatteryHistoryRepository;
//import com.musalasoft.drone.repositories.DroneRepository;
//import com.musalasoft.drone.repositories.MedicationRepository;
//import com.musalasoft.drone.service.DroneService;
//import com.musalasoft.drone.service.impl.DroneServiceImpl;
//import org.junit.jupiter.api.Assertions;
//import org.junit.jupiter.api.Test;
//import org.mockito.ArgumentMatchers;
//import org.mockito.Mockito;
//import org.springframework.http.ResponseEntity;
//
//import java.math.BigDecimal;
//import java.util.ArrayList;
//import java.util.Collection;
//import java.util.List;
//import java.util.Optional;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//
//class DroneControllerTest {
//
//    @Test
//    void testRegisterDrone() {
//        DroneServiceImpl droneService = Mockito.mock(DroneServiceImpl.class);
//        Mockito.when(droneService.registerDrone(ArgumentMatchers.any(DroneDto.class))).thenReturn(new ApiResponse<>());
//        DroneController droneController = new DroneController(droneService);
//        ResponseEntity<?> result = droneController.registerDrone(new DroneDto());
//        Assertions.assertTrue(result.hasBody());
//        assertEquals(201, result.getStatusCodeValue());
//        Assertions.assertTrue(result.getHeaders().isEmpty());
//        Mockito.verify(droneService).registerDrone(ArgumentMatchers.any(DroneDto.class));
//    }
//
//    @Test
//    void testLoadingDrone() {
//        DroneRepository droneRepository = Mockito.mock(DroneRepository.class);
//        MedicationRepository medicationRepository = Mockito.mock(MedicationRepository.class);
//        Mockito.when(medicationRepository.save(ArgumentMatchers.any(Medication.class))).thenReturn(new Medication("Name", 10.0d, "Code", "Image"));
//        Mockito.when(medicationRepository.findByCode(ArgumentMatchers.any(String.class))).thenReturn(Optional.of(new Medication("Name", 10.0d, "Code", "Image")));
//
//        Drone drone = new Drone("42", 10.0d, Model.LIGHTWEIGHT, State.IDLE, BigDecimal.valueOf(42L), new ArrayList<>());
//        Mockito.when(droneRepository.findBySerialNumber(ArgumentMatchers.any(String.class))).thenReturn(Optional.of(drone));
//
//        DroneController droneController = new DroneController(new DroneServiceImpl(droneRepository, medicationRepository, null, null));
//
//        ResponseEntity<?> result = droneController.loadingDrone("42", "Code");
//        Assertions.assertTrue(result.hasBody());
//        assertEquals(200, result.getStatusCodeValue());
//        Assertions.assertTrue(result.getHeaders().isEmpty());
//        Object data = ((ApiResponse<Object>) result.getBody()).getData();
//        assertEquals(drone, data);
//        assertEquals("Medication loaded successfully", ((ApiResponse<Object>) result.getBody()).getMessage());
//        Mockito.verify(droneRepository).save(ArgumentMatchers.any(Drone.class));
//        Mockito.verify(droneRepository).findBySerialNumber(ArgumentMatchers.any(String.class));
//        Mockito.verify(medicationRepository).save(ArgumentMatchers.any(Medication.class));
//        Mockito.verify(medicationRepository).findByCode(ArgumentMatchers.any(String.class));
//    }
//
//    @Test
//    void testGetLoadedMedication() {
//        DroneRepository droneRepository = Mockito.mock(DroneRepository.class);
//        BigDecimal batteryLevel = BigDecimal.valueOf(42L);
//        Mockito.when(droneRepository.findBySerialNumber(ArgumentMatchers.any(String.class))).thenReturn(Optional.of(new Drone("42", 10.0d, DroneModel.LIGHTWEIGHT, DroneState.IDLE, batteryLevel, new ArrayList<>())));
//
//        DroneController droneController = new DroneController(new DroneServiceImpl(droneRepository, null, null, null));
//
//        ResponseEntity<?> result = droneController.getLoadedMedication("Serial No");
//        Assertions.assertTrue(result.hasBody());
//        assertEquals(200, result.getStatusCodeValue());
//        Assertions.assertTrue(result.getHeaders().isEmpty());
//        Assertions.assertTrue(((Collection<Object>) ((ApiResponse<Object>) result.getBody()).getData()).isEmpty());
//        assertEquals("success", ((ApiResponse<Object>) result.getBody()).getMessage());
//        Mockito.verify(droneRepository).findBySerialNumber(ArgumentMatchers.any(String.class));
//    }
//
//    @Test
//    void testAvailableDrone() {
//        List<Drone> droneList = new ArrayList<>();
//        droneList.add(new Drone());
//        DroneRepository droneRepository = Mockito.mock(DroneRepository.class);
//        Mockito.when(droneRepository.findAllAvailableDrone()).thenReturn(droneList);
//
//        DroneController droneController = new DroneController(new DroneServiceImpl(droneRepository, null, null, null));
//
//        ResponseEntity<?> result = droneController.AvailableDrone();
//        Assertions.assertTrue(result.hasBody());
//        assertEquals(200, result.getStatusCodeValue());
//        Assertions.assertTrue(result.getHeaders().isEmpty());
//        assertEquals(1, ((Collection<Drone>) ((ApiResponse<Object>) result.getBody()).getData()).size());
//        assertEquals("Here are List of available drones", ((ApiResponse<Object>) result.getBody()).getMessage());
//        Mockito.verify(droneRepository).findAllAvailableDrone();
//    }
//
//    @Test
//    void testBatteryLevelOfADrone() {
//        DroneRepository droneRepository = Mockito.mock(DroneRepository.class);
//        Mockito.when(droneRepository.findBySerialNumber(ArgumentMatchers.any(String.class))).thenReturn(Optional.of(new Drone()));
//
//        DroneController droneController = new DroneController(new DroneServiceImpl(droneRepository, null, null, null));
//
//        ResponseEntity<?> result = droneController.batteryLevelOfADrone("Serial No");
//        Assertions.assertTrue(result.hasBody());
//        assertEquals(200, result.getStatusCodeValue());
//        Assertions.assertTrue(result.getHeaders().isEmpty());
//        Assertions.assertNull(((ApiResponse<Object>) result.getBody()).getData());
//        assertEquals("Drone with serialNumber Serial No has a battery level of:", ((ApiResponse<Object>) result.getBody()).getMessage());
//        Mockito.verify(droneRepository).findBySerialNumber(ArgumentMatchers.any(String.class));
//    }
//
//    @Test
//    void testGetAllDrones() {
//        DroneRepository droneRepository = Mockito.mock(DroneRepository.class);
//        Mockito.when(droneRepository.findAll()).thenReturn(new ArrayList<>());
//
//        DroneController droneController = new DroneController(new DroneServiceImpl(droneRepository, null, null, null));
//
//        ResponseEntity<?> result = droneController.getAllDrones();
//        Assertions.assertTrue(result.hasBody());
//        assertEquals(200, result.getStatusCodeValue());
//        Assertions.assertTrue(result.getHeaders().isEmpty());
//        Assertions.assertTrue(((Collection<Object>) ((ApiResponse<Object>) result.getBody()).getData()).isEmpty());
//        assertEquals("success", ((ApiResponse<Object>) result.getBody()).getMessage());
//        Mockito.verify(droneRepository).findAll();
//    }
//
//    @Test
//    void testFindAnIdleDrone() {
//        DroneRepository droneRepository = Mockito.mock(DroneRepository.class);
//        Drone drone = new Drone();
//        Mockito.when(droneRepository.findBySerialNumber(ArgumentMatchers.any(String.class))).thenReturn(Optional.of(drone));
//
//        DroneController droneController = new DroneController(new DroneServiceImpl(droneRepository, null, null, null));
//
//        ResponseEntity<?> result = droneController.findAnIdleDrone("42");
//        Assertions.assertTrue(result.hasBody());
//        assertEquals(200, result.getStatusCodeValue());
//        Assertions.assertTrue(result.getHeaders().isEmpty());
//        Assertions.assertSame(drone, ((ApiResponse<Object>) result.getBody()).getData());
//        assertEquals("success", ((ApiResponse<Object>) result.getBody()).getMessage());
//        Mockito.verify(droneRepository).findBySerialNumber(ArgumentMatchers.any(String.class));
//    }
//
//    @Test
//    void testGetBatteryLogForDrone() {
//        BatteryHistoryRepository batteryHistoryRepository = Mockito.mock(BatteryHistoryRepository.class);
//        Mockito.when(batteryHistoryRepository.findAllByDrone_SerialNumber(ArgumentMatchers.any(String.class))).thenReturn(new ArrayList<>());
//
//        DroneController droneController = new DroneController(new DroneServiceImpl(null, null, null, batteryHistoryRepository));
//
//        ResponseEntity<?> result = droneController.getBatteryLogForDrone("42");
//        Assertions.assertTrue(result.hasBody());
//        assertEquals(200, result.getStatusCodeValue());
//        Assertions.assertTrue(result.getHeaders().isEmpty());
//        Assertions.assertTrue(((Collection<Object>) ((ApiResponse<Object>) result.getBody()).getData()).isEmpty());
//        assertEquals("Battery history for drone with serialNumber: 42", ((ApiResponse<Object>) result.getBody()).getMessage());
//        Mockito.verify(batteryHistoryRepository).findAllByDrone_SerialNumber(ArgumentMatchers.any(String.class));
//    }
//
//    @Test
//    void testDeliverMedication() {
//        DroneService droneService = Mockito.mock(DroneServiceImpl.class);
//        Mockito.when(droneService.deliverMedication(ArgumentMatchers.any(DroneDeliveryDto.class))).thenReturn(new ApiResponse<>());
//        DroneController droneController = new DroneController(droneService);
//
//        ResponseEntity<?> result = droneController.DeliverMedication(new DroneDeliveryDto("42", "Source", "Destination"));
//        Assertions.assertTrue(result.hasBody());
//        assertEquals(200, result.getStatusCodeValue());
//        Assertions.assertTrue(result.getHeaders().isEmpty());
//        Mockito.verify(droneService).deliverMedication(ArgumentMatchers.any(DroneDeliveryDto.class));
//    }
//}
