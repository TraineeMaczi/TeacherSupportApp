package com.nokia.teachersupport.tools;

import com.nokia.teachersupport.configuration.ThymeLeafConfig;
import com.nokia.teachersupport.context.IContextService;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItem;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.thymeleaf.context.Context;

import java.io.*;
import java.nio.file.Files;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;


public class Generator {

    public static MultipartFile generate(String listOfPages, IContextService contextService) throws Exception {
        String[] lista = listOfPages.split(",");
        Context context = new Context();
        File f = new File("src\\main\\resources\\zip\\pages.zip");
        ZipOutputStream out = new ZipOutputStream(new FileOutputStream(f));
        for (String obj : lista) {

            if (obj.equals("Home.html")) {
                contextService.homeContext(context);
                ZipEntry e = new ZipEntry("Home.html");
                out.putNextEntry(e);
                byte[] data = ThymeLeafConfig.getTemplateEngine().process("Home.html", context).getBytes();
                out.write(data, 0, data.length);
                out.closeEntry();
            }
            if (obj.equals("AboutMe.html")) {
                contextService.aboutMeContext(context);
                ZipEntry e = new ZipEntry("AboutMe.html");
                out.putNextEntry(e);
                byte[] data = ThymeLeafConfig.getTemplateEngine().process("AboutMe.html", context).getBytes();
                out.write(data, 0, data.length);
                out.closeEntry();
            }
            if (obj.equals("Publications.html")) {
                contextService.publicationsContext(context);
                ZipEntry e = new ZipEntry("Publications.html");
                out.putNextEntry(e);
                byte[] data = ThymeLeafConfig.getTemplateEngine().process("Publications.html", context).getBytes();
                out.write(data, 0, data.length);
                out.closeEntry();
            }
            if (obj.equals("Contact.html")) {
                contextService.contactContext(context);
                ZipEntry e = new ZipEntry("Contact.html");
                out.putNextEntry(e);
                byte[] data = ThymeLeafConfig.getTemplateEngine().process("Contact.html", context).getBytes();
                out.write(data, 0, data.length);
                out.closeEntry();
            }
            if (obj.equals("Student.html")) {
                contextService.studentContext(context);
                ZipEntry e = new ZipEntry("Student.html");
                out.putNextEntry(e);
                byte[] data = ThymeLeafConfig.getTemplateEngine().process("Student.html", context).getBytes();
                out.write(data, 0, data.length);
                out.closeEntry();
            }
        }
        out.close();

        FileItem fileItem = new DiskFileItem("pages.zip", Files.probeContentType(f.toPath()), false, f.getName(), (int) f.length(), f.getParentFile());

        try {
            InputStream input = new FileInputStream(f);
            OutputStream os = fileItem.getOutputStream();
            IOUtils.copy(input, os);
        } catch (IOException ex) {
        }
        MultipartFile result = new CommonsMultipartFile(fileItem);
        return result;
    }
}
