package com.nokia.teachersupport.studGroup;

import com.nokia.teachersupport.currentUser.CurrentUser;
import com.nokia.teachersupport.person.IPersonService;
import com.nokia.teachersupport.person.Person;
import com.nokia.teachersupport.personSecurity.IUserSecurityDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Objects;

@Controller
public class StudGroupController {


    private IStudGroupService studGroupService;
    private IPersonService personService;
    private IUserSecurityDataService userSecurityDataService;



    @Autowired
    public StudGroupController(IUserSecurityDataService userSecurityDataService,IPersonService personService,IStudGroupService studGroupService)
    {

        this.studGroupService=studGroupService;
        this.personService=personService;
        this.userSecurityDataService=userSecurityDataService;
    }

    @GetMapping("/teacherSupportStudent")
    String student(Model model){
        Person person=personService.getPersonByUserSecurityData(userSecurityDataService.getUserSecurityDataByEmail(CurrentUser.getCurrentUserName()));
        model.addAttribute("newStudGroupUserAction", new StudGroup());
        model.addAttribute("currentGroups",person.getPersonStudGroupList());
        model.addAttribute("currentUserName",Objects.requireNonNull(CurrentUser.getCurrentUserName()));
        if(person.getCurrentGroupName()!=null) {
            model.addAttribute("groupFiles", studGroupService.getStudGroupByName(person.getCurrentGroupName()).getFileModels());
            model.addAttribute("currentGroupName", person.getCurrentGroupName());
        }
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
