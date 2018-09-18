package com.nokia.teachersupport.studGroup;

import com.nokia.teachersupport.person.Person;
import com.nokia.teachersupport.serviceProvider.IServiceProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.util.List;

@Service
public class StudGroupServiceImpl implements IStudGroupService {

    private StudGroupRepo studGroupRepo;


    @Autowired
    public void setStudGroupRepo(StudGroupRepo studGroupRepo) {
        this.studGroupRepo = studGroupRepo;
    }


    @Override
    public StudGroup saveStudGroup(StudGroup studGroup) {
        return studGroupRepo.save(studGroup);
    }


    @Override
    public void studGroupDTOIntoStudGroup(StudGroupDTO studGroupDTO, StudGroup studGroup) {
        if (!studGroupDTO.getGroupNameField().equals("")) {
            studGroup.setGroupNameField(studGroupDTO.getGroupNameField());
            studGroup.setGroupNrFiled(studGroupDTO.getGroupNrFiled());
            studGroup.setFacultyField(studGroupDTO.getFacultyField()); //tu by bylo fajnie zeby faculty nie wpisywac tylko pobrac z listy
            studGroup.setClassNameField(studGroupDTO.getClassNameField());
            studGroup.setClassDayFiled(studGroupDTO.getClassDayFiled());
            studGroup.setTimeFromFieldH(((studGroupDTO.getTimeFromFieldH().length() == 1) ? "0" : "") + studGroupDTO.getTimeFromFieldH());
            studGroup.setTimeFromFieldM(((studGroupDTO.getTimeFromFieldM().length() == 1) ? "0" : "") + studGroupDTO.getTimeFromFieldM());
            studGroup.setTimeToFieldH(((studGroupDTO.getTimeToFieldH().length() == 1) ? "0" : "") + studGroupDTO.getTimeToFieldH());
            studGroup.setTimeToFieldM(((studGroupDTO.getTimeToFieldM().length() == 1) ? "0" : "") + studGroupDTO.getTimeToFieldM());
        }
    }

    @Override
    public StudGroup addStudGroup(StudGroup studGroup, IServiceProvider serviceProvider) {
        Person person = serviceProvider.getIPersonService().getCurrentPerson(serviceProvider);
        if (person.doIHaveAGroupWithName(studGroup.getGroupNameField()) == null) {
            studGroup.setGroupsOwner(person);
            person.addGroupsToMyList(studGroup);
            serviceProvider.getIPersonService().savePerson(person);
            studGroupRepo.save(studGroup);
        }
        return studGroup;
    }

    @Override
    public void deleteStudGroup(Person person, String groupName,IServiceProvider serviceProvider,HttpSession session) {

        StudGroup studGroup = person.doIHaveAGroupWithName(groupName);

        if (studGroup != null) {
            person.deleteStudGroup(studGroup);
            if (studGroup.getGroupsResourcesList() != null) {
                for (GroupRemoteResource remoteResource : studGroup.getGroupsResourcesList()) {
                    remoteResource.deleteResourceOwner();
                    serviceProvider.getIGroupRemoteResourceService().deleteRemoteResource(remoteResource);
                }
            }
            studGroup.getGroupsResourcesList().clear();


            person.deleteStudGroup(studGroup);
            studGroupRepo.delete(studGroup);
            serviceProvider.getIPersonService().savePerson(person);
        }
        //        session.setAttribute("currentStudGroupName",null);
    }

    @Override
    public void goStudGroupUpdate(StudGroupDTO studGroupDTO, HttpSession session, IServiceProvider serviceProvider) {
        String groupName = (String) session.getAttribute("currentStudGroupName");
        Person person = serviceProvider.getIPersonService().getCurrentPerson(serviceProvider);
        if (person.doIHaveAGroupWithName(groupName) != null) {
            StudGroup studGroup = person.doIHaveAGroupWithName(groupName);
            studGroupDTOIntoStudGroup(studGroupDTO, studGroup);
            studGroupRepo.save(studGroup);
            session.setAttribute("currentStudGroupName", studGroup.getGroupNameField());
        }
    }

    @Override
    public List<StudGroup> cleanMyStudGrops(Person person,IServiceProvider serviceProvider, HttpSession session) {
        List<StudGroup> studGroupsPersonList = person.getPersonStudGroupList();
        for (Integer i = 0; !studGroupsPersonList.isEmpty(); ) {
            StudGroup sd = studGroupsPersonList.get(i);
            deleteStudGroup(person, sd.getGroupNameField(), serviceProvider, session);

        }
        return studGroupsPersonList;
    }

    @Override
    public boolean checkStudGroupDTOIntegrity(StudGroupDTO studGroupDTO) {
        if (studGroupDTO.getGroupNameField().equals("") || studGroupDTO.getGroupNrFiled().equals("") || studGroupDTO.getClassNameField().equals("") || studGroupDTO.getTimeFromFieldH().equals("") ||
                studGroupDTO.getTimeFromFieldM().equals("") || studGroupDTO.getTimeToFieldM().equals("") || studGroupDTO.getTimeToFieldH().equals("") || studGroupDTO.getClassDayFiled().equals("")) {
            return false;
        } else {
            try {
                double fromH = Double.parseDouble(studGroupDTO.getTimeFromFieldH());
                double fromM = Double.parseDouble(studGroupDTO.getTimeFromFieldM());
                double toH = Double.parseDouble(studGroupDTO.getTimeToFieldH());
                double toM = Double.parseDouble(studGroupDTO.getTimeToFieldM());
                if (toM < 0 || toH < 0 || fromH < 0 || fromM < 0) {
                    return false;
                }
                if (toM > 60 || toH > 24 || fromH > 24 || fromM > 60) {
                    return false;
                }
                if (fromH * 60 + fromM > toH * 60 + toM)
                    return false;

            } catch (NumberFormatException nfe) {
                return false;
            }
            return true;
        }
    }
}
