package com.nokia.teachersupport.admin;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;


@RestController
public class AdminDashBoardRestController {
    @Autowired
    private IAdminDashboardService adminDashboardService;

    @PostMapping("/teacherSupportAdminDashboard/newUserAdminActionFromFile")
    String addNewUsersFromFile(@RequestParam("uploadfile") MultipartFile file) throws IOException {
        if (adminDashboardService.saveUsersFromFile(file.getInputStream()))
            return "SUCCES";
        else
            return "FAIL!";
    }
}
