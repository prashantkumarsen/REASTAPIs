package com.reastApis.exception;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.reastApis.entity.ErrorResponse;

@RestControllerAdvice
public class GlobalExceptionHandler 
{
	String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss"));
	
	@ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleResourceNotFound(ResourceNotFoundException ex) 
	{		
       ErrorResponse error = ErrorResponse.builder()
      .timestamp(timestamp)
      .status(HttpStatus.NOT_FOUND.value())
      .message(ex.getMessage())
      .build();
        		
        		
        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> handleValidationException(MethodArgumentNotValidException ex) {

        Map<String, String> errors = new HashMap<>();

        ex.getBindingResult().getFieldErrors().forEach(error -> {
            errors.put(error.getField(), error.getDefaultMessage());
        });

        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
    }

	@ExceptionHandler(Exception.class)
	public ResponseEntity<ErrorResponse> handleGenericException(Exception ex)
	{
		ErrorResponse error = ErrorResponse.builder()
	     .timestamp(timestamp)
	     .status(HttpStatus.INTERNAL_SERVER_ERROR.value())
	     .message("Something went wrong!")
		 .build();
		
		return new ResponseEntity<ErrorResponse>(error,HttpStatus.INTERNAL_SERVER_ERROR);		
	}
}
