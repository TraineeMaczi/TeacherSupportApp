package com.nokia.teachersupport.generate;

import com.nokia.teachersupport.serviceProvider.IServiceProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class GenerateController {
    private IServiceProvider serviceProvider;

    @Autowired
    public GenerateController(IServiceProvider serviceProvider) {
        this.serviceProvider = serviceProvider;
    }

    @GetMapping("/teacherSupportGenerate")
    String generate(Model model) {
        serviceProvider.getIModelService().generateModel(model);
        return "teacherSupportGenerate";
    }

}
