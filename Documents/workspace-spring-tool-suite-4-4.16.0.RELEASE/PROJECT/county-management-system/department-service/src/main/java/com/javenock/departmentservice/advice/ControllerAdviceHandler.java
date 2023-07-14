package com.javenock.departmentservice.advice;

import com.javenock.departmentservice.exception.DepartmentExistsException;
import com.javenock.departmentservice.exception.DepartmentNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class ControllerAdviceHandler {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(DepartmentNotFoundException.class)
    public Map<String, String> handleDepartmentNotFound(DepartmentNotFoundException ex){
        Map<String,String> errorMap = new HashMap<>();
        errorMap.put("System error :", ex.getMessage());
        return errorMap;
    }
    @ResponseStatus(HttpStatus.ALREADY_REPORTED)
    @ExceptionHandler(DepartmentExistsException.class)
    public Map<String, String> handleDepartmentExistsEx(DepartmentExistsException ex){
        Map<String, String> errorMap = new HashMap<>();
        errorMap.put("Syatem error :",ex.getMessage());
        return errorMap;
    }
}
