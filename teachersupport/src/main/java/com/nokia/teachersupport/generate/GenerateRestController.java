package com.nokia.teachersupport.generate;

import com.nokia.teachersupport.configuration.ThymeLeafConfig;
import com.nokia.teachersupport.tools.CurrentUser;
import com.nokia.teachersupport.person.IPersonService;
import com.nokia.teachersupport.person.Person;
import com.nokia.teachersupport.personSecurity.IUserSecurityDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import java.io.FileWriter;
import java.io.Writer;
import org.thymeleaf.context.Context;

@RestController
public class GenerateRestController{
    @Autowired
    private IPersonService personService;
    @Autowired
    private IUserSecurityDataService userSecurityDataService;
    @PostMapping("/generate/listOfPages")
    public String generatePages(@RequestParam("listOfPages") String listOfPages) throws Exception
    {
        String[] lista=listOfPages.split(",");
        for(String obj: lista)
        {
            System.out.println(obj);
        }

        Context context = new Context();
        Person person = personService.getPersonByUserSecurityData(userSecurityDataService.getUserSecurityDataByEmail(CurrentUser.getCurrentUserName()));
        context.setVariable("news", person.getPersonNewsList());
        Writer writer = new FileWriter("C:/mypage/Home.html");
        writer.write(ThymeLeafConfig.getTemplateEngine().process("Home.html", context));
        writer.close();
        return "teacherSupportGenerate";
    }
}
