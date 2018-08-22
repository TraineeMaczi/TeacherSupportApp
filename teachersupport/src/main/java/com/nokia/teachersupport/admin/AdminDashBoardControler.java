package com.nokia.teachersupport.admin;


import com.nokia.teachersupport.fileUpload.IFileService;
import com.nokia.teachersupport.model.IModelService;
import com.nokia.teachersupport.person.IPersonService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
public class AdminDashBoardControler {

    private IFileService fileService;

    private IPersonService personService;

    private IModelService modelService;
    @Autowired
    public AdminDashBoardControler(IFileService fileService, IPersonService personService, IModelService modelService) {

        this.fileService = fileService;
        this.personService = personService;
        this.modelService=modelService;
    }

    @PreAuthorize("hasAnyRole('ADMIN')")
    @GetMapping("/teacherSupportAdminDashboard")
    String dash(Model model) {
        modelService.adminDashboardModel(model);
        return "teacherSupportAdminDashboard";
    }

    @PreAuthorize("hasAnyRole('ADMIN')")
    @PostMapping("/teacherSupportAdminDashboard/newUserAdminAction")
    String addNewUser(UserDTOForAdminAction userDTOForAdminActionDTO) {
        personService.addUser(userDTOForAdminActionDTO);
        return "redirect:/teacherSupportAdminDashboard";
    }
    @PreAuthorize("hasAnyRole('ADMIN')")
    @GetMapping("/deleteAll")
    public String deleteAll() {
        personService.deleteAllPersons();
        return "redirect:/teacherSupportAdminDashboard";
    }

    @PreAuthorize("hasAnyRole('ADMIN')")
    @PostMapping("/teacherSupportAdminDashboard/deleteUser")
    public String deleteUser(@RequestParam("userId") Integer userId) {
        personService.deletePerson(personService.getPerson(userId));
        return"redirect:/teacherSupportAdminDashboard";
    }


}