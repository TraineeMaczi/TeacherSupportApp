package com.nokia.teachersupport.tools;

import com.nokia.teachersupport.configuration.ThymeLeafConfig;
import com.nokia.teachersupport.context.IContextService;
import org.springframework.beans.factory.annotation.Autowired;
import org.thymeleaf.context.Context;

import java.io.FileWriter;
import java.io.Writer;

public class Generator {

    public static void generate(String listOfPages, IContextService contextService)throws Exception
    {
        String[] lista=listOfPages.split(",");
        Context context = new Context();
        for(String obj: lista)
        {

            if(obj.equals("Home.html"))
            {
                contextService.homeContext(context);
                Writer writer = new FileWriter("C:/mypage/Home.html");
                writer.write(ThymeLeafConfig.getTemplateEngine().process("Home.html", context));
                writer.close();
            }
            if(obj.equals("AboutMe.html"))
            {
                contextService.aboutMeContext(context);
                Writer writer = new FileWriter("C:/mypage/AboutMe.html");
                writer.write(ThymeLeafConfig.getTemplateEngine().process("AboutMe.html", context));
                writer.close();
            }
            if(obj.equals("Publications.html"))
            {
                contextService.publicationsContext(context);
                Writer writer = new FileWriter("C:/mypage/Publications.html");
                writer.write(ThymeLeafConfig.getTemplateEngine().process("Publications.html", context));
                writer.close();
            }
            if(obj.equals("Contact.html"))
            {
                contextService.contactContext(context);
                Writer writer = new FileWriter("C:/mypage/Contact.html");
                writer.write(ThymeLeafConfig.getTemplateEngine().process("Contact.html", context));
                writer.close();
            }
            if(obj.equals("Student.html"))
            {
                contextService.studentContext(context);
                Writer writer = new FileWriter("C:/mypage/Student.html");
                writer.write(ThymeLeafConfig.getTemplateEngine().process("Student.html", context));
                writer.close();
            }
        }



    }
}
