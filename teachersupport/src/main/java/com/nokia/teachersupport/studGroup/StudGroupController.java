package com.nokia.teachersupport.studGroup;

import com.nokia.teachersupport.model.IModelService;
import com.nokia.teachersupport.tools.CurrentUser;
import com.nokia.teachersupport.person.IPersonService;
import com.nokia.teachersupport.person.Person;
import com.nokia.teachersupport.personSecurity.IUserSecurityDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpSession;
import java.util.Objects;

@Controller
public class StudGroupController {


    private IStudGroupService studGroupService;
    private IPersonService personService;
    private IUserSecurityDataService userSecurityDataService;
    private IModelService modelService;


    @Autowired
    public StudGroupController(IModelService modelServicec,IUserSecurityDataService userSecurityDataService,IPersonService personService,IStudGroupService studGroupService)
    {
        this.studGroupService=studGroupService;
        this.personService=personService;
        this.userSecurityDataService=userSecurityDataService;
        this.modelService=modelServicec;
    }

    @GetMapping("/teacherSupportStudent")
    String student(Model model, HttpSession session){

        modelService.studGroupModel(model,session);
        return "teacherSupportStudent";
    }
    
    @PostMapping("/teacherSupportStudent/addNewGroupUserAction")
    String addNewGroupUserAction(StudGroup studGroup)
    {
        Person person=personService.getPersonByUserSecurityData(userSecurityDataService.getUserSecurityDataByEmail(CurrentUser.getCurrentUserName()));

        if(studGroupService.getStudGroupByName(studGroup.getGroupNameField())==null) {
            studGroup.setGroupsOwner(person);
            person.addGroupsToMyList(studGroup);

            personService.savePerson(person);
            studGroupService.saveStudGroup(studGroup);
        }
        return "redirect:/teacherSupportStudent";
    }
}
