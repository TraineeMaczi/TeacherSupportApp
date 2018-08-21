package com.nokia.teachersupport.fileUpload;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;


@RestController
public class UpdateFileController {

    private FileServiceImpl fileService;

    @Autowired
    public UpdateFileController(FileServiceImpl fileService) {
        this.fileService = fileService;
    }


    @PostMapping("/upload/{type}")
    public String uploadMultipartFile(@RequestParam("uploadfile") MultipartFile file, @PathVariable String type) throws IOException {
        if (!fileService.saveMultipartFile(file, type).getName().equals(""))
            return "File uploaded successfully! -> filename = " + file.getOriginalFilename();
        else
            return "FAIL! \n" +
                    "You did not choose a file.";
    }

}
