package com.nokia.teachersupport.serviceProvider;

import com.nokia.teachersupport.context.IContextService;
import com.nokia.teachersupport.faculty.IFacultyService;
import com.nokia.teachersupport.file.IFileService;
import com.nokia.teachersupport.fileStorage.IFileStorageService;
import com.nokia.teachersupport.modelAttributeProvider.IModelAttributeProviderService;
import com.nokia.teachersupport.newsP.INewsService;
import com.nokia.teachersupport.person.IMeetMeService;
import com.nokia.teachersupport.person.IPersonService;
import com.nokia.teachersupport.personSecurity.IUserSecurityDataService;
import com.nokia.teachersupport.personSecurity.personEditProfile.IEditProfileService;
import com.nokia.teachersupport.personSecurity.personRegister.verificationToken.IVerificationTokenService;
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
    IVerificationTokenService getITokenService();
    IEditProfileService getIEditProfileService();
    IModelAttributeProviderService getIModelService();
    IFileService getIFileService();
    IFileStorageService getIFileStorage();
    IContextService getIContextService();
}
