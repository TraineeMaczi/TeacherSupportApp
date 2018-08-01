package com.nokia.teachersupport.studGroup;

import com.nokia.teachersupport.infrastructure.tools.UserTools;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Objects;

@Controller
public class StudGroupController {


    private IStudGroupService studGroupService;

    @Autowired
    public StudGroupController(IStudGroupService studGroupService)
    {
        this.studGroupService=studGroupService;
    }

    @GetMapping("/teacherSupportStudent")
    String student(Model model){
        model.addAttribute("newStudGroupUserAction", new StudGroup());
        model.addAttribute("currentGroups",studGroupService.listOfAllGroups());
        model.addAttribute("currentUserName",Objects.requireNonNull(UserTools.getCurrentUserName()));
        return "teacherSupportStudent";
    }



    @PostMapping("/teacherSupportStudent/addNewGroupUserAction")
    String addNewGroupUserAction(StudGroup studGroup)
    {
        if(studGroupService.getStudGroupByName(studGroup.getGroupNameField())==null)
        {
            studGroupService.saveStudGroup(studGroup);
        }
        return "redirect:/teacherSupportStudent";
    }
}
