package com.nokia.teachersupport.admin;


import com.nokia.teachersupport.file.File;
import com.nokia.teachersupport.person.ServiceResponse;
import com.nokia.teachersupport.serviceProvider.IServiceProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;


@RestController
public class AdminDashBoardRestController {
    private IServiceProvider serviceProvider;

    @Autowired
    public AdminDashBoardRestController(IServiceProvider serviceProvider) {
     this.serviceProvider=serviceProvider;
    }

    //Te zostawiam tak jak jest
    @PreAuthorize("hasAnyRole('ADMIN')")
    @PostMapping("/teacherSupportAdminDashboard/newUserAdminActionFromFile")
    String addNewUsersFromFile(@RequestParam("uploadfile") MultipartFile file) throws IOException {
        if (serviceProvider.getIPersonService().savePersonsFromFile(file.getInputStream(),serviceProvider))
            return "SUCCES";
        return "FAIL!";
    }

    @PreAuthorize("hasAnyRole('ADMIN')")
    @PostMapping("/addFaculty/{facultyName}")
    public String uploadMultipartFile(@RequestParam("uploadFile") MultipartFile file, @PathVariable("facultyName") String facultyName) throws IOException {
        if(file.isEmpty())
            return "Firstly you must upload faculty photo";
        File fileModel = serviceProvider.getIFileService().saveMultipartFile(file, "facultyFoto",serviceProvider);
        serviceProvider.getIFileService().goUploadMultipartFile(fileModel,facultyName,serviceProvider);
        return "SUCCES";
    }

    @PreAuthorize("hasAnyRole('ADMIN')")
    @PostMapping("/teacherSupportAdminDashboard/deleteFacultyAdminAction")
    public ResponseEntity<Object> deleteFacultySiteAction(@RequestBody String facultyName) {

       serviceProvider.getIFacultyService().goDeleteFacultySiteAction(facultyName,serviceProvider);
        ServiceResponse<String> response = new ServiceResponse<String>("success", facultyName);
        return new ResponseEntity<Object>(response, HttpStatus.OK);
    }

}


