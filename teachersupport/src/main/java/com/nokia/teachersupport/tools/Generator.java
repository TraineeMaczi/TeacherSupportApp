package com.nokia.teachersupport.tools;

import com.nokia.teachersupport.configuration.ThymeLeafConfig;
import com.nokia.teachersupport.context.IContextService;
import com.nokia.teachersupport.serviceProvider.IServiceProvider;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItem;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.thymeleaf.context.Context;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;


public class Generator {

    public static MultipartFile generate(String listOfPages, IServiceProvider serviceProvider) throws Exception {
        String[] lista = listOfPages.split(",");
        Context context = new Context();
        File f = new File("src\\main\\resources\\zip\\pages.zip");
        ZipOutputStream out = new ZipOutputStream(new FileOutputStream(f));
        File f2 = new File("src\\main\\resources\\static\\css\\Style.css");
        Path file = f2.toPath();
        String inputFileName = file.toFile().getPath();
        try (FileInputStream inputStream = new FileInputStream(inputFileName)) {
            ZipEntry entry = new ZipEntry(file.toFile().getName());
            out.putNextEntry(entry);
            byte[] readBuffer = new byte[2048];
            int amountRead;
            int written = 0;
            while ((amountRead = inputStream.read(readBuffer)) > 0) {
                out.write(readBuffer, 0, amountRead);
                written += amountRead;
            }
            out.closeEntry();
        } catch (IOException e) {
            System.out.println("BLADDD");//WTF COS Z TYM ZROBIC
        }
        for (String obj : lista) {

            if (obj.equals("Home.html")) {
                serviceProvider.getIContextService().homeContext(context,serviceProvider);
                ZipEntry e = new ZipEntry("Home.html");
                out.putNextEntry(e);
                byte[] data = ThymeLeafConfig.getTemplateEngine().process("Home.html", context).getBytes();
                out.write(data, 0, data.length);
                out.closeEntry();
            }
            if (obj.equals("AboutMe.html")) {
                serviceProvider.getIContextService().aboutMeContext(context,serviceProvider);
                ZipEntry e = new ZipEntry("AboutMe.html");
                out.putNextEntry(e);
                byte[] data = ThymeLeafConfig.getTemplateEngine().process("AboutMe.html", context).getBytes();
                out.write(data, 0, data.length);
                out.closeEntry();
            }
            if (obj.equals("Publications.html")) {
                serviceProvider.getIContextService().publicationContext(context,serviceProvider);
                ZipEntry e = new ZipEntry("Publications.html");
                out.putNextEntry(e);
                byte[] data = ThymeLeafConfig.getTemplateEngine().process("Publications.html", context).getBytes();
                out.write(data, 0, data.length);
                out.closeEntry();
            }
            if (obj.equals("Contact.html")) {
                serviceProvider.getIContextService().contactContext(context,serviceProvider);
                ZipEntry e = new ZipEntry("Contact.html");
                out.putNextEntry(e);
                byte[] data = ThymeLeafConfig.getTemplateEngine().process("Contact.html", context).getBytes();
                out.write(data, 0, data.length);
                out.closeEntry();
            }
            if (obj.equals("Student.html")) {
                serviceProvider.getIContextService().studentContext(context,serviceProvider);
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

    public static MultipartFile generateTemplates(IServiceProvider serviceProvider) throws Exception {
        Context context = new Context();
        File f = new File("src\\main\\resources\\zip\\pages.zip");
        ZipOutputStream out = new ZipOutputStream(new FileOutputStream(f));
        File f2 = new File("src\\main\\resources\\static\\css\\Style.css");
        Path file = f2.toPath();
        String inputFileName = file.toFile().getPath();
        try (FileInputStream inputStream = new FileInputStream(inputFileName)) {
            ZipEntry entry = new ZipEntry(file.toFile().getName());
            out.putNextEntry(entry);
            byte[] readBuffer = new byte[2048];
            int amountRead;
            int written = 0;
            while ((amountRead = inputStream.read(readBuffer)) > 0) {
                out.write(readBuffer, 0, amountRead);
                written += amountRead;
            }
            out.closeEntry();
        } catch (IOException e) {
            System.out.println("BLADDD"); //WTF COS Z TYM ZROBIC
        }
        serviceProvider.getIContextService().nullContext(context);
        ZipEntry e = new ZipEntry("Home.html");
        out.putNextEntry(e);
        byte[] data = ThymeLeafConfig.getTemplateEngine().process("Home.html", context).getBytes();
        out.write(data, 0, data.length);
        out.closeEntry();
        e = new ZipEntry("AboutMe.html");
        out.putNextEntry(e);
        data = ThymeLeafConfig.getTemplateEngine().process("AboutMe.html", context).getBytes();
        out.write(data, 0, data.length);
        out.closeEntry();
        e = new ZipEntry("Publication.html");
        out.putNextEntry(e);
        data = ThymeLeafConfig.getTemplateEngine().process("Publication.html", context).getBytes();
        out.write(data, 0, data.length);
        out.closeEntry();
        e = new ZipEntry("Contact.html");
        out.putNextEntry(e);
        data = ThymeLeafConfig.getTemplateEngine().process("Contact.html", context).getBytes();
        out.write(data, 0, data.length);
        out.closeEntry();
        e = new ZipEntry("Student.html");
        out.putNextEntry(e);
        data = ThymeLeafConfig.getTemplateEngine().process("Student.html", context).getBytes();
        out.write(data, 0, data.length);
        out.closeEntry();
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
