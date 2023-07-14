package com.javenock.staffdetailsservice.advice;

import com.javenock.staffdetailsservice.exception.DepartmentNotFoundException;
import com.javenock.staffdetailsservice.exception.NoStaffsRegisteredException;
import com.javenock.staffdetailsservice.exception.StaffNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class ApplicationExceptionHandler {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String,String> errorHandlingMethod(MethodArgumentNotValidException err){
        Map<String, String> errorHandler = new HashMap<>();
        err.getBindingResult().getFieldErrors().forEach(error -> {
            errorHandler.put(error.getField(), error.getDefaultMessage());
        });
        return errorHandler;
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(StaffNotFoundException.class)
    public Map<String, String> StaffNotFoundEx(StaffNotFoundException ex){
        Map<String, String> errorMap = new HashMap<>();
        errorMap.put("Error Message :", ex.getMessage());
        return errorMap;
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(NoStaffsRegisteredException.class)
    public Map<String, String> NoStaffsException(NoStaffsRegisteredException ex){
        Map<String, String> errorMap = new HashMap<>();
        errorMap.put("Error message :", ex.getMessage());
        return errorMap;
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(DepartmentNotFoundException.class)
    public Map<String, String> NoSearchDepartment(DepartmentNotFoundException ex){
        Map<String, String> errorMap = new HashMap<>();
        errorMap.put("Error message :", ex.getMessage());
        return errorMap;
    }
}
