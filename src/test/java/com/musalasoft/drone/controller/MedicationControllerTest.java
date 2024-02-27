//package com.musalasoft.drone.controller;
//
//import com.musalasoft.drone.controllers.MedicationController;
//import com.musalasoft.drone.payloads.requests.MedicationDto;
//import com.musalasoft.drone.repositories.MedicationRepository;
//import com.musalasoft.drone.service.MedicationService;
//import com.musalasoft.drone.service.impl.MedicationServiceImpl;
//import org.junit.jupiter.api.Assertions;
//import org.junit.jupiter.api.Test;
//import org.mockito.ArgumentMatchers;
//import org.mockito.Mockito;
//import org.springframework.http.ResponseEntity;
//
//import java.util.ArrayList;
//import java.util.Collection;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.junit.jupiter.api.Assertions.assertTrue;
//import static org.mockito.Mockito.*;
//
//class MedicationControllerTest {
//
//    @Test
//    void testSaveMedicineDetails() {
//        MedicationService medicationService = Mockito.mock(MedicationService.class);
//        Mockito.when(medicationService.createMedication(ArgumentMatchers.any(MedicationDto.class))).thenReturn(new ApiResponse<>());
//
//        MedicationController medicationController = new MedicationController(medicationService);
//        ResponseEntity<?> result = medicationController.saveMedicineDetails(new MedicationDto());
//        Assertions.assertTrue(result.hasBody());
//        Assertions.assertEquals(201, result.getStatusCodeValue());
//        Assertions.assertTrue(result.getHeaders().isEmpty());
//        Mockito.verify(medicationService).createMedication(ArgumentMatchers.any(MedicationDto.class));
//    }
//
//    @Test
//    void testSaveMedicineDetails3() {
//        MedicationServiceImpl medicationServiceImpl = mock(MedicationServiceImpl.class);
//        when(medicationServiceImpl.createMedication((MedicationDto) any())).thenReturn(new ApiResponse<>());
//        MedicationController medicationController = new MedicationController(medicationServiceImpl);
//        ResponseEntity<?> actualSaveMedicineDetailsResult = medicationController.saveMedicineDetails(new MedicationDto());
//        assertTrue(actualSaveMedicineDetailsResult.hasBody());
//        assertEquals(201, actualSaveMedicineDetailsResult.getStatusCodeValue());
//        assertTrue(actualSaveMedicineDetailsResult.getHeaders().isEmpty());
//        verify(medicationServiceImpl).createMedication((MedicationDto) any());
//    }
//
//    @Test
//    void testGetAllMedication() {
//        MedicationRepository medicationRepository = Mockito.mock(MedicationRepository.class);
//        Mockito.when(medicationRepository.findAll()).thenReturn(new ArrayList<>());
//
//        MedicationController medicationController = new MedicationController(new MedicationServiceImpl(medicationRepository));
//
//        ResponseEntity<?> result = medicationController.getAllMedication();
//        Assertions.assertTrue(result.hasBody());
//        Assertions.assertEquals(200, result.getStatusCodeValue());
//        Assertions.assertTrue(result.getHeaders().isEmpty());
//        Assertions.assertTrue(((Collection<Object>) ((ApiResponse<Object>) result.getBody()).getData()).isEmpty());
//        assertEquals("success", ((ApiResponse<Object>) result.getBody()).getMessage());
//        Mockito.verify(medicationRepository).findAll();
//    }
//
//    @Test
//    void testGetAllMedication2() {
//        MedicationRepository medicationRepository = mock(MedicationRepository.class);
//        when(medicationRepository.findAll()).thenReturn(new ArrayList<>());
//        ResponseEntity<?> actualAllMedication = (new MedicationController(
//                new MedicationServiceImpl(medicationRepository))).getAllMedication();
//        assertTrue(actualAllMedication.hasBody());
//        assertEquals(200, actualAllMedication.getStatusCodeValue());
//        assertTrue(actualAllMedication.getHeaders().isEmpty());
//        assertTrue(((Collection<Object>) ((ApiResponse<Object>) actualAllMedication.getBody()).getData()).isEmpty());
//        assertEquals("success", ((ApiResponse<Object>) actualAllMedication.getBody()).getMessage());
//        verify(medicationRepository).findAll();
//    }
//
//}
