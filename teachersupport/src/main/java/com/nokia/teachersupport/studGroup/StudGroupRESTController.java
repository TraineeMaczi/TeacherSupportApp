package com.nokia.teachersupport.studGroup;

import com.nokia.teachersupport.currentUser.CurrentUser;
import com.nokia.teachersupport.person.IMeetMeService;
import com.nokia.teachersupport.person.IPersonService;
import com.nokia.teachersupport.person.Person;
import com.nokia.teachersupport.person.ServiceResponse;
import com.nokia.teachersupport.personSecurity.IUserSecurityDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class StudGroupRESTController {


    private IPersonService personService;
    private IUserSecurityDataService userSecurityDataService;
    private IStudGroupService studGroupService;
    private StudGroup studGroupLocalInstanc; //nieladnie silna zelznosc


    @Autowired
    public StudGroupRESTController(IStudGroupService studGroupService, IPersonService personService,IMeetMeService meetMeService ,IUserSecurityDataService userSecurityDataService) {
        this.personService = personService;
        this.userSecurityDataService=userSecurityDataService;
        this.studGroupService=studGroupService;
        this.studGroupLocalInstanc=null;
    }


    @PostMapping("/teacherSupportStudent/select")
    public ResponseEntity<Object> addContactInfo(@RequestBody String groupName) {
        Person person=personService.getPersonByUserSecurityData(userSecurityDataService.getUserSecurityDataByEmail(CurrentUser.getCurrentUserName()));
        studGroupLocalInstanc=studGroupService.getStudGroupByName(groupName);
        ServiceResponse<String> response = new ServiceResponse<String>("success", groupName);
        return new ResponseEntity<Object>(response, HttpStatus.OK);
    }


}
