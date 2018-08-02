package com.nokia.teachersupport.person;

import com.nokia.teachersupport.currentUser.CurrentUser;
import com.nokia.teachersupport.personSecurity.IUserSecurityDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Objects;

@Controller
public class IndexController {

    private IPersonService personService;
    private IUserSecurityDataService userSecurityDataService;

    @Autowired
    public IndexController(IPersonService personService,IUserSecurityDataService userSecurityDataService) {
        this.personService = personService;
        this.userSecurityDataService=userSecurityDataService;
    }

    @GetMapping("/")
    String index(Model model) {
        Person person=personService.getPersonByUserSecurityData(userSecurityDataService.getUserSecurityDataByEmail(CurrentUser.getCurrentUserName()));
        model.addAttribute("currentUserPerson",person);
        model.addAttribute("currentUserName",Objects.requireNonNull(CurrentUser.getCurrentUserName()));
        return "teacherSupportIndex";
    }


}
