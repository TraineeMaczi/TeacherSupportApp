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
import com.nokia.teachersupport.serviceProvider.IServiceProvider;
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


   private IServiceProvider serviceProvider;

    @Autowired
    public AdminDashBoardControler(IServiceProvider serviceProvider) {
   this.serviceProvider=serviceProvider;
    }

    //  @PreAuthorize("hasAnyRole('ADMIN')")
    @GetMapping("/teacherSupportAdminDashboard")
    String dash(Model model) {
        UserSecurityData user = serviceProvider.getIUserSecurityDataService().getUserSecurityDataByEmail(CurrentUser.getCurrentUserName());
        if (serviceProvider.getIUserSecurityDataService().isAdmin(user)) {
            serviceProvider.getIModelService().adminDashboardModel(model,serviceProvider);
            return "teacherSupportAdminDashboard";
        }
        return "teacherSupportAdminDashboardInvalid";
    }

    @PreAuthorize("hasAnyRole('ADMIN')")
    @PostMapping("/teacherSupportAdminDashboard/newUserAdminAction")
    String addNewUser(UserDTOForAdminAction userDTOForAdminActionDTO) {
        serviceProvider.getIPersonService().addUser(userDTOForAdminActionDTO, serviceProvider );
        return "redirect:/teacherSupportAdminDashboard";
    }

    //Ze to cos pobiera sesje to to jest nie dobre
    @PreAuthorize("hasAnyRole('ADMIN')")
    @GetMapping("/deleteAll")
    public String deleteAll(HttpSession session) {
        serviceProvider.getIPersonService().deleteAllPersons(serviceProvider,session);
        return "redirect:/teacherSupportAdminDashboard";
    }

    @PreAuthorize("hasAnyRole('ADMIN')")
    @PostMapping("/teacherSupportAdminDashboard/deleteUser")
    public String deleteUser(@RequestParam("userId") Integer userId,HttpSession session) {

        serviceProvider.getIPersonService().deletePerson(serviceProvider.getIPersonService().getPerson(userId),serviceProvider,session);
        return "redirect:/teacherSupportAdminDashboard";
    }


}