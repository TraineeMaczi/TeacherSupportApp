package com.nokia.teachersupport.person;

import com.nokia.teachersupport.currentUser.CurrentUser;
import com.nokia.teachersupport.personSecurity.IUserSecurityDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class AboutMeRESTController {
    private IPersonService personService;
    private IUserSecurityDataService userSecurityDataService;

    @Autowired
    public AboutMeRESTController(IPersonService personService, IUserSecurityDataService userSecurityDataService) {
        this.personService = personService;
        this.userSecurityDataService = userSecurityDataService;
    }
    @PostMapping("/teacherSupportAboutMe/BasicInfo/new")
    public ResponseEntity<Object> addBasicInfo(@RequestBody BasicInfoDTO basicInfoDTO) {
        Person person=new Person();
        person=personService.getPersonByUserSecurityData(userSecurityDataService.getUserSecurityDataByEmail(CurrentUser.getCurrentUserName()));

        person.setDegreeField(basicInfoDTO.getDegree());
        person.setWorkAddressField(basicInfoDTO.getWorkplace());
        person.setProfessionField(basicInfoDTO.getProfession());
        person.setUsosPersonProfileLinkField(basicInfoDTO.getUsos());
        person.setTwitterField(basicInfoDTO.getTwitter());
        person.setFacebookField(basicInfoDTO.getFacebook());

        personService.savePerson(person);

        ServiceResponse<BasicInfoDTO> response = new ServiceResponse<BasicInfoDTO>("success", basicInfoDTO);
        return new ResponseEntity<Object>(response, HttpStatus.OK);
    }

    @GetMapping("/teacherSupportAboutMe/BasicInfo")
    public ResponseEntity<Object> getBasicInfo() {
        BasicInfoDTO basicInfoDTO=new BasicInfoDTO();
        Person person=new Person();
        person=personService.getPersonByUserSecurityData(userSecurityDataService.getUserSecurityDataByEmail(CurrentUser.getCurrentUserName()));

        basicInfoDTO.setDegree(person.getDegreeField());
        basicInfoDTO.setWorkplace(person.getWorkAddressField());
        basicInfoDTO.setProfession(person.getProfessionField());
        basicInfoDTO.setUsos(person.getUsosPersonProfileLinkField());
        basicInfoDTO.setTwitter(person.getTwitterField());
        basicInfoDTO.setFacebook(person.getFacebookField());

        ServiceResponse<BasicInfoDTO> response = new ServiceResponse<>("success", basicInfoDTO);
        return new ResponseEntity<Object>(response, HttpStatus.OK);
    }
}







