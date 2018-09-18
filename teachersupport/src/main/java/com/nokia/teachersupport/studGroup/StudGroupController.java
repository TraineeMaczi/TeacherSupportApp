package com.nokia.teachersupport.studGroup;

import com.nokia.teachersupport.serviceProvider.IServiceProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpSession;

@Controller
public class StudGroupController {


   private IServiceProvider serviceProvider;


    @Autowired
    public StudGroupController(IServiceProvider serviceProvider)
    {
        this.serviceProvider=serviceProvider;
    }

    @GetMapping("/teacherSupportStudent")
    String student(Model model, HttpSession session){
        serviceProvider.getIModelService().studGroupModel(model,session,serviceProvider);
        return "teacherSupportStudent";
    }

    @PostMapping("/teacherSupportStudent/addNewGroupUserAction")
    String addNewGroupUserAction(StudGroup studGroup)
    {
        serviceProvider.getIStudGroupService().addStudGroup(studGroup,serviceProvider);
        return "redirect:/teacherSupportStudent";
    }
}
