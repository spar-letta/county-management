package com.javenock.departmentservice.exception;

public class DepartmentNotFoundException extends Exception{
    public DepartmentNotFoundException(String message) {
        super(message);
    }
}
