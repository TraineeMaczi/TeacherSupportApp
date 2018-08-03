package com.nokia.teachersupport.fileUpload;

import com.nokia.teachersupport.currentUser.CurrentUser;
import com.nokia.teachersupport.fileUpload.FileModel;
import com.nokia.teachersupport.fileUpload.FileRepository;
import com.nokia.teachersupport.person.IPersonService;
import com.nokia.teachersupport.personSecurity.IUserSecurityDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;



@RestController
public class UpdateFileController {
    @Autowired
    private IFileService fileRepository;
    @Autowired
    private IPersonService personService;
    @Autowired
    private IUserSecurityDataService userSecurityDataService;

    @PostMapping("/upload/{type}")
    public String uploadMultipartFile(@RequestParam("uploadfile") MultipartFile file, @PathVariable String type) {
        try {
            FileModel filemode = new FileModel(file.getOriginalFilename(), type,file.getBytes(),
                    personService.getPersonByUserSecurityData(userSecurityDataService.getUserSecurityDataByEmail(CurrentUser.getCurrentUserName())));
            if(file.getOriginalFilename().equals(""))
                return "FAIL! \n" +
                        "You did not choose a file.";
            fileRepository.savefile(filemode);
            return "File uploaded successfully! -> filename = " + file.getOriginalFilename();
        } catch (	Exception e) {
            return "FAIL! ";
        }
    }

}
