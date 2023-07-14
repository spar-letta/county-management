package com.javenock.staffdetailsservice.response;

import com.javenock.staffdetailsservice.model.StaffDetails;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GrandResponse {
    private StaffResponse staffResponse;
    private DepartmentResponse departmentResponse;
}
