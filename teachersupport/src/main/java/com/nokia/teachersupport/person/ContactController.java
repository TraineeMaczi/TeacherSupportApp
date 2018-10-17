package com.nokia.teachersupport.person;

import com.nokia.teachersupport.serviceProvider.IServiceProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ContactController {

   private IServiceProvider serviceProvider;
    @Autowired
    public ContactController (IServiceProvider serviceProvider) {
        this.serviceProvider=serviceProvider;
    }

    @GetMapping("/teacherSupportContact")
    String contact(Model model){
        serviceProvider.getIModelService().contactModel(model,serviceProvider);
        return "teacherSupportContact";
    }

}
