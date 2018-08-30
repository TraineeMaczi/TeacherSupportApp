package com.nokia.teachersupport.studGroup;

import com.nokia.teachersupport.fileUpload.FileModel;
import com.nokia.teachersupport.fileUpload.IFileService;
import com.nokia.teachersupport.person.IPersonService;
import com.nokia.teachersupport.person.Person;
import com.nokia.teachersupport.personSecurity.IUserSecurityDataService;
import com.nokia.teachersupport.tools.CurrentUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.List;

@Service
public class StudGroupServiceImpl implements IStudGroupService {

    private StudGroupRepo studGroupRepo;


    @Autowired
    public void setStudGroupRepo(StudGroupRepo studGroupRepo) {
        this.studGroupRepo=studGroupRepo;
    }


    @Override
    public void deleteStudGroupById(Integer studGroupId) {
        studGroupRepo.deleteById(studGroupId);
    }

    @Override
    public StudGroup saveStudGroup(StudGroup studGroup) {
        return studGroupRepo.save(studGroup);
    }

    @Override
    public StudGroup getStudGroupByName(String studGroupName) {
        return studGroupRepo.findByGroupNameField(studGroupName);
    }

    @Override
    public List<StudGroup> listOfAllGroups() {
        return studGroupRepo.findAll();
    }

    @Override
    public void studGroupDTOIntoStudGroup(StudGroupDTO studGroupDTO,StudGroup studGroup) {
        if(!studGroupDTO.getGroupNameField().equals("")) {
            studGroup.setGroupNameField(studGroupDTO.getGroupNameField());
            studGroup.setGroupNrFiled(studGroupDTO.getGroupNrFiled());
            studGroup.setFacultyField(studGroupDTO.getFacultyField()); //tu by bylo fajnie zeby faculty nie wpisywac tylko pobrac z listy
            studGroup.setClassNameField(studGroupDTO.getClassNameField());
            studGroup.setClassDayFiled(studGroupDTO.getClassDayFiled());
            studGroup.setTimeFromFieldH(((studGroupDTO.getTimeFromFieldH().length()==1)?"0":"")+studGroupDTO.getTimeFromFieldH());
            studGroup.setTimeFromFieldM(((studGroupDTO.getTimeFromFieldM().length()==1)?"0":"")+studGroupDTO.getTimeFromFieldM());
            studGroup.setTimeToFieldH(((studGroupDTO.getTimeToFieldH().length()==1)?"0":"")+studGroupDTO.getTimeToFieldH());
            studGroup.setTimeToFieldM(((studGroupDTO.getTimeToFieldM().length()==1)?"0":"")+studGroupDTO.getTimeToFieldM());
        }
    }

    @Override
    public StudGroup addStudGroup(StudGroup studGroup, IPersonService personService, IUserSecurityDataService userSecurityDataService) {
        Person person=personService.getPersonByUserSecurityData(userSecurityDataService.getUserSecurityDataByEmail(CurrentUser.getCurrentUserName()));

//        if(studGroup.getGroupNameField()==null || studGroup.getGroupNameField().equals(""))
//        {
//        }

        if(person.doIHaveAGroupWithName(studGroup.getGroupNameField())==null) {
            studGroup.setGroupsOwner(person);
            person.addGroupsToMyList(studGroup);

            personService.savePerson(person);
            studGroupRepo.save(studGroup);
        }
        return studGroup;
    }

    @Override
    public void deleteStudGroup(String groupName, IPersonService personService, IFileService fileService,
                                IGroupRemoteResourceService remoteResourceService, IUserSecurityDataService userSecurityDataService, HttpSession session) {
        Person person = personService.getPersonByUserSecurityData(userSecurityDataService.getUserSecurityDataByEmail(CurrentUser.getCurrentUserName()));
        StudGroup studGroup=person.doIHaveAGroupWithName(groupName);

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
        studGroupRepo.delete(studGroup);
        personService.savePerson(person);
//        session.setAttribute("currentStudGroupName",null);
    }

    @Override
    public void goStudGroupUpdate(StudGroupDTO studGroupDTO,HttpSession session,IUserSecurityDataService userSecurityDataService,IPersonService personService) {
            String groupName = (String) session.getAttribute("currentStudGroupName");
        Person person = personService.getPersonByUserSecurityData(userSecurityDataService.getUserSecurityDataByEmail(CurrentUser.getCurrentUserName()));
            if(person.doIHaveAGroupWithName(groupName) !=null) {
                StudGroup studGroup = person.doIHaveAGroupWithName(groupName);
                studGroupDTOIntoStudGroup(studGroupDTO, studGroup);
                studGroupRepo.save(studGroup);
                session.setAttribute("currentStudGroupName",studGroup.getGroupNameField());
            }
    }
}
