package com.nokia.teachersupport.person;

import com.nokia.teachersupport.tools.CurrentUser;
import com.nokia.teachersupport.faculty.Faculty;
import com.nokia.teachersupport.faculty.IFacultyService;
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

import java.util.*;

@Controller
public class IndexController {

    private IPersonService personService;
    private IUserSecurityDataService userSecurityDataService;
    private IFacultyService facultyService;
    private IModelService modelService;

    @Autowired
    public IndexController(IFacultyService facultyService, IPersonService personService, IUserSecurityDataService userSecurityDataService, IModelService modelService) {
        this.personService = personService;
        this.userSecurityDataService = userSecurityDataService;
        this.facultyService = facultyService;
        this.modelService = modelService;
    }

    @GetMapping("/")
    String index(Model model) {
        modelService.indexModel(model);
        return "teacherSupportIndex";
    }

    @PostMapping("/index/confirmFaculty")
    String saveFaculty(@RequestParam("facultyName") String name) {
        personService.goSaveMyFaculty(name, personService, facultyService, userSecurityDataService);
        return "teacherSupportIndex";
    }

    @GetMapping("/index/giveMePhoto")
    ResponseEntity<Object> giveFacultyPhoto() {
        List<String> pic = personService.goGiveMeFacultyPhoto(facultyService);
        ServiceResponse<List<String>> response = new ServiceResponse<>("success", pic);
        return new ResponseEntity<Object>(response, HttpStatus.OK);
    }

    @GetMapping("/index/giveMeId")
    ResponseEntity<Object> giveFacultyId() {
        List<Integer> Id = personService.goGiveMeFacultyId(facultyService);
        ServiceResponse<List<Integer>> response = new ServiceResponse<>("success", Id);
        return new ResponseEntity<Object>(response, HttpStatus.OK);
    }
}
