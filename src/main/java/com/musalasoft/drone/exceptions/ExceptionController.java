package com.musalasoft.drone.exceptions;
import com.musalasoft.drone.payloads.responses.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestControllerAdvice
public class ExceptionController  {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiResponse> handleValidationErrors(MethodArgumentNotValidException ex) {
        List<String> errors = ex.getBindingResult().getFieldErrors()
                .stream().map(FieldError::getDefaultMessage).collect(Collectors.toList());
        return new ResponseEntity<>(new ApiResponse<>("validation errors",false, errors), HttpStatus.BAD_REQUEST);
    }


    @ExceptionHandler(SizeExceededException.class)
    public ResponseEntity<?> SizeExceededException(SizeExceededException ex) {
        return new ResponseEntity<>(new ApiResponse<>((ex.getMessage()),false), HttpStatus.BAD_REQUEST);
    }

    private Map<String, List<String>> getErrorsMap(List<String> errors) {
        Map<String, List<String>> errorResponse = new HashMap<>();
        errorResponse.put("Errors", errors);
        return errorResponse;

    }
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<?> ResourceNotFoundException(ResourceNotFoundException ex) {
        return new ResponseEntity<>(new ApiResponse<>((ex.getMessage()),false), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(CustomInternalServerException.class)
    public ResponseEntity<?> CustomInternalServerException(CustomInternalServerException ex) {
        return new ResponseEntity<>(new ApiResponse<>((ex.getMessage()),false), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(UserPasswordMismatchException.class)
    public ResponseEntity<?> UserPasswordMismatchException(UserPasswordMismatchException ex) {
        return new ResponseEntity<>(new ApiResponse<>((ex.getMessage()),false), HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(AuthenticationFailedException.class)
    public ResponseEntity<?> AuthenticationFailedException(AuthenticationFailedException ex) {
        return new ResponseEntity<>(new ApiResponse<>((ex.getMessage()),false), HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(CustomOutOfStock.class)
    public ResponseEntity<?> CustomOutOfStock(CustomOutOfStock ex) {
        return new ResponseEntity<>(new ApiResponse<>((ex.getMessage()),false), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(BatteryAndDroneStateException.class)
    public ResponseEntity<?> BatteryAndDroneStateException(BatteryAndDroneStateException ex) {
        return new ResponseEntity<>(new ApiResponse<>((ex.getMessage()),false), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(DroneLimitException.class)
    public ResponseEntity<?> DroneLimitException(DroneLimitException ex) {
        return new ResponseEntity<>(new ApiResponse<>((ex.getMessage()),false), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(DroneNotFoundException.class)
    public ResponseEntity<?> DroneNotFoundException(DroneNotFoundException ex) {
        return new ResponseEntity<>(new ApiResponse<>((ex.getMessage()),false), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(InvalidDroneException.class)
    public ResponseEntity<?> InvalidDroneException(InvalidDroneException ex) {
        return new ResponseEntity<>(new ApiResponse<>((ex.getMessage()),false), HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(MedicationNotFoundException.class)
    public ResponseEntity<?> MedicationNotFoundException(MedicationNotFoundException ex) {
        return new ResponseEntity<>(new ApiResponse<>((ex.getMessage()),false), HttpStatus.NOT_FOUND);
    }
    @ExceptionHandler(MedicineDetailsAlreadyExistException.class)
    public ResponseEntity<?> MedicineDetailsAlreadyExistException(MedicineDetailsAlreadyExistException ex) {
        return new ResponseEntity<>(new ApiResponse<>((ex.getMessage()),false), HttpStatus.CONFLICT);
    }
    @ExceptionHandler(InvalidMedicationException.class)
    public ResponseEntity<?> InvalidMedicationException(InvalidMedicationException ex) {
        return new ResponseEntity<>(new ApiResponse<>((ex.getMessage()),false), HttpStatus.CONFLICT);
    }
    @ExceptionHandler(DroneAlreadyRegisteredException.class)
    public ResponseEntity<?> DroneAlreadyRegisteredException(DroneAlreadyRegisteredException ex) {
        return new ResponseEntity<>(new ApiResponse<>((ex.getMessage()),false), HttpStatus.CONFLICT);
    }


}
