package com.nokia.teachersupport.admin;


import com.nokia.teachersupport.faculty.Faculty;
import com.nokia.teachersupport.faculty.IFacultyService;
import com.nokia.teachersupport.fileUpload.FileModel;
import com.nokia.teachersupport.fileUpload.IFileService;
import com.nokia.teachersupport.person.IPersonService;
import com.nokia.teachersupport.person.Person;
import com.nokia.teachersupport.person.ServiceResponse;
import com.nokia.teachersupport.personSecurity.IUserSecurityDataService;
import com.nokia.teachersupport.personSecurity.UserSecurityDataServiceImpl;
import com.nokia.teachersupport.roles.IRoleService;
import com.nokia.teachersupport.roles.SecutityRole;
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
    private IFileService fileService;
    private IFacultyService facultyService;
    private IPersonService personService;
    private IUserSecurityDataService userSecurityDataService;
    private IRoleService roleService;

    @Autowired
    public AdminDashBoardRestController(IRoleService roleService, IUserSecurityDataService userSecurityDataService, IFileService fileService, IFacultyService facultyService, IPersonService personService) {
        this.fileService = fileService;
        this.facultyService = facultyService;
        this.personService = personService;
        this.roleService = roleService;
        this.userSecurityDataService = userSecurityDataService;
    }

    //Te zostawiam tak jak jest
    @PreAuthorize("hasAnyRole('ADMIN')")
    @PostMapping("/teacherSupportAdminDashboard/newUserAdminActionFromFile")
    String addNewUsersFromFile(@RequestParam("uploadfile") MultipartFile file) throws IOException {
        if (personService.savePersonsFromFile(file.getInputStream(), userSecurityDataService, facultyService, roleService))
            return "SUCCES";
        return "FAIL!";
    }

    @PreAuthorize("hasAnyRole('ADMIN')")
    @PostMapping("/addFaculty/{facultyName}")
    public String uploadMultipartFile(@RequestParam("uploadFile") MultipartFile file, @PathVariable("facultyName") String facultyName) throws IOException {
        if(file.isEmpty())
            return "Firstly you must upload faculty photo";
        FileModel fileModel = fileService.saveMultipartFile(file, "facultyFoto");
        fileService.goUploadMultipartFile(fileModel,facultyName,fileService,facultyService);
        return "SUCCES";
    }

    @PreAuthorize("hasAnyRole('ADMIN')")
    @PostMapping("/teacherSupportAdminDashboard/deleteFacultyAdminAction")
    public ResponseEntity<Object> deleteFacultySiteAction(@RequestBody String facultyName) {

       facultyService.goDeleteFacultySiteAction(facultyName,fileService);
        ServiceResponse<String> response = new ServiceResponse<String>("success", facultyName);
        return new ResponseEntity<Object>(response, HttpStatus.OK);
    }

}


