package com.javenock.departmentservice.repository;

import com.javenock.departmentservice.model.Department;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DepartmentRepository extends JpaRepository<Department, Long> {
    Department findByDepartmentid(String departmentid);
}
