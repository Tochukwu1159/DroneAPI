//package com.musalasoft.drone.serviceImpl;
//
//
//import com.musalasoft.drone.exceptions.InvalidMedicationException;
//import com.musalasoft.drone.exceptions.MedicineDetailsAlreadyExistException;
//import com.musalasoft.drone.models.Medication;
//import com.musalasoft.drone.objectMapper.MedicationMapper;
//import com.musalasoft.drone.payloads.requests.MedicationDto;
//import com.musalasoft.drone.payloads.responses.MedicationResponse;
//import com.musalasoft.drone.repositories.MedicationRepository;
//import com.musalasoft.drone.service.impl.MedicationServiceImpl;
//import org.junit.Before;
//import org.junit.jupiter.api.Assertions;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.Mockito;
//import org.mockito.MockitoAnnotations;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.mock.mockito.MockBean;
//import org.springframework.test.context.ContextConfiguration;
//import org.springframework.test.context.junit.*;
//import org.springframework.test.context.junit.jupiter.SpringExtension;
//
//import java.util.ArrayList;
//import java.util.Collection;
//import java.util.List;
//import java.util.Optional;
//
//
//import static org.junit.jupiter.api.Assertions.*;
//import static org.mockito.Mockito.*;
//
//@ContextConfiguration(classes = {MedicationServiceImpl.class})
//@ExtendWith(SpringExtension.class)
//public class MedicationServiceImplTest {
//
//    @Mock
//    private MedicationRepository medicationRepository;
//
//    @Mock
//    private MedicationMapper medicationMapper;
//
//    @InjectMocks
//    private MedicationServiceImpl medicationService;
//
//    @Before
//    public void setUp() {
//        MockitoAnnotations.initMocks(this);
//    }
//
//    @Test
//    public void testRegisterMedication() {
//        // Mocking input DTO
//        MedicationDto medicationDto = new MedicationDto("MedicationName1", 10.0, "MED001", "image1.jpg");
//
//        // Mocking mapped Medication object
//        Medication mappedMedication = new Medication();
//        mappedMedication.setName("MedicationName1");
//        mappedMedication.setWeight(10.0);
//        mappedMedication.setCode("MED001");
//        mappedMedication.setImage("image1.jpg");
//
//        // Mocking saved Medication object
//        Medication savedMedication = new Medication();
//        savedMedication.setId(1L);
//        savedMedication.setName("MedicationName1");
//        savedMedication.setWeight(10.0);
//        savedMedication.setCode("MED001");
//        savedMedication.setImage("image1.jpg");
//
//        // Mocking medicationMapper behavior
//        when(medicationMapper.mapToMedication(medicationDto)).thenReturn(mappedMedication);
//        when(medicationRepository.save(mappedMedication)).thenReturn(savedMedication);
//        when(medicationMapper.mapToMedicationResponse(savedMedication)).thenReturn(new MedicationResponse("MedicationName1", 10.0, "MED001", "image1.jpg"));
//
//        // Call the method to be tested
//        MedicationResponse response = medicationService.registerMedication(medicationDto);
//
//        // Verify behavior
//        verify(medicationMapper).mapToMedication(medicationDto);
//        verify(medicationRepository).save(mappedMedication);
//        verify(medicationMapper).mapToMedicationResponse(savedMedication);
//
//        // Assertions
//        assertEquals("MedicationName1", response.getName());
//        assertEquals(Double.valueOf(10.0), response.getWeight());
//        assertEquals("MED001", response.getCode());
//        assertEquals("image1.jpg", response.getImage());
//    }
//}
//
