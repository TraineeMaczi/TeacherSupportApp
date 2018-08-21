package com.nokia.teachersupport.generate;

import com.nokia.teachersupport.currentUser.CurrentUser;
import com.nokia.teachersupport.model.IModelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Controller
public class GenerateController {
    private IModelService modelService;

    @Autowired
    public GenerateController(IModelService modelService) {
        this.modelService = modelService;
    }

    @GetMapping("/teacherSupportGenerate")
    String generate(Model model) {
        modelService.generateModel(model);
        return "teacherSupportGenerate";
    }

}
