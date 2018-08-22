package com.nokia.teachersupport.studGroup;

public interface IGroupRemoteResourceService {
    GroupRemoteResource resourceDTOIntoResource(RemoteStudGroupResourceDTO remoteStudGroupResourceDTO);
    GroupRemoteResource saveRemoteResource(GroupRemoteResource remoteResource);
    GroupRemoteResource findRemoteResourceById(Integer remoteResourceId);
    void deleteRemoteResource(GroupRemoteResource remoteResource);
}
