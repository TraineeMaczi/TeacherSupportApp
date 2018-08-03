package com.nokia.teachersupport.person;

import com.nokia.teachersupport.currentUser.CurrentUser;
import com.nokia.teachersupport.personSecurity.IUserSecurityDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;

@Controller
public class AboutMeController {

    private IPersonService personService;
    private IUserSecurityDataService userSecurityDataService;

    @Autowired
    public AboutMeController (IPersonService personService,IUserSecurityDataService userSecurityDataService) {
        this.personService = personService;
        this.userSecurityDataService=userSecurityDataService;
    }

    @GetMapping("/teacherSupportAboutMe")
    String aboutme(Model model){
        Person person=personService.getPersonByUserSecurityData(userSecurityDataService.getUserSecurityDataByEmail(CurrentUser.getCurrentUserName()));
        model.addAttribute("currentUserName",Objects.requireNonNull(CurrentUser.getCurrentUserName()));
        model.addAttribute("currentUserPerson",person);
        return "teacherSupportAboutMe";
    }



}
