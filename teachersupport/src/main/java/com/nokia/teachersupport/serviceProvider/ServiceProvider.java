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


import org.springframework.stereotype.Service;

@Service
public class ServiceProvider implements IServiceProvider {
    private IPersonService personService;
    private IUserSecurityDataService userSecurityDataService;
    private IFacultyService facultyService;
    private IMeetMeService meetMeService;
    private IRoleService roleService;
    private IStudGroupService studGroupService;
    private IPublicationService publicationsService;
    private INewsService newsService;
    private IGroupRemoteResourceService remoteResourceService;
    private ITokenService tokenService;
    private IEditProfileService editProfileService;
    private IModelService modelService;
    private IFileService fileService;
    private IFileStorage fileStorage;
    private IContextService contextService;

    public ServiceProvider(IPersonService personService,IUserSecurityDataService userSecurityDataService,IFacultyService facultyService,IMeetMeService meetMeService,IRoleService roleService,
                           IStudGroupService studGroupService,IPublicationService publicationsService,INewsService newsService,IGroupRemoteResourceService remoteResourceService,
                           ITokenService tokenService,IEditProfileService editProfileService,IModelService modelService,IFileService fileService,IFileStorage fileStorage,IContextService contextService) {

        this.personService = personService;
        this.userSecurityDataService=userSecurityDataService;
        this.facultyService=facultyService;
        this.meetMeService=meetMeService;
        this.roleService=roleService;
        this.studGroupService=studGroupService;
        this.publicationsService=publicationsService;
        this.newsService=newsService;
        this.remoteResourceService=remoteResourceService;
        this.tokenService=tokenService;
        this.editProfileService=editProfileService;
        this.modelService=modelService;
        this.fileService=fileService;
        this.fileStorage=fileStorage;
        this.contextService=contextService;
    }

    @Override
    public IPersonService getIPersonService() {
        return this.personService;
    }

    @Override
    public IUserSecurityDataService getIUserSecurityDataService() {
        return this.userSecurityDataService;
    }

    @Override
    public IFacultyService getIFacultyService() {
        return this.facultyService;
    }

    @Override
    public IMeetMeService getIMeetMeService() {
        return this.meetMeService;
    }

    @Override
    public IRoleService getIRoleService() {
        return this.roleService;
    }

    @Override
    public IStudGroupService getIStudGroupService() {
        return this.studGroupService;
    }

    @Override
    public IPublicationService getIPublicationService() {
        return this.publicationsService;
    }

    @Override
    public INewsService getINewsService() {
        return this.newsService;
    }

    @Override
    public IGroupRemoteResourceService getIGroupRemoteResourceService() {
        return this.remoteResourceService;
    }

    @Override
    public ITokenService getITokenService() {
        return tokenService;
    }

    @Override
    public IEditProfileService getIEditProfileService() {
        return editProfileService;
    }

    @Override
    public IModelService getIModelService() {
        return modelService;
    }

    @Override
    public IFileService getIFileService() {
        return fileService;
    }

    @Override
    public IFileStorage getIFileStorage() {
        return fileStorage;
    }

    @Override
    public IContextService getIContextService() {
        return contextService;
    }
}
