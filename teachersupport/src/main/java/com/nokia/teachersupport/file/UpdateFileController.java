package com.nokia.teachersupport.file;


import com.nokia.teachersupport.serviceProvider.IServiceProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;


@RestController
public class UpdateFileController {

    private IServiceProvider serviceProvider;

    @Autowired
    public UpdateFileController(IServiceProvider serviceProvider) {
        this.serviceProvider = serviceProvider;
    }


    @PostMapping("/upload/{type}")
    public String uploadMultipartFile(@RequestParam("uploadfile") MultipartFile file, @PathVariable String type) throws IOException {
        if ((type.equals("CV") && !file.getOriginalFilename().contains(".pdf")))
            return "Fail! You must upload file in pdf format";
        if (file.getOriginalFilename().equals(""))
            return "Fail! Your file is empty";
        serviceProvider.getIFileService().saveMultipartFile(file,type,serviceProvider);
        return "File uploaded successfully! -> filename = " + file.getOriginalFilename();
    }

}
