package com.nokia.teachersupport.person;

import com.nokia.teachersupport.serviceProvider.IServiceProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AboutMeController {


    private IServiceProvider serviceProvider;

    @Autowired
    public AboutMeController(IServiceProvider serviceProvider) {
        this.serviceProvider = serviceProvider;

    }

    @GetMapping("/teacherSupportAboutMe")
    String aboutme(Model model) {
        serviceProvider.getIModelService().aboutMeModel(model, serviceProvider);
        return "teacherSupportAboutMe";
    }


}
