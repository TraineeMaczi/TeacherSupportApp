package com.nokia.teachersupport.person;

import com.nokia.teachersupport.fileUpload.IFileService;
import com.nokia.teachersupport.personSecurity.IUserSecurityDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
public class AboutMeRESTController {
    private IPersonService personService;
    private IUserSecurityDataService userSecurityDataService;
    private IFileService fileService;

    @Autowired
    public AboutMeRESTController(IPersonService personService, IUserSecurityDataService userSecurityDataService, IFileService fileService) {
        this.personService = personService;
        this.userSecurityDataService = userSecurityDataService;
        this.fileService = fileService;
    }

    @PostMapping("/teacherSupportAboutMe/BasicInfo/new")
    public ResponseEntity<Object> addBasicInfo(@RequestBody BasicInfoDTO basicInfoDTO) {
        personService.goAddBasicInfo(basicInfoDTO, userSecurityDataService, personService);
        ServiceResponse<BasicInfoDTO> response = new ServiceResponse<BasicInfoDTO>("success", basicInfoDTO);
        return new ResponseEntity<Object>(response, HttpStatus.OK);
    }

    @PostMapping("/teacherSupportAboutMe/hobby/new")
    public ResponseEntity<Object> addHobbyInfo(@RequestBody String hobbyInfo) {
        personService.goAddHobbyInfo(hobbyInfo, personService, userSecurityDataService);
        ServiceResponse<String> response = new ServiceResponse<String>("success", hobbyInfo);
        return new ResponseEntity<Object>(response, HttpStatus.OK);
    }

    @PostMapping("/uploadFoto")
    public String uploadPhoto(@RequestParam("uploadfile") MultipartFile file) throws IOException {
        personService.goUploadPhoto(file, fileService, personService, userSecurityDataService);
        return "SUCCES";
    }

    @GetMapping("/givePhoto")
    ResponseEntity<Object> giveMyPhoto() {
        String pom = personService.goGivePhoto(personService, userSecurityDataService);
        ServiceResponse<String> response = new ServiceResponse<>("success", pom);
        return new ResponseEntity<Object>(response, HttpStatus.OK);
    }

}







