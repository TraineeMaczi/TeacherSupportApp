package com.nokia.teachersupport.admin;


import com.nokia.teachersupport.faculty.Faculty;
import com.nokia.teachersupport.faculty.IFacultyService;
import com.nokia.teachersupport.fileUpload.FileModel;
import com.nokia.teachersupport.fileUpload.IFileService;
import com.nokia.teachersupport.person.Person;
import com.nokia.teachersupport.person.ServiceResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;


@RestController
public class AdminDashBoardRestController {
    @Autowired
    private IAdminDashboardService adminDashboardService;
    @Autowired
    private IFileService fileService;
    @Autowired
    private IFacultyService facultyService;

    @PreAuthorize("hasAnyRole('ADMIN')")
    @PostMapping("/teacherSupportAdminDashboard/newUserAdminActionFromFile")
    String addNewUsersFromFile(@RequestParam("uploadfile") MultipartFile file) throws IOException {
        if (adminDashboardService.saveUsersFromFile(file.getInputStream()))
            return "SUCCES";
        else
            return "FAIL!";
    }
    @PreAuthorize("hasAnyRole('ADMIN')")
    @PostMapping("/addFaculty/{facultyName}")
    public String uploadMultipartFile(@RequestParam("uploadFile") MultipartFile file,@PathVariable("facultyName") String facultyName) throws IOException {
        fileService.saveMultipartFile(file, "facultyFoto");
        FileModel fileModel=fileService.findFileByName(file.getOriginalFilename());
        Faculty faculty= new Faculty();
        faculty.setFacultyNameField(facultyName);
        faculty.setFile(fileModel);
        adminDashboardService.saveUserFacultyDataAdminAction(faculty);
        return "IT WORKS :)";
    }

    @PreAuthorize("hasAnyRole('ADMIN')")
    @PostMapping("/teacherSupportAdminDashboard/deleteFacultyAdminAction")
    public ResponseEntity<Object> deleteFacultySiteAction(@RequestBody String facultyName) {
        if (adminDashboardService.getFacultyByName(facultyName) != null) {
            Faculty faculty=adminDashboardService.getFacultyByName(facultyName);
            fileService.dleteFileById(faculty.getFile().getId());
            faculty.setFile(null);

            List<Person> myPersons=faculty.getFacultyAndPersonList();
            for(Integer i=0;!myPersons.isEmpty();i++)
            {
                Person currentPerson=myPersons.get(i);
                currentPerson.deleteFaculty(faculty);
                myPersons.remove(currentPerson);
            }
            adminDashboardService.deleteFacultyAdminAction(faculty);
        }
        ServiceResponse<String> response = new ServiceResponse<String>("success", facultyName);
        return new ResponseEntity<Object>(response, HttpStatus.OK);
    }
}


