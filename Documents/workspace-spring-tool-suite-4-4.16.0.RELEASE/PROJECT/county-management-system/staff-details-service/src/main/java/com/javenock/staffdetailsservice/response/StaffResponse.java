package com.javenock.staffdetailsservice.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class StaffResponse {
    private String staff_id;
    private String kra_pin;
    private Long nationid;
    private String mobile_number;
    private String first_name;
    private String othername;
    private Long age;
}
