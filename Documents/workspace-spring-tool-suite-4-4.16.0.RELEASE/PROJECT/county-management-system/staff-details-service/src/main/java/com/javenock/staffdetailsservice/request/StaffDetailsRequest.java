package com.javenock.staffdetailsservice.request;

import jakarta.validation.constraints.*;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class StaffDetailsRequest {

    @NotNull(message = "staff id should not be null")
    private String staff_id;
    @NotBlank(message = "kra should not be blank")
    private String kra_pin;
    @NotNull(message = "nation id should not be null")
    private Long nationid;
    @Pattern(regexp = "^\\d{10}$", message = "invalid mobile number")
    private String mobile_number;
    @NotBlank(message = "first name is required")
    private String first_name;
    @NotBlank
    private String othername;
    @Min(18)
    @Max(65)
    private Long age;
    @NotBlank
    private String departmentid;
}
