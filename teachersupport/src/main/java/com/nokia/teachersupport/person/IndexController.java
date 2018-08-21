package com.nokia.teachersupport.person;

import com.nokia.teachersupport.currentUser.CurrentUser;
import com.nokia.teachersupport.faculty.Faculty;
import com.nokia.teachersupport.faculty.IFacultyService;
import com.nokia.teachersupport.fileUpload.FileModel;
import com.nokia.teachersupport.model.IModelService;
import com.nokia.teachersupport.personSecurity.IUserSecurityDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.util.*;

import java.util.Objects;

@Controller
public class IndexController {

    private IPersonService personService;
    private IUserSecurityDataService userSecurityDataService;
    private IFacultyService facultyService;
    private IModelService modelService;
    @Autowired
    public IndexController( IFacultyService facultyService,IPersonService personService,IUserSecurityDataService userSecurityDataService, IModelService modelService) {
        this.personService = personService;
        this.userSecurityDataService=userSecurityDataService;
        this.facultyService=facultyService;
        this.modelService=modelService;
    }

    @GetMapping("/")
    String index(Model model) {
        modelService.indexModel(model);
        return "teacherSupportIndex";
    }
    @PostMapping("/index/confirmFaculty")
    String saveFaculty(@RequestParam("facultyName") String name) {
       Person person=personService.getPersonByUserSecurityData(userSecurityDataService.getUserSecurityDataByEmail(CurrentUser.getCurrentUserName()));
       person.setFacultyField(facultyService.findFaculty(name));
       Faculty faculty=facultyService.findFaculty(name);
       faculty.addPersonToFaculty(person);
       facultyService.saveFaculty(faculty);
       personService.savePerson(person);
       return "teacherSupportIndex";
    }
    @GetMapping("/index/giveMePhoto")
    ResponseEntity<Object> giveFacultyPhoto() {
        List<Faculty>faculties=facultyService.listOfAllFaculties();
        List<String>pic= new ArrayList<>();
        String pom;
        for (Faculty faculty: faculties)
        {
            if(faculty.getFile()!=null) {
                pom = Base64.getEncoder().encodeToString(faculty.getFile().getPic());
                pom = "data:image/jpeg;base64,"+pom;
            }
            else pom = "img/logo.jpg";
            pic.add(pom);
        }
        ServiceResponse<List<String>> response = new ServiceResponse<>("success", pic);
        return new ResponseEntity<Object>(response, HttpStatus.OK);
    }
    @GetMapping("/index/giveMeId")
    ResponseEntity<Object> giveFacultyId() {
        List<Faculty>faculties=facultyService.listOfAllFaculties();
        List<Integer>Id= new ArrayList<>();
        for (Faculty faculty: faculties)
            Id.add(faculty.getId());
        ServiceResponse<List<Integer>> response = new ServiceResponse<>("success", Id);
        return new ResponseEntity<Object>(response, HttpStatus.OK);
    }
}
