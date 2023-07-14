package com.javenock.staffdetailsservice.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DepartmentResponse {
    private String departmentid;
    private String department_name;
    private String datestarted;
}
