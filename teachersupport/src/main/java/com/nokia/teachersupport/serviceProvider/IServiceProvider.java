package com.nokia.teachersupport.serviceProvider;

import com.nokia.teachersupport.context.IContextService;
import com.nokia.teachersupport.faculty.IFacultyService;
import com.nokia.teachersupport.fileUpload.IFileService;
import com.nokia.teachersupport.filestorage.IFileStorage;
import com.nokia.teachersupport.model.IModelService;
import com.nokia.teachersupport.newsP.INewsService;
import com.nokia.teachersupport.person.IMeetMeService;
import com.nokia.teachersupport.person.IPersonService;
import com.nokia.teachersupport.personSecurity.IUserSecurityDataService;
import com.nokia.teachersupport.personSecurity.personEditProfile.IEditProfileService;
import com.nokia.teachersupport.personSecurity.personRegister.verificationToken.ITokenService;
import com.nokia.teachersupport.publication.IPublicationService;
import com.nokia.teachersupport.roles.IRoleService;
import com.nokia.teachersupport.studGroup.IGroupRemoteResourceService;
import com.nokia.teachersupport.studGroup.IStudGroupService;


public interface IServiceProvider {
    IPersonService getIPersonService();
    IUserSecurityDataService getIUserSecurityDataService();
    IFacultyService getIFacultyService();
    IMeetMeService getIMeetMeService();
    IRoleService getIRoleService();
    IStudGroupService getIStudGroupService();
    IPublicationService getIPublicationService();
    INewsService getINewsService();
    IGroupRemoteResourceService getIGroupRemoteResourceService();
    ITokenService getITokenService();
    IEditProfileService getIEditProfileService();
    IModelService getIModelService();
    IFileService getIFileService();
    IFileStorage getIFileStorage();
    IContextService getIContextService();
}
