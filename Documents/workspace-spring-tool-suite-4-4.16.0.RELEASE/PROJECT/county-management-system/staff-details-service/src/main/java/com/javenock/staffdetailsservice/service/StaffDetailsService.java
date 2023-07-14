package com.javenock.staffdetailsservice.service;

import com.javenock.staffdetailsservice.exception.DepartmentNotFoundException;
import com.javenock.staffdetailsservice.exception.NoStaffsRegisteredException;
import com.javenock.staffdetailsservice.exception.StaffNotFoundException;
import com.javenock.staffdetailsservice.model.StaffDetails;
import com.javenock.staffdetailsservice.repository.StaffDetailsRepository;
import com.javenock.staffdetailsservice.request.StaffDetailsRequest;
import com.javenock.staffdetailsservice.response.DepartmentResponse;
import com.javenock.staffdetailsservice.response.GrandResponse;
import com.javenock.staffdetailsservice.response.StaffResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientResponseException;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Map;

@Service
public class StaffDetailsService {

    @Autowired
    private StaffDetailsRepository staffDetailsRepository;

    @Autowired
    private RestTemplate restTemplate;

//    @Value("${microservices.department-service.endpoints.endpoint.uri}")
//    private String URL_ENDPOINT;

    public GrandResponse save_staff_details(StaffDetailsRequest staffDetailsRequest) throws DepartmentNotFoundException {
           try{
               DepartmentResponse departmentResponse = restTemplate.getForObject("http://DEPARTMENT-SERVICE/department/{departmentid}", DepartmentResponse.class, Map.of("departmentid",staffDetailsRequest.getDepartmentid()));
               StaffDetails staffDetails = StaffDetails.builder()
                       .staff_id(staffDetailsRequest.getStaff_id())
                       .kra_pin(staffDetailsRequest.getKra_pin())
                       .nationid(staffDetailsRequest.getNationid())
                       .first_name(staffDetailsRequest.getFirst_name())
                       .othername(staffDetailsRequest.getOthername())
                       .mobile_number(staffDetailsRequest.getMobile_number())
                       .age(staffDetailsRequest.getAge())
                       .departmentid(departmentResponse.getDepartmentid())
                       .build();
               staffDetailsRepository.save(staffDetails);
               GrandResponse grandResponse = new GrandResponse(new StaffResponse(staffDetails.getStaff_id(),
                       staffDetails.getKra_pin(),
                       staffDetails.getNationid(),
                       staffDetails.getFirst_name(),
                       staffDetails.getMobile_number(),
                       staffDetails.getOthername(),
                       staffDetails.getAge()),departmentResponse);
               return grandResponse;
           }catch(RestClientResponseException ex){
               throw new DepartmentNotFoundException(ex.getResponseBodyAsString());
           }

    }

    public StaffDetails fetch_staff_by_nation_id(Long nationId) throws StaffNotFoundException {
        StaffDetails staff = staffDetailsRepository.findByNationid(nationId);
        if(staff != null){
            return staff;
        }else {
            throw new StaffNotFoundException("Staff Details not found with nation id :"+nationId);
        }
    }

    public List<StaffDetails> fetchAllStaffs() throws NoStaffsRegisteredException {
        List<StaffDetails> listOfStaffs = staffDetailsRepository.findAll();
        if(listOfStaffs.size() > 0){
            return listOfStaffs;
        }else{
            throw new NoStaffsRegisteredException("No staffs registered");
        }
    }


    public StaffDetails updateStaffDetails(StaffDetailsRequest staffDetailsRequest, Long id) throws StaffNotFoundException {
        StaffDetails staffDetails = staffDetailsRepository.findById(id).orElseThrow(() -> new StaffNotFoundException("No such staff found"));
            staffDetails.setId(staffDetails.getId());
            staffDetails.setStaff_id(staffDetailsRequest.getStaff_id());
            staffDetails.setNationid(staffDetailsRequest.getNationid());
            staffDetails.setKra_pin(staffDetailsRequest.getKra_pin());
            staffDetails.setMobile_number(staffDetailsRequest.getMobile_number());
            staffDetails.setFirst_name(staffDetailsRequest.getFirst_name());
            staffDetails.setOthername(staffDetailsRequest.getOthername());
            staffDetails.setAge(staffDetailsRequest.getAge());
            return staffDetailsRepository.save(staffDetails);
    }

    public void delete_staff_by_id(Long id) throws StaffNotFoundException {
        StaffDetails staffDetails = staffDetailsRepository.findById(id).orElseThrow(() -> new StaffNotFoundException("No such staff found"));
        staffDetailsRepository.delete(staffDetails);
    }
}
