package com.nokia.teachersupport.fileUpload;

import com.nokia.teachersupport.currentUser.CurrentUser;
import com.nokia.teachersupport.person.IPersonService;
import com.nokia.teachersupport.personSecurity.IUserSecurityDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;


@RestController
public class UpdateFileController {
    @Autowired
    FileServiceImpl fileService;
    @PostMapping("/upload/{type}")
    public String uploadMultipartFile(@RequestParam("uploadfile") MultipartFile file, @PathVariable String type) throws IOException {
        if (fileService.saveMultipartFile(file, type))
            return "File uploaded successfully! -> filename = " + file.getOriginalFilename();
        else
            return "FAIL! \n" +
                    "You did not choose a file.";
    }

}
