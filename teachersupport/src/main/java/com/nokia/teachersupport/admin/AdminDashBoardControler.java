package com.nokia.teachersupport.admin;


import com.nokia.teachersupport.currentUser.CurrentUser;
import com.nokia.teachersupport.faculty.Faculty;
import com.nokia.teachersupport.fileUpload.FileModel;
import com.nokia.teachersupport.fileUpload.IFileService;
import com.nokia.teachersupport.person.IPersonService;
import com.nokia.teachersupport.person.Person;
import com.nokia.teachersupport.personSecurity.UserSecurityData;
import com.nokia.teachersupport.roles.SecutityRole;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;
import java.util.Objects;


@Controller
public class AdminDashBoardControler {

    private IAdminDashboardService adminDashboardService;
    private IFileService fileService;
    private IPersonService personService;
    @Autowired
    public AdminDashBoardControler(IAdminDashboardService adminDashboardsSrvice,IFileService fileService,IPersonService personService) {
        this.adminDashboardService = adminDashboardsSrvice;
        this.fileService=fileService;
        this.personService=personService;
    }

    @PreAuthorize("hasAnyRole('ADMIN')")
    @GetMapping("/teacherSupportAdminDashboard")
    String dash(Model model) {
        adminDashboardService.dashModel(model);
        return "teacherSupportAdminDashboard";
    }

    @PreAuthorize("hasAnyRole('ADMIN')")
    @PostMapping("/teacherSupportAdminDashboard/newUserAdminAction")
    String addNewUser(UserDTOForAdminAction userDTOForAdminActionDTO) {
        adminDashboardService.addUser(userDTOForAdminActionDTO);
        return "redirect:/teacherSupportAdminDashboard";
    }

    @PreAuthorize("hasAnyRole('ADMIN')")
    @PostMapping("/teacherSupportAdminDashboard/newFacultyAdminAction")
    String addNewFaculty(Faculty faculty) {
        if (adminDashboardService.getFacultyByName(faculty.getFacultyNameField()) == null) {
            adminDashboardService.saveUserFacultyDataAdminAction(faculty);
        }
        return "redirect:/teacherSupportAdminDashboard";
    }



}