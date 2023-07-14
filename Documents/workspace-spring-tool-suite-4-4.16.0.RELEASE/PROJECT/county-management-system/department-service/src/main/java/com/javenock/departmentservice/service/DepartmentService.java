package com.javenock.departmentservice.service;

import com.javenock.departmentservice.exception.DepartmentExistsException;
import com.javenock.departmentservice.exception.DepartmentNotFoundException;
import com.javenock.departmentservice.model.Department;
import com.javenock.departmentservice.repository.DepartmentRepository;
import com.javenock.departmentservice.request.DepartmentRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class DepartmentService {

    @Autowired
    private DepartmentRepository departmentRepository;

    public Department save_department(DepartmentRequest departmentRequest) throws DepartmentExistsException {
        Department department = Department.builder()
                .departmentid(departmentRequest.getDepartmentid())
                .department_name(departmentRequest.getDepartment_name())
                .datestarted(LocalDate.parse(departmentRequest.getDatestarted()))
                .build();
        Department dept = departmentRepository.findByDepartmentid(departmentRequest.getDepartmentid());
        if(dept != null){
            throw new DepartmentExistsException("Department id :"+ departmentRequest.getDepartmentid() + " already exists");
        }else{
            return departmentRepository.save(department);
        }

    }


    public Department findDepartmentByDeprtId(String departmentid) throws DepartmentNotFoundException {
        Department department = departmentRepository.findByDepartmentid(departmentid);
        if(department != null){
            return department;
        }else{
            throw new DepartmentNotFoundException("No such department with department id :"+departmentid);
        }
    }
}
