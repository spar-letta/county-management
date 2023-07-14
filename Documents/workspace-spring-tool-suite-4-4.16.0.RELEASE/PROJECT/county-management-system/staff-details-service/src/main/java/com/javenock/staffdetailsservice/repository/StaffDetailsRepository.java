package com.javenock.staffdetailsservice.repository;

import com.javenock.staffdetailsservice.model.StaffDetails;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StaffDetailsRepository extends JpaRepository<StaffDetails,Long> {
    StaffDetails findByNationid(Long nation_Id);
}
