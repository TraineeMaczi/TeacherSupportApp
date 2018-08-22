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
//    private StudGroup studGroupLocalInstanc; //nieladnie silna zelznosc


    @Autowired
    public StudGroupRESTController(IGroupRemoteResourceService remoteResourceService,IFileService fileService,IStudGroupService studGroupService, IPersonService personService, IMeetMeService meetMeService, IUserSecurityDataService userSecurityDataService) {
        this.personService = personService;
        this.userSecurityDataService = userSecurityDataService;
        this.studGroupService = studGroupService;
        this.fileService=fileService;
        this.remoteResourceService=remoteResourceService;
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
    public ResponseEntity<Object> deleteGroup(@RequestBody String groupName,HttpSession session) {
        Person person = personService.getPersonByUserSecurityData(userSecurityDataService.getUserSecurityDataByEmail(CurrentUser.getCurrentUserName()));
        StudGroup studGroup=studGroupService.getStudGroupByName(groupName);
        person.deleteStudGroup(studGroup);
        for(FileModel fileModel:studGroup.getFileModels())
        {
            fileModel.setFilesOfGroup(null);
            fileService.dleteFileById(fileModel.getId());

        }

        for(GroupRemoteResource remoteResource:studGroup.getGroupsResourcesList())
        {
            remoteResource.deleteResourceOwner();
            remoteResourceService.deleteRemoteResource(remoteResource);
        }
        studGroup.getGroupsResourcesList().removeAll(studGroup.getGroupsResourcesList());
        studGroup.getFileModels().removeAll(studGroup.getFileModels());
        studGroupService.deleteStudGroupById(studGroup.getId());
        personService.savePerson(person);
        session.setAttribute("currentStudGroupName",null);
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
    @PostMapping("/teacherSupportStudent/deleteLocalResource")
    public ResponseEntity<Object> deleteGroupLocalResource(@RequestBody Integer id,HttpSession session) {
   Person person = personService.getPersonByUserSecurityData(userSecurityDataService.getUserSecurityDataByEmail(CurrentUser.getCurrentUserName()));
        String groupName=(String)session.getAttribute("currentStudGroupName");
        FileModel file=fileService.findFileById(id);
        if(groupName !=null)
        {
            StudGroup studGroup=studGroupService.getStudGroupByName(groupName);
            studGroup.getFileModels().remove(file);
            fileService.dleteFileById(id);
            studGroupService.saveStudGroup(studGroup);
            session.setAttribute("currentStudGroupName",null);

        }
        ServiceResponse<Integer> response = new ServiceResponse<Integer>("success", id );
        return new ResponseEntity<Object>(response, HttpStatus.OK);
    }

    @PostMapping("/teacherSupportStudent/remoteResourceAdd")
    public ResponseEntity<Object> remoteResourceAdd(@RequestBody RemoteStudGroupResourceDTO remoteStudGroupResourceDTO,HttpSession session) {
        GroupRemoteResource remoteResource=remoteResourceService.resourceDTOIntoResource(remoteStudGroupResourceDTO);
        String groupName=(String)session.getAttribute("currentStudGroupName");
        StudGroup studGroup=studGroupService.getStudGroupByName(groupName);
        studGroup.addResourcesToMyList(remoteResource);
        remoteResource.setResourceOwner(studGroup);
        remoteResourceService.saveRemoteResource(remoteResource);
        studGroupService.saveStudGroup(studGroup);
        ServiceResponse<RemoteStudGroupResourceDTO> response = new ServiceResponse<RemoteStudGroupResourceDTO>("success", remoteStudGroupResourceDTO);
        return new ResponseEntity<Object>(response, HttpStatus.OK);
    }

    @PostMapping("/teacherSupportStudent/deleteRemoteResource")
    public ResponseEntity<Object> remoteResourceDelete(@RequestBody Integer remoteResourceId,HttpSession session) {
        GroupRemoteResource remoteResource=remoteResourceService.findRemoteResourceById(remoteResourceId);
        String groupName=(String)session.getAttribute("currentStudGroupName");
        StudGroup studGroup=studGroupService.getStudGroupByName(groupName);
        studGroup.getGroupsResourcesList().remove(remoteResource);
        studGroupService.saveStudGroup(studGroup);
        remoteResourceService.deleteRemoteResource(remoteResource);
        ServiceResponse<Integer> response = new ServiceResponse<Integer>("success", remoteResourceId);
        return new ResponseEntity<Object>(response, HttpStatus.OK);
    }
}
