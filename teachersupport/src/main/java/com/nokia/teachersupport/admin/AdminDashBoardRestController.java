package com.nokia.teachersupport.admin;


import com.nokia.teachersupport.faculty.Faculty;
import com.nokia.teachersupport.faculty.IFacultyService;
import com.nokia.teachersupport.fileUpload.FileModel;
import com.nokia.teachersupport.fileUpload.IFileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;


@RestController
public class AdminDashBoardRestController {
    @Autowired
    private IAdminDashboardService adminDashboardService;
    @Autowired
    private IFileService fileService;
    @Autowired
    private IFacultyService facultyService;
    @PostMapping("/teacherSupportAdminDashboard/newUserAdminActionFromFile")
    String addNewUsersFromFile(@RequestParam("uploadfile") MultipartFile file) throws IOException {
        if (adminDashboardService.saveUsersFromFile(file.getInputStream()))
            return "SUCCES";
        else
            return "FAIL!";
    }
    @PostMapping("/addFaculty/{facultyName}")
    public String uploadMultipartFile(@RequestParam("uploadFile") MultipartFile file,@PathVariable("facultyName") String facultyName) throws IOException {
        FileModel fileModel= fileService.saveMultipartFile(file, "facultyFoto");
        Faculty faculty= new Faculty();
        faculty.setFacultyNameField(facultyName);
        faculty.setFile(fileModel);
        adminDashboardService.saveUserFacultyDataAdminAction(faculty);
        return "IT WORKS :)";
    }
}
