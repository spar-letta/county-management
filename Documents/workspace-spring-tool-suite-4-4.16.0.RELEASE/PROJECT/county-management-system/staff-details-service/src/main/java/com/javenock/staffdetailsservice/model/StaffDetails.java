package com.javenock.staffdetailsservice.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name="staff_details")
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class StaffDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String staff_id;
    private String kra_pin;
    private String mobile_number;
    private Long nationid;
    private String first_name;
    private String othername;
    private Long age;
    private String departmentid;


}
