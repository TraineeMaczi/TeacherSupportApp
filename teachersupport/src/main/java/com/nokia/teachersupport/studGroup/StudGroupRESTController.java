package com.nokia.teachersupport.studGroup;

import com.nokia.teachersupport.tools.CurrentUser;
import com.nokia.teachersupport.fileUpload.FileModel;
import com.nokia.teachersupport.fileUpload.IFileService;
import com.nokia.teachersupport.person.IMeetMeService;
import com.nokia.teachersupport.person.IPersonService;
import com.nokia.teachersupport.person.Person;
import com.nokia.teachersupport.person.ServiceResponse;
import com.nokia.teachersupport.personSecurity.IUserSecurityDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

@RestController
public class StudGroupRESTController {


    private IPersonService personService;
    private IUserSecurityDataService userSecurityDataService;
    private IStudGroupService studGroupService;
    private IFileService fileService;
    private IGroupRemoteResourceService remoteResourceService;

    @Autowired
    public StudGroupRESTController(IGroupRemoteResourceService remoteResourceService, IFileService fileService, IStudGroupService studGroupService, IPersonService personService, IMeetMeService meetMeService, IUserSecurityDataService userSecurityDataService) {
        this.personService = personService;
        this.userSecurityDataService = userSecurityDataService;
        this.studGroupService = studGroupService;
        this.fileService = fileService;
        this.remoteResourceService = remoteResourceService;
    }


    @PostMapping("/teacherSupportStudent/edit")
    public ResponseEntity<Object> editGroup(@RequestBody String groupName, HttpSession session) {
        Person person = personService.getPersonByUserSecurityData(userSecurityDataService.getUserSecurityDataByEmail(CurrentUser.getCurrentUserName()));
        session.setAttribute("currentStudGroupName", person.doIHaveAGroupWithName(groupName).getGroupNameField());
        ServiceResponse<String> response = new ServiceResponse<String>("success", groupName);
        return new ResponseEntity<Object>(response, HttpStatus.OK);
    }

    @PostMapping("/teacherSupportStudent/delete")
    public ResponseEntity<Object> deleteGroup(@RequestBody String groupName, HttpSession session) {
        Person person= personService.getPersonByUserSecurityData(userSecurityDataService.getUserSecurityDataByEmail(CurrentUser.getCurrentUserName()));
        studGroupService.deleteStudGroup(person,groupName, personService, fileService, remoteResourceService, userSecurityDataService, session);
        ServiceResponse<String> response = new ServiceResponse<String>("success", groupName);
        return new ResponseEntity<Object>(response, HttpStatus.OK);
    }

    @PostMapping("/teacherSupportStudent/updateGroup")
    public ResponseEntity<Object> studGroupUpdate(@RequestBody StudGroupDTO studGroupDTO, HttpSession session) {
        studGroupService.goStudGroupUpdate(studGroupDTO, session, userSecurityDataService, personService);

        ServiceResponse<StudGroupDTO> response = new ServiceResponse<StudGroupDTO>("success", studGroupDTO);
        return new ResponseEntity<Object>(response, HttpStatus.OK);
    }


    @PostMapping("/teacherSupportStudent/remoteResourceAdd")
    public ResponseEntity<Object> remoteResourceAdd(@RequestBody RemoteStudGroupResourceDTO remoteStudGroupResourceDTO, HttpSession session) {
        remoteResourceService.goAddRemoteResource(remoteStudGroupResourceDTO, session, studGroupService, personService, userSecurityDataService);
        ServiceResponse<RemoteStudGroupResourceDTO> response = new ServiceResponse<RemoteStudGroupResourceDTO>("success", remoteStudGroupResourceDTO);
        return new ResponseEntity<Object>(response, HttpStatus.OK);
    }

    @PostMapping("/teacherSupportStudent/deleteRemoteResource")
    public ResponseEntity<Object> remoteResourceDelete(@RequestBody Integer remoteResourceId, HttpSession session) {
        remoteResourceService.goDeleteStudGroupRemoteResource(remoteResourceId, session, studGroupService, personService, userSecurityDataService);
        ServiceResponse<Integer> response = new ServiceResponse<Integer>("success", remoteResourceId);
        return new ResponseEntity<Object>(response, HttpStatus.OK);
    }
}
