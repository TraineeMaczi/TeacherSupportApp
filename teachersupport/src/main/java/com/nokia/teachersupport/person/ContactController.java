package com.nokia.teachersupport.person;

import com.nokia.teachersupport.model.IModelService;
import com.nokia.teachersupport.personSecurity.IUserSecurityDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ContactController {

    private IPersonService personService;
    private IUserSecurityDataService userSecurityDataService;
    private IModelService modelService;
    @Autowired
    public ContactController (IPersonService personService,IUserSecurityDataService userSecurityDataService, IModelService modelService) {
        this.personService = personService;
        this.userSecurityDataService=userSecurityDataService;
        this.modelService=modelService;
    }

    @GetMapping("/teacherSupportContact")
    String contact(Model model){
        modelService.contactModel(model);
        return "teacherSupportContact";
    }

}
