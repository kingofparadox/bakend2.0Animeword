package com.animeword.bakend.error;

import com.animeword.bakend.shared.DtoError;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@RestControllerAdvice
public class GlobalExceptionHandler {

    // 1. Errores de validación de DTOs (@Valid)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<DtoError> handleValidationExceptions(MethodArgumentNotValidException ex) {
        Map<String, String> errores = new HashMap<>();
        
        for (org.springframework.validation.ObjectError error : ex.getBindingResult().getAllErrors()) {
            String campo = ((FieldError) error).getField();
            String mensaje = error.getDefaultMessage();
            errores.put(campo, mensaje);
        }

        DtoError errorDto = new DtoError(
            HttpStatus.BAD_REQUEST.value(),
            "Error de validación en los datos enviados",
            LocalDateTime.now(),
            Optional.of(errores)
        );
        
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorDto);
    }

    // 2. Recurso No Encontrado (404)
    @ExceptionHandler(errorNoencotrado.class)
    public ResponseEntity<DtoError> handleResourceNotFound(errorNoencotrado ex) {
        DtoError errorDto = new DtoError(
            HttpStatus.NOT_FOUND.value(),
            "Recurso no encontrado",
            LocalDateTime.now(),
            Optional.of(Map.of("recurso", ex.getMessage()))
        );
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorDto);
    }

    // 3. Errores de Base de Datos (409 Conflict)
    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<DtoError> handleDatabaseIntegrity(DataIntegrityViolationException ex) {
        DtoError errorDto = new DtoError(
            HttpStatus.CONFLICT.value(),
            "Conflicto en la base de datos",
            LocalDateTime.now(),
            Optional.of(Map.of("baseDeDatos", "El registro viola las restricciones de unicidad o integridad."))
        );
        return ResponseEntity.status(HttpStatus.CONFLICT).body(errorDto);
    }

    // 4. Excepción General / Inesperada (500)
    @ExceptionHandler(Exception.class)
    public ResponseEntity<DtoError> handleGeneralException(Exception ex) {
        DtoError errorDto = new DtoError(
            HttpStatus.INTERNAL_SERVER_ERROR.value(),
            "Ocurrió un error inesperado en el servidor",
            LocalDateTime.now(),
            Optional.of(Map.of("error", ex.getMessage()))
        );
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorDto);
    }
}