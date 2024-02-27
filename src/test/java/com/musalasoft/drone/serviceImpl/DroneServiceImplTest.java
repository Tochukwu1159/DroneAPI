//package com.musalasoft.drone.serviceImpl;
//
//
//
//import com.musalasoft.drone.exceptions.DroneAlreadyRegisteredException;
//import com.musalasoft.drone.models.BatteryHistory;
//import com.musalasoft.drone.models.DeliveryDetails;
//import com.musalasoft.drone.models.Drone;
//import com.musalasoft.drone.repositories.BatteryHistoryRepository;
//import com.musalasoft.drone.repositories.DeliveryRepository;
//import com.musalasoft.drone.repositories.DroneRepository;
//import com.musalasoft.drone.service.impl.DroneServiceImpl;
//import org.junit.jupiter.api.Assertions;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.mockito.ArgumentMatchers;
//import org.mockito.Mockito;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.mock.mockito.MockBean;
//import org.springframework.test.context.ContextConfiguration;
//import org.springframework.test.context.junit.jupiter.SpringExtension;
//import static org.junit.jupiter.api.Assertions.assertEquals;
//
//
//import java.math.BigDecimal;
//import java.util.ArrayList;
//import java.util.Collection;
//import java.util.List;
//import java.util.Optional;
//
//@ContextConfiguration(classes = {DroneServiceImpl.class})
//@ExtendWith(SpringExtension.class)
//class DroneServiceImplTest {
//    @MockBean
//    private BatteryHistoryRepository batteryHistoryRepository;
//
//    @MockBean
//    private DeliveryRepository deliveryRepository;
//
//    @MockBean
//    private DroneRepository droneRepository;
//
//    @Autowired
//    private DroneServiceImpl droneServiceImpl;
//
//    @MockBean
//    private MedicationRepository medicationRepository;
//
//    @Test
//    void testRegisterDrone() {
//        Mockito.when(droneRepository.save((Drone) ArgumentMatchers.any())).thenReturn(new Drone());
//        Mockito.when(droneRepository.findBySerialNumber((String) ArgumentMatchers.any())).thenReturn(Optional.of(new Drone()));
//        Assertions.assertThrows(DroneAlreadyRegisteredException.class, () -> droneServiceImpl
//                .registerDrone(new DroneDto("42", DroneModel.LIGHTWEIGHT, BigDecimal.valueOf(42L), 500.0d)));
//        Mockito.verify(droneRepository).findBySerialNumber((String) ArgumentMatchers.any());
//    }
//
//    @Test
//    void testRegisterDrone2() {
//        Mockito.when(droneRepository.save((Drone) ArgumentMatchers.any())).thenReturn(new Drone());
//        Mockito.when(droneRepository.findBySerialNumber((String) ArgumentMatchers.any())).thenReturn(Optional.empty());
//        BigDecimal valueOfResult = BigDecimal.valueOf(42L);
//        ApiResponse<?> actualRegisterDroneResult = droneServiceImpl
//                .registerDrone(new DroneDto("42", DroneModel.LIGHTWEIGHT, valueOfResult, 500.0d));
//        Object data = actualRegisterDroneResult.getData();
//        Assertions.assertTrue(data instanceof Drone);
//        assertEquals("Drone registered successfully", actualRegisterDroneResult.getMessage());
//        BigDecimal batteryLevel = ((Drone) data).getBatteryLevel();
//        Assertions.assertSame(valueOfResult, batteryLevel);
//        assertEquals(500.0d, ((Drone) data).getWeightLimit());
//        Assertions.assertNull(((Drone) data).getMedication());
//        assertEquals(DroneModel.LIGHTWEIGHT, ((Drone) data).getModel());
//        assertEquals(DroneState.IDLE, ((Drone) data).getState());
//        assertEquals("42", ((Drone) data).getSerialNumber());
//        Assertions.assertEquals("42", batteryLevel.toString());
//        Mockito.verify(droneRepository).save((Drone) ArgumentMatchers.any());
//        Mockito.verify(droneRepository).findBySerialNumber((String) ArgumentMatchers.any());
//    }
//
//    @Test
//    void testLoadDrone() {
//        Mockito.when(droneRepository.findBySerialNumber((String) ArgumentMatchers.any())).thenReturn(Optional.of(new Drone()));
//        Mockito.when(medicationRepository.findByCode((String) ArgumentMatchers.any()))
//                .thenThrow(new DroneAlreadyRegisteredException("An error occurred"));
//        Assertions.assertThrows(DroneAlreadyRegisteredException.class,
//                () -> droneServiceImpl.loadDrone("Drone Serial No", "Med Code"));
//        Mockito.verify(droneRepository).findBySerialNumber((String) ArgumentMatchers.any());
//        Mockito.verify(medicationRepository).findByCode((String) ArgumentMatchers.any());
//    }
//
//    @Test
//    void testLoadDrone2() {
//        Mockito.when(droneRepository.save((Drone) ArgumentMatchers.any())).thenReturn(new Drone());
//        BigDecimal batteryLevel = BigDecimal.valueOf(42L);
//        Drone drone = new Drone("42", 10.0d, DroneModel.LIGHTWEIGHT, DroneState.IDLE, batteryLevel, new ArrayList<>());
//
//        Mockito.when(droneRepository.findBySerialNumber((String) ArgumentMatchers.any())).thenReturn(Optional.of(drone));
//        Mockito.when(medicationRepository.save((Medication) ArgumentMatchers.any())).thenReturn(new Medication("Name", 10.0d, "Code", "Image"));
//        Mockito.when(medicationRepository.findByCode((String) ArgumentMatchers.any()))
//                .thenReturn(Optional.of(new Medication("Name", 10.0d, "Code", "Image")));
//        ApiResponse<Drone> actualLoadDroneResult = droneServiceImpl.loadDrone("Drone Serial No", "Med Code");
//        Drone data = actualLoadDroneResult.getData();
//        Assertions.assertSame(drone, data);
//        assertEquals("Medication loaded successfully", actualLoadDroneResult.getMessage());
//        assertEquals(DroneState.LOADED, data.getState());
//        assertEquals(1, data.getMedication().size());
//        assertEquals("42", data.getBatteryLevel().toString());
//        Mockito.verify(droneRepository).save((Drone) ArgumentMatchers.any());
//        Mockito.verify(droneRepository).findBySerialNumber((String) ArgumentMatchers.any());
//        Mockito.verify(medicationRepository).save((Medication) ArgumentMatchers.any());
//        Mockito.verify(medicationRepository).findByCode((String) ArgumentMatchers.any());
//    }
//
//    @Test
//    void testGetLoadedMedication() {
//        BigDecimal batteryLevel = BigDecimal.valueOf(42L);
//        Mockito.when(droneRepository.findBySerialNumber((String) ArgumentMatchers.any())).thenReturn(Optional
//                .of(new Drone("42", 10.0d, DroneModel.LIGHTWEIGHT, DroneState.IDLE, batteryLevel, new ArrayList<>())));
//        ApiResponse<List<Medication>> actualLoadedMedication = droneServiceImpl.getLoadedMedication("Serial No");
//        assertTrue(actualLoadedMedication.getData().isEmpty());
//        assertEquals("success", actualLoadedMedication.getMessage());
//        Mockito.verify(droneRepository).findBySerialNumber((String) ArgumentMatchers.any());
//    }
//
//    @Test
//    void testGetLoadedMedication2() {
//        Mockito.when(droneRepository.findBySerialNumber((String) ArgumentMatchers.any())).thenReturn(Optional.empty());
//        Assertions.assertThrows(DroneNotFoundException.class, () -> droneServiceImpl.getLoadedMedication("Serial No"));
//        Mockito.verify(droneRepository).findBySerialNumber((String) ArgumentMatchers.any());
//    }
//
//    @Test
//    void testGetAvailableDrones() {
//        Mockito.when(droneRepository.findAllAvailableDrone()).thenReturn(new ArrayList<>());
//        Assertions.assertThrows(BatteryAndDroneStateException.class, () -> droneServiceImpl.getAvailableDrones());
//        Mockito.verify(droneRepository).findAllAvailableDrone();
//    }
//
//    @Test
//    void testGetAvailableDrones2() {
//        ArrayList<Drone> droneList = new ArrayList<>();
//        droneList.add(new Drone());
//        Mockito.when(droneRepository.findAllAvailableDrone()).thenReturn(droneList);
//        ApiResponse<?> actualAvailableDrones = droneServiceImpl.getAvailableDrones();
//        Assertions.assertEquals(1, ((Collection<Drone>) actualAvailableDrones.getData()).size());
//        assertEquals("Here are List of available drones", actualAvailableDrones.getMessage());
//        Mockito.verify(droneRepository).findAllAvailableDrone();
//    }
//
//
//    @Test
//    void testCheckBatteryLevel() {
//        Mockito.when(droneRepository.findBySerialNumber((String) ArgumentMatchers.any())).thenReturn(Optional.of(new Drone()));
//        ApiResponse<?> actualCheckBatteryLevelResult = droneServiceImpl.checkBatteryLevel("42");
//        Assertions.assertNull(actualCheckBatteryLevelResult.getData());
//        assertEquals("Drone with serialNumber 42 has a battery level of:", actualCheckBatteryLevelResult.getMessage());
//        Mockito.verify(droneRepository).findBySerialNumber((String) ArgumentMatchers.any());
//    }
//
//    @Test
//    void testGetAllDrones() {
//        Mockito.when(droneRepository.findAll()).thenReturn(new ArrayList<>());
//        ApiResponse<?> actualAllDrones = droneServiceImpl.getAllDrones();
//        Assertions.assertTrue(((Collection<Object>) actualAllDrones.getData()).isEmpty());
//        assertEquals("success", actualAllDrones.getMessage());
//        Mockito.verify(droneRepository).findAll();
//    }
//
//    @Test
//    void testGetADrone() {
//        Drone drone = new Drone();
//        Mockito.when(droneRepository.findBySerialNumber((String) ArgumentMatchers.any())).thenReturn(Optional.of(drone));
//        ApiResponse<Drone> actualADrone = droneServiceImpl.getADrone("Serial No");
//        Assertions.assertSame(drone, actualADrone.getData());
//        assertEquals("success", actualADrone.getMessage());
//        Mockito.verify(droneRepository).findBySerialNumber((String) ArgumentMatchers.any());
//    }
//
//    @Test
//    void testDeliverMedication() {
//        Drone drone = Mockito.mock(Drone.class);
//        Mockito.doNothing().when(drone).setState((DroneState) ArgumentMatchers.any());
//        Mockito.when(drone.getState()).thenReturn(DroneState.LOADED);
//        Optional<Drone> ofResult = Optional.of(drone);
//        Mockito.when(droneRepository.save((Drone) ArgumentMatchers.any())).thenReturn(new Drone());
//        Mockito.when(droneRepository.findBySerialNumber((String) ArgumentMatchers.any())).thenReturn(ofResult);
//        Mockito.when(deliveryRepository.save((DeliveryDetails) ArgumentMatchers.any()))
//                .thenReturn(new DeliveryDetails("42", "Source", "Destination", "Medication Code"));
//        ApiResponse<String> actualDeliverMedicationResult = droneServiceImpl
//                .deliverMedication(new DroneDeliveryDto("42", "Source", "Destination"));
//        assertEquals("Drone with serial number 42 is out to deliver medication", actualDeliverMedicationResult.getData());
//        assertEquals("delivery in progress", actualDeliverMedicationResult.getMessage());
//        Mockito.verify(droneRepository).save((Drone) ArgumentMatchers.any());
//        Mockito.verify(droneRepository).findBySerialNumber((String) ArgumentMatchers.any());
//        Mockito.verify(drone).getState();
//        Mockito.verify(drone).setState((DroneState) ArgumentMatchers.any());
//        Mockito.verify(deliveryRepository).save((DeliveryDetails) ArgumentMatchers.any());
//    }
//    @Test
//    void testGetDroneHistory() {
//        Mockito.when(batteryHistoryRepository.findAllByDrone_SerialNumber((String) ArgumentMatchers.any())).thenReturn(new ArrayList<>());
//        ApiResponse<List<BatteryHistory>> actualDroneHistory = droneServiceImpl.getDroneHistory("42");
//        assertTrue(actualDroneHistory.getData().isEmpty());
//        assertEquals("Battery history for drone with serialNumber: 42", actualDroneHistory.getMessage());
//        Mockito.verify(batteryHistoryRepository).findAllByDrone_SerialNumber((String) ArgumentMatchers.any());
//    }
//
//    @Test
//    void testCheckBatteryLevelsAndSaveLogHistory() {
//        Mockito.when(droneRepository.findAll()).thenThrow(new DroneAlreadyRegisteredException("An error occurred"));
//        Assertions.assertThrows(DroneAlreadyRegisteredException.class, () -> droneServiceImpl.checkBatteryLevelsAndSaveLogHistory());
//        Mockito.verify(droneRepository).findAll();
//    }
//
//    @Test
//    void testCheckBatteryLevelsAndSaveLogHistory2() {
//        ArrayList<Drone> droneList = new ArrayList<>();
//        BigDecimal batteryLevel = BigDecimal.valueOf(42L);
//        droneList.add(new Drone("42", 10.0d, DroneModel.LIGHTWEIGHT, DroneState.IDLE, batteryLevel, new ArrayList<>()));
//        Mockito.when(droneRepository.findAll()).thenReturn(droneList);
//        Mockito.when(batteryHistoryRepository.save((BatteryHistory) ArgumentMatchers.any())).thenReturn(new BatteryHistory());
//        droneServiceImpl.checkBatteryLevelsAndSaveLogHistory();
//        Mockito.verify(droneRepository).findAll();
//        Mockito.verify(batteryHistoryRepository).save((BatteryHistory) ArgumentMatchers.any());
//    }
//
//}
//
