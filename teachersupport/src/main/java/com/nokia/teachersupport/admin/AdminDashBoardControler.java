package com.nokia.teachersupport.admin;


import com.nokia.teachersupport.faculty.IFacultyService;
import com.nokia.teachersupport.fileUpload.IFileService;
import com.nokia.teachersupport.model.IModelService;
import com.nokia.teachersupport.newsP.INewsService;
import com.nokia.teachersupport.person.IMeetMeService;
import com.nokia.teachersupport.person.IPersonService;

import com.nokia.teachersupport.personSecurity.IUserSecurityDataService;
import com.nokia.teachersupport.personSecurity.UserSecurityData;
import com.nokia.teachersupport.publication.IPublicationService;
import com.nokia.teachersupport.roles.IRoleService;
import com.nokia.teachersupport.studGroup.IGroupRemoteResourceService;
import com.nokia.teachersupport.studGroup.IStudGroupService;
import com.nokia.teachersupport.tools.CurrentUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;


@Controller
public class AdminDashBoardControler {


    private IPersonService personService;
    private IUserSecurityDataService userSecurityDataService;
    private IModelService modelService;
    private IFacultyService facultyService;
    private IRoleService roleService;
    private IMeetMeService meetMeService;
    private INewsService newsService;
    private IPublicationService publicationsService;
    private IStudGroupService studGroupService;
    private IFileService fileService;
    private IGroupRemoteResourceService remoteResourceService;

    @Autowired
    public AdminDashBoardControler(IRoleService roleService, IFacultyService facultyService, IUserSecurityDataService userSecurityDataService,
                                   IPersonService personService, IModelService modelService, IMeetMeService meetMeService, INewsService newsService, IPublicationService publicationsService,
                                   IStudGroupService studGroupService, IFileService fileService, IGroupRemoteResourceService remoteResourceService) {
        this.roleService = roleService;
        this.facultyService = facultyService;
        this.personService = personService;
        this.modelService = modelService;
        this.userSecurityDataService = userSecurityDataService;
        this.meetMeService=meetMeService;
        this.newsService=newsService;
        this.publicationsService=publicationsService;
        this.studGroupService=studGroupService;
        this.fileService=fileService;
        this.remoteResourceService=remoteResourceService;
    }

    //  @PreAuthorize("hasAnyRole('ADMIN')")
    @GetMapping("/teacherSupportAdminDashboard")
    String dash(Model model) {
        UserSecurityData user = userSecurityDataService.getUserSecurityDataByEmail(CurrentUser.getCurrentUserName());
        if (userSecurityDataService.isAdmin(user)) {
            modelService.adminDashboardModel(model);
            return "teacherSupportAdminDashboard";
        }
        return "teacherSupportAdminDashboardInvalid";
    }

    @PreAuthorize("hasAnyRole('ADMIN')")
    @PostMapping("/teacherSupportAdminDashboard/newUserAdminAction")
    String addNewUser(UserDTOForAdminAction userDTOForAdminActionDTO) {
        personService.addUser(userDTOForAdminActionDTO, userSecurityDataService, facultyService, roleService);
        return "redirect:/teacherSupportAdminDashboard";
    }

    //Ze to cos pobiera sesje to to jest nie dobre
    @PreAuthorize("hasAnyRole('ADMIN')")
    @GetMapping("/deleteAll")
    public String deleteAll(HttpSession session) {
        personService.deleteAllPersons(userSecurityDataService,meetMeService,newsService,publicationsService,studGroupService,fileService,remoteResourceService,session);
        return "redirect:/teacherSupportAdminDashboard";
    }

    @PreAuthorize("hasAnyRole('ADMIN')")
    @PostMapping("/teacherSupportAdminDashboard/deleteUser")
    public String deleteUser(@RequestParam("userId") Integer userId,HttpSession session) {

        personService.deletePerson(personService.getPerson(userId), userSecurityDataService,meetMeService,newsService,publicationsService,studGroupService,fileService,remoteResourceService,session);
        return "redirect:/teacherSupportAdminDashboard";
    }


}