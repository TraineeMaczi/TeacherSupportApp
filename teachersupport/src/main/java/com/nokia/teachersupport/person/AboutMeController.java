package com.nokia.teachersupport.person;

import com.nokia.teachersupport.model.IModelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class AboutMeController {


    private IModelService modelService;

    @Autowired
    public AboutMeController(IModelService modelService) {
        this.modelService = modelService;

    }
    @GetMapping("/teacherSupportAboutMe")
    String aboutme(Model model) {
        modelService.aboutMeModel(model);
        return "teacherSupportAboutMe";
    }


}
