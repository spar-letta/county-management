package com.javenock.staffdetailsservice.service;

import com.javenock.staffdetailsservice.exception.DepartmentNotFoundException;
import com.javenock.staffdetailsservice.exception.StaffNotFoundException;
import com.javenock.staffdetailsservice.model.StaffDetails;
import com.javenock.staffdetailsservice.repository.StaffDetailsRepository;
import com.javenock.staffdetailsservice.request.StaffDetailsRequest;
import com.javenock.staffdetailsservice.response.DepartmentResponse;
import com.javenock.staffdetailsservice.response.GrandResponse;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class StaffDetailsServiceTest {

    @InjectMocks
    private StaffDetailsService staffDetailsService;

    @Mock
    private StaffDetailsRepository staffDetailsRepository;

    @Mock
    private RestTemplate restTemplate;

    @Test
    @DisplayName("save_staff_details")
    public void shouldSaveStaffDetails() throws DepartmentNotFoundException {
       DepartmentResponse departmentRes = new DepartmentResponse();
        StaffDetailsRequest staffDetailsRequest = StaffDetailsRequest.builder()
                .staff_id("101")
                        .nationid(12345678L)
                                .kra_pin("A938484840W")
                                        .first_name("Grace")
                                                .othername("Wamboi weru")
                                                        .mobile_number("0708761434")
                                                                .age(35L)
                                                                    .departmentid("AA23")
        .build();
        when(restTemplate.getForObject("http://localhost:8087/department/{departmentid}", DepartmentResponse.class, Map.of("departmentid",staffDetailsRequest.getDepartmentid()))).thenReturn(departmentRes);
        StaffDetails new_staffDetails = StaffDetails.builder()
                .id(1L)
                .staff_id(staffDetailsRequest.getStaff_id())
                .kra_pin(staffDetailsRequest.getKra_pin())
                .nationid(staffDetailsRequest.getNationid())
                .first_name(staffDetailsRequest.getFirst_name())
                .othername(staffDetailsRequest.getOthername())
                .mobile_number(staffDetailsRequest.getMobile_number())
                .age(staffDetailsRequest.getAge())
                .departmentid(departmentRes.getDepartmentid())
                .build();


        when(staffDetailsRepository.save(any(StaffDetails.class))).thenReturn(new_staffDetails);
        GrandResponse saved_staff_details = new GrandResponse(staffDetailsService.save_staff_details(staffDetailsRequest).getStaffResponse(),departmentRes);
        assertNotNull(saved_staff_details);
        assertThat(saved_staff_details.getStaffResponse().getStaff_id()).isEqualTo("101");
    }

    @Test
    @DisplayName("find_by_national_id")
    public void shouldReturnStaffByNationId() throws StaffNotFoundException {
        StaffDetailsRequest staffDetailsRequest = StaffDetailsRequest.builder()
                .staff_id("101")
                .nationid(12345678L)
                .kra_pin("A938484840W")
                .first_name("Grace")
                .othername("Wamboi weru")
                .mobile_number("0708761434")
                .age(35L)
                .build();
        StaffDetails new_staffDetails = StaffDetails.builder()
                .id(1L)
                .staff_id(staffDetailsRequest.getStaff_id())
                .kra_pin(staffDetailsRequest.getKra_pin())
                .nationid(staffDetailsRequest.getNationid())
                .first_name(staffDetailsRequest.getFirst_name())
                .othername(staffDetailsRequest.getOthername())
                .mobile_number(staffDetailsRequest.getMobile_number())
                .age(staffDetailsRequest.getAge())
                .build();
        when(staffDetailsRepository.findByNationid(anyLong())).thenReturn(new_staffDetails);
        StaffDetails fetchedStaffDetails = staffDetailsService.fetch_staff_by_nation_id(12345678L);
        assertNotNull(fetchedStaffDetails);
        assertThat(fetchedStaffDetails.getStaff_id()).isEqualTo("101");
    }

    @Test
    @DisplayName("No_staff_with_nation_id")
    public void shouldThrowExceptionNoNationIdFound() throws StaffNotFoundException {
        StaffDetailsRequest staffDetailsRequest = StaffDetailsRequest.builder()
                .staff_id("101")
                .nationid(12345678L)
                .kra_pin("A938484840W")
                .first_name("Grace")
                .othername("Wamboi weru")
                .mobile_number("0708761434")
                .age(35L)
                .build();
        StaffDetails new_staffDetails = StaffDetails.builder()
                .id(1L)
                .staff_id(staffDetailsRequest.getStaff_id())
                .kra_pin(staffDetailsRequest.getKra_pin())
                .nationid(staffDetailsRequest.getNationid())
                .first_name(staffDetailsRequest.getFirst_name())
                .othername(staffDetailsRequest.getOthername())
                .mobile_number(staffDetailsRequest.getMobile_number())
                .age(staffDetailsRequest.getAge())
                .build();
        when(staffDetailsRepository.findByNationid(12345678L)).thenReturn(new_staffDetails);
        assertThrows(RuntimeException.class, () -> staffDetailsService.fetch_staff_by_nation_id(12345L));


    }
}
