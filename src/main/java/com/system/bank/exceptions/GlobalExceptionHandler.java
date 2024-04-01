package com.system.bank.exceptions;

import com.system.bank.model.ResponseModel;
import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ResponseEntity<ResponseModel> validationExceptions(MethodArgumentNotValidException exception){
        Map<String , String > errors = new HashMap<>();
        exception.getBindingResult()
                .getFieldErrors()
                .forEach(error -> errors.put(error.getField(),error.getDefaultMessage()));

        return ResponseEntity.badRequest()
                .body(
                        ResponseModel
                                .builder()
                                .status(HttpStatus.BAD_REQUEST)
                                .success(false)
                                .errors(errors)
                                .build()
                );
    }
    @ExceptionHandler(IllegalArgumentException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ResponseEntity<ResponseModel> illegalArgumentException(IllegalArgumentException exception){
        return ResponseEntity
                .badRequest()
                .body(
                        ResponseModel
                                .builder()
                                .status(HttpStatus.BAD_REQUEST)
                                .success(false)
                                .errors(exception.getMessage())
                                .build()
                );
    }
    @ExceptionHandler(RuntimeException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ResponseBody
    public ResponseEntity<ResponseModel> RuntimeException(RuntimeException exception){
        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(
                        ResponseModel
                                .builder()
                                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                                .success(false)
                                .errors(exception.getMessage())
                                .build()
                );
    }
    @ExceptionHandler(EntityNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ResponseBody
    public ResponseEntity<ResponseModel> NotFoundException(EntityNotFoundException exception){
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(
                        ResponseModel
                                .builder()
                                .status(HttpStatus.NOT_FOUND)
                                .success(false)
                                .errors(exception.getMessage())
                                .build()
                );
    }
    @ExceptionHandler(EntityExistsException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    @ResponseBody
    public ResponseEntity<ResponseModel> EntityExistsException(EntityExistsException exception){
        return ResponseEntity
                .status(HttpStatus.CONFLICT)
                .body(
                        ResponseModel
                                .builder()
                                .status(HttpStatus.CONFLICT)
                                .success(false)
                                .errors(exception.getMessage())
                                .build()
                );
    }
    @ExceptionHandler(BadCredentialsException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    @ResponseBody
    public ResponseEntity<ResponseModel> BadCredentialsException(BadCredentialsException exception){
        return ResponseEntity
                .status(HttpStatus.UNAUTHORIZED)
                .body(
                        ResponseModel
                                .builder()
                                .status(HttpStatus.UNAUTHORIZED)
                                .success(false)
                                .errors(exception.getMessage())
                                .build()
                );
    }
    @ExceptionHandler(AuthenticationCredentialsNotFoundException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    @ResponseBody
    public ResponseEntity<ResponseModel> authenticationCredentialsNotFoundException(AuthenticationCredentialsNotFoundException exception){
        return ResponseEntity
                .status(HttpStatus.UNAUTHORIZED)
                .body(
                        ResponseModel
                                .builder()
                                .status(HttpStatus.UNAUTHORIZED)
                                .success(false)
                                .errors("Authentication credentials not found")
                                .build()
                );
    }
    @ExceptionHandler(LowBalanceException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ResponseEntity<ResponseModel> LowBalanceException(LowBalanceException exception){
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(
                        ResponseModel
                                .builder()
                                .status(HttpStatus.BAD_REQUEST)
                                .success(false)
                                .errors(exception.getMessage())
                                .build()
                );
    }

}
