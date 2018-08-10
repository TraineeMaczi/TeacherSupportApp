package com.nokia.teachersupport.person;

import com.nokia.teachersupport.currentUser.CurrentUser;
import com.nokia.teachersupport.faculty.Faculty;
import com.nokia.teachersupport.faculty.IFacultyService;
import com.nokia.teachersupport.personSecurity.IUserSecurityDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Objects;

@Controller
public class IndexController {

    private IPersonService personService;
    private IUserSecurityDataService userSecurityDataService;
    private IFacultyService facultyService;
    @Autowired
    public IndexController( IFacultyService facultyService,IPersonService personService,IUserSecurityDataService userSecurityDataService) {
        this.personService = personService;
        this.userSecurityDataService=userSecurityDataService;
        this.facultyService=facultyService;
    }

    @GetMapping("/")
    String index(Model model) {
        Person person=personService.getPersonByUserSecurityData(userSecurityDataService.getUserSecurityDataByEmail(CurrentUser.getCurrentUserName()));
        model.addAttribute("currentUserPerson",person);
        model.addAttribute("currentUserName",Objects.requireNonNull(CurrentUser.getCurrentUserName()));
        model.addAttribute("facultyList",facultyService.listOfAllFaculties());
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

}
