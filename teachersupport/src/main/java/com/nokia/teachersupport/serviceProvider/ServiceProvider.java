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
import com.nokia.teachersupport.publications.IPublicationsService;
import com.nokia.teachersupport.roles.IRoleService;
import com.nokia.teachersupport.studGroup.IGroupRemoteResourceService;
import com.nokia.teachersupport.studGroup.IStudGroupService;
import org.aspectj.weaver.patterns.ITokenSource;
import org.aspectj.weaver.patterns.IVerificationRequired;
import org.springframework.stereotype.Service;

@Service
public class ServiceProvider implements IServiceProvider {
    private IPersonService personService;
    private IUserSecurityDataService userSecurityDataService;
    private IFacultyService facultyService;
    private IMeetMeService meetMeService;
    private IRoleService roleService;
    private IStudGroupService studGroupService;
    private IPublicationsService publicationsService;
    private INewsService newsService;
    private IGroupRemoteResourceService remoteResourceService;
    private ITokenSource tokenSource;
    private IEditProfileService editProfileService;
    private IModelService modelService;
    private IFileService fileService;
    private IFileStorage fileStorage;
    private IContextService contextService;

    public ServiceProvider(IPersonService personService,IUserSecurityDataService userSecurityDataService,IFacultyService facultyService,IMeetMeService meetMeService,IRoleService roleService,
                           IStudGroupService studGroupService,IPublicationsService publicationsService,INewsService newsService,IGroupRemoteResourceService remoteResourceService,
                           ITokenSource tokenSource,IEditProfileService editProfileService,IModelService modelService,IFileService fileService,IFileStorage fileStorage,IContextService contextService) {

        this.personService = personService;
        this.userSecurityDataService=userSecurityDataService;
        this.facultyService=facultyService;
        this.meetMeService=meetMeService;
        this.roleService=roleService;
        this.studGroupService=studGroupService;
        this.publicationsService=publicationsService;
        this.newsService=newsService;
        this.remoteResourceService=remoteResourceService;
        this.tokenSource=tokenSource;
        this.editProfileService=editProfileService;
        this.modelService=modelService;
        this.fileService=fileService;
        this.fileStorage=fileStorage;
        this.contextService=contextService;
    }
}
