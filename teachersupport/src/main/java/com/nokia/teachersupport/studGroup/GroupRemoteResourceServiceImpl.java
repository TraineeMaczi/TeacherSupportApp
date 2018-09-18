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
        Person person = serviceProvider.getIPersonService().getCurrentPerson(serviceProvider);
        StudGroup studGroup = person.doIHaveAGroupWithName(groupName);
        studGroup.getGroupsResourcesList().remove(remoteResource);
        serviceProvider.getIStudGroupService().saveStudGroup(studGroup);
        deleteRemoteResource(remoteResource);
    }


    @Override
    public RemoteStudGroupResourceDTO goAddRemoteResource(RemoteStudGroupResourceDTO remoteStudGroupResourceDTO, HttpSession session,IServiceProvider serviceProvider) {
        GroupRemoteResource remoteResource = resourceDTOIntoResource(remoteStudGroupResourceDTO);
        String groupName = (String) session.getAttribute("currentStudGroupName");
        if (groupName != null && !groupName.equals("")) {
            Person person = serviceProvider.getIPersonService().getCurrentPerson(serviceProvider);
            StudGroup studGroup = person.doIHaveAGroupWithName(groupName);
            studGroup.addResourcesToMyList(remoteResource);
            remoteResource.setResourceOwner(studGroup);
            remoteResourceRepo.save(remoteResource);
            serviceProvider.getIStudGroupService().saveStudGroup(studGroup);
        }
        return remoteStudGroupResourceDTO;
    }


}
