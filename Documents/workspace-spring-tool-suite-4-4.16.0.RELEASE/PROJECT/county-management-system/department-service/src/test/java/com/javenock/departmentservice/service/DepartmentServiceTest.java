package com.javenock.departmentservice.service;

import com.javenock.departmentservice.exception.DepartmentExistsException;
import com.javenock.departmentservice.exception.DepartmentNotFoundException;
import com.javenock.departmentservice.model.Department;
import com.javenock.departmentservice.repository.DepartmentRepository;
import com.javenock.departmentservice.request.DepartmentRequest;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.time.LocalDate;

import static org.junit.Assert.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class DepartmentServiceTest {

    @InjectMocks
    private DepartmentService departmentService;

    @Mock
    private DepartmentRepository departmentRepository;

    @Test
    @DisplayName("save_department")
    public void shouldThrowExceptionDeprtIdExists(){
        DepartmentRequest departmentRequest = DepartmentRequest.
        builder()
                .departmentid("AA25")
                .department_name("TRANSPORT")
                .datestarted("2017-04-28")
                .build();

        Department department = Department
                .builder()
                .id(1L)
                .departmentid(departmentRequest.getDepartmentid())
                .department_name(departmentRequest.getDepartment_name())
                .datestarted(LocalDate.parse(departmentRequest.getDatestarted()))
                .build();

        when(departmentRepository.findByDepartmentid("AA25")).thenReturn(department);
        assertThrows(DepartmentExistsException.class, () -> departmentService.save_department(departmentRequest));

    }


}
