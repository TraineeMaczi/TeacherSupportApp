package com.nokia.teachersupport.person;

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


    private IPersonService personService;
    private IUserSecurityDataService userSecurityDataService;
    private IMeetMeService meetMeService;

    @Autowired
    public ContactRESTController(IPersonService personService, IMeetMeService meetMeService, IUserSecurityDataService userSecurityDataService) {
        this.personService = personService;
        this.userSecurityDataService = userSecurityDataService;
        this.meetMeService = meetMeService;
    }


    @PostMapping("/teacherSupportContact/contact/new")
    public ResponseEntity<Object> addContactInfo(@RequestBody MeetMeDTO meetMeDTO) {
        ServiceResponse<MeetMeDTO> response;
if(meetMeService.checkMeetMeDTOIntegrity(meetMeDTO)) {
    meetMeService.goAddContactInfo(meetMeDTO, personService, userSecurityDataService);
    response = new ServiceResponse<MeetMeDTO>("success", meetMeDTO);
    return new ResponseEntity<Object>(response, HttpStatus.OK);
}
else
{
    response = new ServiceResponse<MeetMeDTO>("error", meetMeDTO);
    return new ResponseEntity<Object>(response, HttpStatus.OK);
}


    }

    @PostMapping("/teacherSupportContact/deleteContactInfo")
    public ResponseEntity<Object> deleteContactInfo(@RequestBody Integer contactInfoId) {
        meetMeService.goDeleteContactInfo(contactInfoId,userSecurityDataService,personService);
        ServiceResponse<Integer> response = new ServiceResponse<Integer>("success", contactInfoId);
        return new ResponseEntity<Object>(response, HttpStatus.OK);
    }

}
