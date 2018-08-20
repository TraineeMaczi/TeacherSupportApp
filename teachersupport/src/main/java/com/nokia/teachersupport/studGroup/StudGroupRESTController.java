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
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

@RestController
public class StudGroupRESTController {


    private IPersonService personService;
    private IUserSecurityDataService userSecurityDataService;
    private IStudGroupService studGroupService;
//    private StudGroup studGroupLocalInstanc; //nieladnie silna zelznosc


    @Autowired
    public StudGroupRESTController(IStudGroupService studGroupService, IPersonService personService, IMeetMeService meetMeService, IUserSecurityDataService userSecurityDataService) {
        this.personService = personService;
        this.userSecurityDataService = userSecurityDataService;
        this.studGroupService = studGroupService;
//        this.studGroupLocalInstanc = new StudGroup();
    }


    @PostMapping("/teacherSupportStudent/edit")
    public ResponseEntity<Object> editGroup(@RequestBody String groupName,HttpSession session) {
        Person person = personService.getPersonByUserSecurityData(userSecurityDataService.getUserSecurityDataByEmail(CurrentUser.getCurrentUserName()));
//        studGroupLocalInstanc = studGroupService.getStudGroupByName(groupName);
//        person.setCurrentGroupName(groupName);
//        personService.savePerson(person);
        session.setAttribute("currentStudGroupName",groupName);
        ServiceResponse<String> response = new ServiceResponse<String>("success", groupName);
        return new ResponseEntity<Object>(response, HttpStatus.OK);
    }

    @PostMapping("/teacherSupportStudent/delete")
    public ResponseEntity<Object> deleteGroup(@RequestBody String groupName) {
        Person person = personService.getPersonByUserSecurityData(userSecurityDataService.getUserSecurityDataByEmail(CurrentUser.getCurrentUserName()));
        person.deleteStudGroup(studGroupService.getStudGroupByName(groupName));
        studGroupService.deleteStudGroupById(studGroupService.getStudGroupByName(groupName).getId());
        personService.savePerson(person);
        ServiceResponse<String> response = new ServiceResponse<String>("success", groupName);
        return new ResponseEntity<Object>(response, HttpStatus.OK);
    }

    @PostMapping("/teacherSupportStudent/updateGroup")
    public ResponseEntity<Object> studGroupUpdate(@RequestBody StudGroupDTO studGroupDTO) {
        if (studGroupService.getStudGroupByName(studGroupDTO.getGroupNameField()) != null) {
           StudGroup studGroup = studGroupService.getStudGroupByName(studGroupDTO.getGroupNameField());
            studGroupService.studGroupDTOIntoStudGroup(studGroupDTO,studGroup);
            studGroupService.studGroupDTOIntoStudGroup(studGroupDTO,studGroup);
            studGroupService.saveStudGroup(studGroup);
        }
        //to chyba mozna by wykorzystac dla succes lub fail pinizej
        ServiceResponse<StudGroupDTO> response = new ServiceResponse<StudGroupDTO>("success", studGroupDTO);
        return new ResponseEntity<Object>(response, HttpStatus.OK);
    }

}
