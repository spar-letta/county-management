package com.javenock.staffdetailsservice.controller;

import com.javenock.staffdetailsservice.exception.DepartmentNotFoundException;
import com.javenock.staffdetailsservice.exception.NoStaffsRegisteredException;
import com.javenock.staffdetailsservice.exception.StaffNotFoundException;
import com.javenock.staffdetailsservice.model.StaffDetails;
import com.javenock.staffdetailsservice.request.StaffDetailsRequest;
import com.javenock.staffdetailsservice.response.GrandResponse;
import com.javenock.staffdetailsservice.service.StaffDetailsService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/staff_details")
public class StaffDetailsController {

    private StaffDetailsService staffDetailsService;

    public StaffDetailsController(StaffDetailsService staffDetailsService) {
        this.staffDetailsService = staffDetailsService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public GrandResponse save_staff(@RequestBody @Valid StaffDetailsRequest staffDetailsRequest) throws DepartmentNotFoundException {
        return staffDetailsService.save_staff_details(staffDetailsRequest);
    }

    @GetMapping("/{nation_id}")
    @ResponseStatus(HttpStatus.OK)
    public StaffDetails fetch_staff_by_nation_id(@PathVariable Long nation_id) throws StaffNotFoundException {
        return staffDetailsService.fetch_staff_by_nation_id(nation_id);
    }

    @GetMapping("/all_staffs")
    @ResponseStatus(HttpStatus.OK)
    public List<StaffDetails> fetch_all_staffs() throws NoStaffsRegisteredException {
        return staffDetailsService.fetchAllStaffs();
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public StaffDetails updateStaffDetails(@RequestBody StaffDetailsRequest staffDetailsRequest, @PathVariable Long id) throws StaffNotFoundException {
        return staffDetailsService.updateStaffDetails(staffDetailsRequest, id);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete_staff_by_id(@PathVariable Long id) throws StaffNotFoundException {
        staffDetailsService.delete_staff_by_id(id);
    }
}
