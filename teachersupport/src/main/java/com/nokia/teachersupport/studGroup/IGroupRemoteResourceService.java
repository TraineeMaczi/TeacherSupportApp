package com.nokia.teachersupport.studGroup;

public interface IGroupRemoteResourceService {
    GroupRemoteResource resourceDTOIntoResource(RemoteStudGroupResourceDTO remoteStudGroupResourceDTO);
    GroupRemoteResource saveRemoteResource(GroupRemoteResource remoteResource);
}
