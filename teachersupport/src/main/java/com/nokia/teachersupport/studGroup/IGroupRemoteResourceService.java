package com.nokia.teachersupport.studGroup;

import com.nokia.teachersupport.fileUpload.IFileService;
import com.nokia.teachersupport.person.IPersonService;
import com.nokia.teachersupport.personSecurity.IUserSecurityDataService;

import javax.servlet.http.HttpSession;

public interface IGroupRemoteResourceService {
    GroupRemoteResource resourceDTOIntoResource(RemoteStudGroupResourceDTO remoteStudGroupResourceDTO);
    GroupRemoteResource saveRemoteResource(GroupRemoteResource remoteResource);
    GroupRemoteResource findRemoteResourceById(Integer remoteResourceId);
    void deleteRemoteResource(GroupRemoteResource remoteResource);
    void goDeleteStudGroupRemoteResource(Integer remoteResourceId, HttpSession session,IStudGroupService studGroupService);
    RemoteStudGroupResourceDTO goAddRemoteResource(RemoteStudGroupResourceDTO remoteStudGroupResourceDTO,HttpSession session,IStudGroupService studGroupService);

}
