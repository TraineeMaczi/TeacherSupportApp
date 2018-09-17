package com.nokia.teachersupport.studGroup;

import com.nokia.teachersupport.person.IPersonService;
import com.nokia.teachersupport.person.Person;
import com.nokia.teachersupport.personSecurity.IUserSecurityDataService;
import com.nokia.teachersupport.serviceProvider.IServiceProvider;
import com.nokia.teachersupport.tools.CurrentUser;
import com.nokia.teachersupport.tools.GenerateLink;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;

@Service
public class GroupRemoteResourceServiceImpl implements IGroupRemoteResourceService {

    private GroupRemoteResourceRepo remoteResourceRepo;

    @Autowired
    public GroupRemoteResourceServiceImpl(GroupRemoteResourceRepo remoteResourceRepo) {
        this.remoteResourceRepo = remoteResourceRepo;
    }

    @Override
    public GroupRemoteResource resourceDTOIntoResource(RemoteStudGroupResourceDTO remoteStudGroupResourceDTO) {
        GroupRemoteResource groupRemoteResource = new GroupRemoteResource();
        groupRemoteResource.setStudGroupResourceName(remoteStudGroupResourceDTO.getName());
        groupRemoteResource.setResourceLink(GenerateLink.goGenerateLink(remoteStudGroupResourceDTO.getLink()));
        return groupRemoteResource;
    }

    @Override
    public GroupRemoteResource findRemoteResourceById(Integer remoteResourceId) {
        return remoteResourceRepo.findById(remoteResourceId).orElse(new GroupRemoteResource());
    }

    @Override
    public void deleteRemoteResource(GroupRemoteResource remoteResource) {
        remoteResourceRepo.delete(remoteResource);
    }

    @Override
    public void goDeleteStudGroupRemoteResource(Integer remoteResourceId, HttpSession session, IServiceProvider serviceProvider) {
        GroupRemoteResource remoteResource = findRemoteResourceById(remoteResourceId);
        String groupName = (String) session.getAttribute("currentStudGroupName");
        Person person = personService.getCurrentPerson(userSecurityDataService);
        StudGroup studGroup = person.doIHaveAGroupWithName(groupName);
        studGroup.getGroupsResourcesList().remove(remoteResource);
        studGroupService.saveStudGroup(studGroup);
        deleteRemoteResource(remoteResource);
    }


    @Override
    public RemoteStudGroupResourceDTO goAddRemoteResource(RemoteStudGroupResourceDTO remoteStudGroupResourceDTO, HttpSession session, IStudGroupService studGroupService, IPersonService personService, IUserSecurityDataService userSecurityDataService) {
        GroupRemoteResource remoteResource = resourceDTOIntoResource(remoteStudGroupResourceDTO);
        String groupName = (String) session.getAttribute("currentStudGroupName");
        if (groupName != null && !groupName.equals("")) {
            Person person = personService.getCurrentPerson(userSecurityDataService);
            StudGroup studGroup = person.doIHaveAGroupWithName(groupName);
            studGroup.addResourcesToMyList(remoteResource);
            remoteResource.setResourceOwner(studGroup);
            remoteResourceRepo.save(remoteResource);
            studGroupService.saveStudGroup(studGroup);
        }
        return remoteStudGroupResourceDTO;
    }


}
