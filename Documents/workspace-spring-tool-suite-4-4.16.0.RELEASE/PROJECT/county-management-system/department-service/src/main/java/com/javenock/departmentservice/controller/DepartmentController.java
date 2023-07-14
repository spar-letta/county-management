package com.javenock.departmentservice.controller;

import com.javenock.departmentservice.exception.DepartmentExistsException;
import com.javenock.departmentservice.exception.DepartmentNotFoundException;
import com.javenock.departmentservice.model.Department;
import com.javenock.departmentservice.request.DepartmentRequest;
import com.javenock.departmentservice.service.DepartmentService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/department")
public class DepartmentController {

    private DepartmentService departmentService;

    public DepartmentController(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Department createDepartment(@RequestBody DepartmentRequest departmentRequest) throws DepartmentExistsException {
        return departmentService.save_department(departmentRequest);
    }

    @GetMapping("/{departmentid}")
    @ResponseStatus(HttpStatus.OK)
    public Department fetchDepartmentByDepId(@PathVariable String departmentid) throws DepartmentNotFoundException {
        return departmentService.findDepartmentByDeprtId(departmentid);
    }
}
