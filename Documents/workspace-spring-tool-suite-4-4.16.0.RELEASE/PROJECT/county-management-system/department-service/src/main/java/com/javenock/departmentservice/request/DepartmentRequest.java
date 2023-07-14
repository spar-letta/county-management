package com.javenock.departmentservice.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class DepartmentRequest {
    @NotBlank(message = "department id should not be blank")
    private String departmentid;
    @NotBlank
    private String department_name;
    private String datestarted;
}
