package com.nokia.teachersupport.person;

import com.nokia.teachersupport.tools.CurrentUser;
import com.nokia.teachersupport.fileUpload.FileModel;
import com.nokia.teachersupport.fileUpload.IFileService;
import com.nokia.teachersupport.personSecurity.IUserSecurityDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Base64;

@RestController
public class AboutMeRESTController {
    private IPersonService personService;
    private IUserSecurityDataService userSecurityDataService;
    private IFileService fileService;

    @Autowired
    public AboutMeRESTController(IPersonService personService, IUserSecurityDataService userSecurityDataService, IFileService fileService) {
        this.personService = personService;
        this.userSecurityDataService = userSecurityDataService;
        this.fileService=fileService;
    }
    @PostMapping("/teacherSupportAboutMe/BasicInfo/new")
    public ResponseEntity<Object> addBasicInfo(@RequestBody BasicInfoDTO basicInfoDTO) {
        Person person=personService.getPersonByUserSecurityData(userSecurityDataService.getUserSecurityDataByEmail(CurrentUser.getCurrentUserName()));
        personService.setPersonBasicInfo(basicInfoDTO,person);
        personService.savePerson(person);
        ServiceResponse<BasicInfoDTO> response = new ServiceResponse<BasicInfoDTO>("success", basicInfoDTO);
        return new ResponseEntity<Object>(response, HttpStatus.OK);
    }

    @PostMapping("/teacherSupportAboutMe/hobby/new")
    public ResponseEntity<Object> addHobbyInfo(@RequestBody String hobbyInfo) {
        Person person=personService.getPersonByUserSecurityData(userSecurityDataService.getUserSecurityDataByEmail(CurrentUser.getCurrentUserName()));

        if (!hobbyInfo.equals(""))person.setHobbyField(hobbyInfo);

        personService.savePerson(person);

        ServiceResponse<String> response = new ServiceResponse<String>("success", hobbyInfo);
        return new ResponseEntity<Object>(response, HttpStatus.OK);
    }
    @PostMapping("/uploadFoto")
    public String uploadPhoto(@RequestParam("uploadfile") MultipartFile file) throws IOException {
        FileModel fileModel=fileService.saveMultipartFile(file, "personFoto");
        Person person=personService.getPersonByUserSecurityData(userSecurityDataService.getUserSecurityDataByEmail(CurrentUser.getCurrentUserName()));
        person.setFoto(fileModel);
        personService.savePerson(person);
        return "SUCCES";
    }
    @GetMapping("/givePhoto")
    ResponseEntity<Object> giveMyPhoto() {
        Person person=personService.getPersonByUserSecurityData(userSecurityDataService.getUserSecurityDataByEmail(CurrentUser.getCurrentUserName()));
        String pom;
        if(person.getFoto()==null)
            pom="img/logo.jpg";
        else
            pom="data:image/jpeg;base64,"+ Base64.getEncoder().encodeToString(person.getFoto().getPic());
        ServiceResponse<String> response = new ServiceResponse<>("success", pom);
        return new ResponseEntity<Object>(response, HttpStatus.OK);
    }
    @PostMapping("/uploadCV")
    public String uploadCV(@RequestParam("uploadfile") MultipartFile file) throws IOException {
        FileModel fileModel=fileService.saveMultipartFile(file, "personCV");
        Person person=personService.getPersonByUserSecurityData(userSecurityDataService.getUserSecurityDataByEmail(CurrentUser.getCurrentUserName()));
        person.setCV(fileModel);
        personService.savePerson(person);
        return "SUCCES";
    }
}







