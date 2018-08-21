package com.nokia.teachersupport.person;

import com.nokia.teachersupport.currentUser.CurrentUser;
import com.nokia.teachersupport.model.IModelService;
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
    private IModelService modelService;

    @Autowired
    public AboutMeController (IMeetMeService meetMeService,IPersonService personService,IUserSecurityDataService userSecurityDataService, IModelService modelService) {
        this.personService = personService;
        this.userSecurityDataService=userSecurityDataService;
        this.modelService=modelService;

    }

    @GetMapping("/teacherSupportAboutMe")
    String aboutme(Model model){
        modelService.aboutMeModel(model);
        return "teacherSupportAboutMe";
    }



}
