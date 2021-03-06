package com.nokia.teachersupport.studGroup;

import com.nokia.teachersupport.serviceProvider.IServiceProvider;
import com.nokia.teachersupport.person.Person;
import com.nokia.teachersupport.person.ServiceResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

@RestController
public class StudGroupRESTController {


    private IServiceProvider serviceProvider;

    @Autowired
    public StudGroupRESTController(IServiceProvider serviceProvider) {
        this.serviceProvider=serviceProvider;
    }


    @PostMapping("/teacherSupportStudent/edit")
    public ResponseEntity<Object> editGroup(@RequestBody String groupName, HttpSession session) {
        Person person = serviceProvider.getIPersonService().getCurrentPerson(serviceProvider);
        session.setAttribute("currentStudGroupName", person.doIHaveAGroupWithName(groupName).getGroupNameField());
        ServiceResponse<String> response = new ServiceResponse<String>("success", groupName);
        return new ResponseEntity<Object>(response, HttpStatus.OK);
    }

    @PostMapping("/teacherSupportStudent/delete")
    public ResponseEntity<Object> deleteGroup(@RequestBody String groupName, HttpSession session) {
        Person person=serviceProvider.getIPersonService().getCurrentPerson(serviceProvider);
        serviceProvider.getIStudGroupService().deleteStudGroup(person,groupName,serviceProvider,session);
        ServiceResponse<String> response = new ServiceResponse<String>("success", groupName);
        return new ResponseEntity<Object>(response, HttpStatus.OK);
    }

    @PostMapping("/teacherSupportStudent/updateGroup")
    public ResponseEntity<Object> studGroupUpdate(@RequestBody StudGroupDTO studGroupDTO, HttpSession session) {
        if(serviceProvider.getIStudGroupService().checkStudGroupDTOIntegrity(studGroupDTO)) {
            serviceProvider.getIStudGroupService().goStudGroupUpdate(studGroupDTO, session, serviceProvider);
            ServiceResponse<StudGroupDTO> response = new ServiceResponse<StudGroupDTO>("success", studGroupDTO);
            return new ResponseEntity<Object>(response, HttpStatus.OK);
        }
        ServiceResponse<StudGroupDTO> response = new ServiceResponse<StudGroupDTO>("error", studGroupDTO);
        return new ResponseEntity<Object>(response, HttpStatus.OK);
    }


    @PostMapping("/teacherSupportStudent/remoteResourceAdd")
    public ResponseEntity<Object> remoteResourceAdd(@RequestBody RemoteStudGroupResourceDTO remoteStudGroupResourceDTO, HttpSession session) {
        serviceProvider.getIGroupRemoteResourceService().goAddRemoteResource(remoteStudGroupResourceDTO, session,serviceProvider);
        ServiceResponse<RemoteStudGroupResourceDTO> response = new ServiceResponse<RemoteStudGroupResourceDTO>("success", remoteStudGroupResourceDTO);
        return new ResponseEntity<Object>(response, HttpStatus.OK);
    }

    @PostMapping("/teacherSupportStudent/deleteRemoteResource")
    public ResponseEntity<Object> remoteResourceDelete(@RequestBody Integer remoteResourceId, HttpSession session) {
        serviceProvider.getIGroupRemoteResourceService().goDeleteStudGroupRemoteResource(remoteResourceId, session,serviceProvider);
        ServiceResponse<Integer> response = new ServiceResponse<Integer>("success", remoteResourceId);
        return new ResponseEntity<Object>(response, HttpStatus.OK);
    }
}
