package com.nokia.teachersupport.generate;


import com.nokia.teachersupport.context.IContextService;
import com.nokia.teachersupport.tools.CurrentUser;
import com.nokia.teachersupport.tools.Generator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class GenerateRestController{
    IContextService contextService;
    @Autowired
    public GenerateRestController(IContextService contextService) {
        this.contextService = contextService;
    }

    @PostMapping("/generate/listOfPages")
    public String generatePages(@RequestParam("listOfPages") String listOfPages)throws Exception
    {
        Generator.generate(listOfPages, contextService);
        return "teacherSupportGenerate";
    }
}
