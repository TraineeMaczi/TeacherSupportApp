package com.nokia.teachersupport.person;

import com.nokia.teachersupport.currentUser.CurrentUser;
import com.nokia.teachersupport.personSecurity.IUserSecurityDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class AboutMeRESTController {
    private IPersonService personService;
    private IUserSecurityDataService userSecurityDataService;


    @Autowired
    public AboutMeRESTController(IPersonService personService, IUserSecurityDataService userSecurityDataService) {
        this.personService = personService;
        this.userSecurityDataService = userSecurityDataService;
    }
    @PostMapping("/teacherSupportAboutMe/BasicInfo/new")
    public ResponseEntity<Object> addBasicInfo(@RequestBody BasicInfoDTO basicInfoDTO) {
        Person person=personService.getPersonByUserSecurityData(userSecurityDataService.getUserSecurityDataByEmail(CurrentUser.getCurrentUserName()));
        person=personService.setPersonBasicInfo(basicInfoDTO,person);
        personService.savePerson(person);
        ServiceResponse<BasicInfoDTO> response = new ServiceResponse<BasicInfoDTO>("success", basicInfoDTO);
        return new ResponseEntity<Object>(response, HttpStatus.OK);
    }

    @PostMapping("/teacherSupportAboutMe/hobby/new")
    public ResponseEntity<Object> addHobbyInfo(@RequestBody String hobbyInfo) {
        Person person=personService.getPersonByUserSecurityData(userSecurityDataService.getUserSecurityDataByEmail(CurrentUser.getCurrentUserName()));

        if (!hobbyInfo.equals(""))person.setHobbyField(hobbyInfo);

        personService.savePerson(person);

        ServiceResponse<String> response = new ServiceResponse<String>("success", hobbyInfo);
        return new ResponseEntity<Object>(response, HttpStatus.OK);
    }

}







