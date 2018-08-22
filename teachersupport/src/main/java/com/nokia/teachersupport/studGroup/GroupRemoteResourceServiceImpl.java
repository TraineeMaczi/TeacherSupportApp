package com.nokia.teachersupport.studGroup;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GroupRemoteResourceServiceImpl implements IGroupRemoteResourceService {

    private GroupRemoteResourceRepo remoteResourceRepo;

    @Autowired
    public GroupRemoteResourceServiceImpl(GroupRemoteResourceRepo remoteResourceRepo) {
        this.remoteResourceRepo = remoteResourceRepo;
    }

    @Override
    public GroupRemoteResource resourceDTOIntoResource(RemoteStudGroupResourceDTO remoteStudGroupResourceDTO) {
        GroupRemoteResource groupRemoteResource=new GroupRemoteResource();
        groupRemoteResource.setStudGroupResourceName(remoteStudGroupResourceDTO.getName());
        groupRemoteResource.setResourceLink(remoteStudGroupResourceDTO.getLink());
        return groupRemoteResource;
    }

    @Override
    public GroupRemoteResource saveRemoteResource(GroupRemoteResource remoteResource) {
        return remoteResourceRepo.save(remoteResource);
    }

    @Override
    public GroupRemoteResource findRemoteResourceById(Integer remoteResourceId) {
        return remoteResourceRepo.findById(remoteResourceId).orElse(new GroupRemoteResource());
    }

    @Override
    public void deleteRemoteResource(GroupRemoteResource remoteResource) {
        remoteResourceRepo.delete(remoteResource);
    }


}
