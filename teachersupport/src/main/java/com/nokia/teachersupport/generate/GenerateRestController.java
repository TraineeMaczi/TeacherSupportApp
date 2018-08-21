package com.nokia.teachersupport.generate;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GenerateRestController {
    @PostMapping("/generate/listOfPages")
    public String generatePages(@RequestParam("listOfPages") String listOfPages)
    {
        String[] lista=listOfPages.split(",");
        for(String obj: lista)
        {
            System.out.println(obj);
        }
        return "teacherSupportGenerate";
    }
}
