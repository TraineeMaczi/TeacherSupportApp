package com.nokia.teachersupport.tools;

import com.nokia.teachersupport.configuration.ThymeLeafConfig;
import com.nokia.teachersupport.configuration.WebConfig;
import com.nokia.teachersupport.context.IContextService;
import org.springframework.transaction.support.ResourceHolder;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.thymeleaf.context.Context;

import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.Writer;
import java.nio.file.Files;
import java.util.Base64;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class Generator {

    public static String generate(String listOfPages, IContextService contextService)throws Exception
    {
        String[] lista=listOfPages.split(",");
        Context context = new Context();
        File f = new File("src\\main\\resources\\zip\\pages.zip");
        ZipOutputStream out = new ZipOutputStream(new FileOutputStream(f));
        for(String obj: lista)
        {

            if(obj.equals("Home.html"))
            {
                contextService.homeContext(context);
                ZipEntry e = new ZipEntry("Home.html");
                out.putNextEntry(e);
                byte[] data = ThymeLeafConfig.getTemplateEngine().process("Home.html", context).getBytes();
                out.write(data, 0, data.length);
                out.closeEntry();
            }
            if(obj.equals("AboutMe.html"))
            {
                contextService.aboutMeContext(context);
                ZipEntry e = new ZipEntry("AboutMe.html");
                out.putNextEntry(e);
                byte[] data = ThymeLeafConfig.getTemplateEngine().process("AboutMe.html", context).getBytes();
                out.write(data, 0, data.length);
                out.closeEntry();
            }
            if(obj.equals("Publications.html"))
            {
                contextService.publicationsContext(context);
                ZipEntry e = new ZipEntry("Publications.html");
                out.putNextEntry(e);
                byte[] data = ThymeLeafConfig.getTemplateEngine().process("Publications.html", context).getBytes();
                out.write(data, 0, data.length);
                out.closeEntry();
            }
            if(obj.equals("Contact.html"))
            {
                contextService.contactContext(context);
                ZipEntry e = new ZipEntry("Contact.html");
                out.putNextEntry(e);
                byte[] data = ThymeLeafConfig.getTemplateEngine().process("Contact.html", context).getBytes();
                out.write(data, 0, data.length);
                out.closeEntry();
            }
            if(obj.equals("Student.html"))
            {
                contextService.studentContext(context);
                ZipEntry e = new ZipEntry("ForStudentPage.html");
                out.putNextEntry(e);
                byte[] data = ThymeLeafConfig.getTemplateEngine().process("ForStudentPage.html", context).getBytes();
                out.write(data, 0, data.length);
                out.closeEntry();
            }
        }
        out.close();
        return "zipFiles/pages.zip";
    }
}
