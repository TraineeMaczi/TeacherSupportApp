package com.nokia.teachersupport.person;

import com.nokia.teachersupport.serviceProvider.IServiceProvider;
import com.nokia.teachersupport.tools.CurrentUser;
import com.nokia.teachersupport.personSecurity.IUserSecurityDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ContactRESTController {


   private IServiceProvider serviceProvider;

    @Autowired
    public ContactRESTController(IServiceProvider serviceProvider) {
        this.serviceProvider=serviceProvider;
    }


    @PostMapping("/teacherSupportContact/contact/new")
    public ResponseEntity<Object> addContactInfo(@RequestBody MeetMeDTO meetMeDTO) {
        ServiceResponse<MeetMeDTO> response;
        if (serviceProvider.getIMeetMeService().checkMeetMeDTOIntegrity(meetMeDTO)) {
            serviceProvider.getIMeetMeService().goAddContactInfo(meetMeDTO,serviceProvider);
            response = new ServiceResponse<MeetMeDTO>("success", meetMeDTO);
            return new ResponseEntity<Object>(response, HttpStatus.OK);
        } else {
            response = new ServiceResponse<MeetMeDTO>("error", meetMeDTO);
            return new ResponseEntity<Object>(response, HttpStatus.OK);
        }


    }

    @PostMapping("/teacherSupportContact/deleteContactInfo")
    public ResponseEntity<Object> deleteContactInfo(@RequestBody Integer contactInfoId) {
        serviceProvider.getIMeetMeService().goDeleteContactInfo(contactInfoId, serviceProvider);
        ServiceResponse<Integer> response = new ServiceResponse<Integer>("success", contactInfoId);
        return new ResponseEntity<Object>(response, HttpStatus.OK);
    }

}
