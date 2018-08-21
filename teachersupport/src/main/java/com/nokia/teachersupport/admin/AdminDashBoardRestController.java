package com.nokia.teachersupport.admin;


import com.nokia.teachersupport.faculty.Faculty;
import com.nokia.teachersupport.faculty.IFacultyService;
import com.nokia.teachersupport.fileUpload.FileModel;
import com.nokia.teachersupport.fileUpload.IFileService;
import com.nokia.teachersupport.person.IPersonService;
import com.nokia.teachersupport.person.Person;
import com.nokia.teachersupport.person.ServiceResponse;
import com.nokia.teachersupport.personSecurity.UserSecurityDataServiceImpl;
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
    @Autowired
    public AdminDashBoardRestController(IFileService fileService, IFacultyService facultyService, IPersonService personService) {
        this.fileService = fileService;
        this.facultyService = facultyService;
        this.personService = personService;
    }
    @PreAuthorize("hasAnyRole('ADMIN')")
    @PostMapping("/teacherSupportAdminDashboard/newUserAdminActionFromFile")
    String addNewUsersFromFile(@RequestParam("uploadfile") MultipartFile file) throws IOException {
        if (personService.savePersonsFromFile(file.getInputStream()))
            return "SUCCES";
        return "FAIL!";
    }
    @PreAuthorize("hasAnyRole('ADMIN')")
    @PostMapping("/addFaculty/{facultyName}")
    public String uploadMultipartFile(@RequestParam("uploadFile") MultipartFile file,@PathVariable("facultyName") String facultyName) throws IOException {
        FileModel fileModel= fileService.saveMultipartFile(file, "facultyFoto");
        Faculty faculty= new Faculty();
        faculty.setFacultyNameField(facultyName);
        faculty.setFile(fileModel);
        facultyService.saveFaculty(faculty);
        return "IT WORKS :)";
    }

    @PreAuthorize("hasAnyRole('ADMIN')")
    @PostMapping("/teacherSupportAdminDashboard/deleteFacultyAdminAction")
    public ResponseEntity<Object> deleteFacultySiteAction(@RequestBody String facultyName) {
        if (facultyService.findFaculty(facultyName) != null) {
            Faculty faculty=facultyService.findFaculty(facultyName);
            fileService.dleteFileById(faculty.getFile().getId());
            faculty.setFile(null);

            List<Person> myPersons=faculty.getFacultyAndPersonList();
            for(Integer i=0;!myPersons.isEmpty();i++)
            {
                Person currentPerson=myPersons.get(i);
                currentPerson.deleteFaculty(faculty);
                myPersons.remove(currentPerson);
            }
            facultyService.deleteFaculty(faculty);
        }
        ServiceResponse<String> response = new ServiceResponse<String>("success", facultyName);
        return new ResponseEntity<Object>(response, HttpStatus.OK);
    }

}


