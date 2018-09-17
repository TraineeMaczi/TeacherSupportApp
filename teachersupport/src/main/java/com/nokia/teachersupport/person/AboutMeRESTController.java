package com.nokia.teachersupport.person;

import com.nokia.teachersupport.fileUpload.IFileService;
import com.nokia.teachersupport.personSecurity.IUserSecurityDataService;
import com.nokia.teachersupport.serviceProvider.IServiceProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
public class AboutMeRESTController {
    private IServiceProvider serviceProvider;

    @Autowired
    public AboutMeRESTController(IServiceProvider serviceProvider) {
     this.serviceProvider=serviceProvider;
    }

    @PostMapping("/teacherSupportAboutMe/BasicInfo/new")
    public ResponseEntity<Object> addBasicInfo(@RequestBody BasicInfoDTO basicInfoDTO) {
        serviceProvider.getIPersonService().goAddBasicInfo(basicInfoDTO,serviceProvider);
        ServiceResponse<BasicInfoDTO> response = new ServiceResponse<BasicInfoDTO>("success", basicInfoDTO);
        return new ResponseEntity<Object>(response, HttpStatus.OK);
    }

    @PostMapping("/teacherSupportAboutMe/hobby/new")
    public ResponseEntity<Object> addHobbyInfo(@RequestBody String hobbyInfo) {
        serviceProvider.getIPersonService().goAddHobbyInfo(hobbyInfo, serviceProvider);
        ServiceResponse<String> response = new ServiceResponse<String>("success", hobbyInfo);
        return new ResponseEntity<Object>(response, HttpStatus.OK);
    }

    @PostMapping("/uploadFoto")
    public String uploadPhoto(@RequestParam("uploadfile") MultipartFile file) throws IOException {
        serviceProvider.getIPersonService().goUploadPhoto(file, serviceProvider);
        return "SUCCES";
    }

    @GetMapping("/givePhoto")
    ResponseEntity<Object> giveMyPhoto() {
        String pom = serviceProvider.getIPersonService().goGivePhoto(serviceProvider);
        ServiceResponse<String> response = new ServiceResponse<>("success", pom);
        return new ResponseEntity<Object>(response, HttpStatus.OK);
    }

}







