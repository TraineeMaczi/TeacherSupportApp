package com.nokia.teachersupport.studGroup;

import com.nokia.teachersupport.fileUpload.IFileService;
import com.nokia.teachersupport.person.IPersonService;
import com.nokia.teachersupport.personSecurity.IUserSecurityDataService;
import com.nokia.teachersupport.serviceProvider.IServiceProvider;

import javax.servlet.http.HttpSession;

public interface IGroupRemoteResourceService {
    GroupRemoteResource resourceDTOIntoResource(RemoteStudGroupResourceDTO remoteStudGroupResourceDTO);
    GroupRemoteResource findRemoteResourceById(Integer remoteResourceId);
    void deleteRemoteResource(GroupRemoteResource remoteResource);
    void goDeleteStudGroupRemoteResource(Integer remoteResourceId, HttpSession session, IServiceProvider serviceProvider);
    RemoteStudGroupResourceDTO goAddRemoteResource(RemoteStudGroupResourceDTO remoteStudGroupResourceDTO,HttpSession session,IServiceProvider serviceProvider);

}
