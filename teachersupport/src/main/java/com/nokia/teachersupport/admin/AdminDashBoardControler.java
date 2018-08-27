package com.nokia.teachersupport.admin;


import com.nokia.teachersupport.faculty.IFacultyService;
import com.nokia.teachersupport.fileUpload.IFileService;
import com.nokia.teachersupport.model.IModelService;
import com.nokia.teachersupport.person.IPersonService;

import com.nokia.teachersupport.personSecurity.IUserSecurityDataService;
import com.nokia.teachersupport.personSecurity.UserSecurityData;
import com.nokia.teachersupport.roles.IRoleService;
import com.nokia.teachersupport.tools.CurrentUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
public class AdminDashBoardControler {


    private IPersonService personService;
    private IUserSecurityDataService userSecurityDataService;
    private IModelService modelService;
    private IFacultyService facultyService;
    private IRoleService roleService;

    @Autowired
    public AdminDashBoardControler(IRoleService roleService, IFacultyService facultyService, IUserSecurityDataService userSecurityDataService, IPersonService personService, IModelService modelService) {
        this.roleService = roleService;
        this.facultyService = facultyService;
        this.personService = personService;
        this.modelService = modelService;
        this.userSecurityDataService = userSecurityDataService;
    }

  //  @PreAuthorize("hasAnyRole('ADMIN')")
    @GetMapping("/teacherSupportAdminDashboard")
    String dash(Model model) {
       UserSecurityData user= userSecurityDataService.getUserSecurityDataByEmail(CurrentUser.getCurrentUserName());
       if(userSecurityDataService.isAdmin(user)) {
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

    @PreAuthorize("hasAnyRole('ADMIN')")
    @GetMapping("/deleteAll")
    public String deleteAll() {
        personService.deleteAllPersons(userSecurityDataService);
        return "redirect:/teacherSupportAdminDashboard";
    }

    @PreAuthorize("hasAnyRole('ADMIN')")
    @PostMapping("/teacherSupportAdminDashboard/deleteUser")
    public String deleteUser(@RequestParam("userId") Integer userId) {
        personService.deletePerson(personService.getPerson(userId), userSecurityDataService);
        return "redirect:/teacherSupportAdminDashboard";
    }


}