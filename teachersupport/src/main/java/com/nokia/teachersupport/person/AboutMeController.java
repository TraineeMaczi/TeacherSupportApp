package com.nokia.teachersupport.person;

import com.nokia.teachersupport.infrastructure.tools.UserTools;
import com.nokia.teachersupport.personSecurity.IUserSecurityDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;

@Controller
public class AboutMeController {

    private IPersonService personService;
    private IUserSecurityDataService userSecurityDataService;

    @Autowired
    public AboutMeController (IPersonService personService,IUserSecurityDataService userSecurityDataService) {
        this.personService = personService;
        this.userSecurityDataService=userSecurityDataService;
    }

    @GetMapping("/teacherSupportAboutMe")
    String aboutme(Model model){
        Person person=new Person();
        person=personService.getPersonByUserSecurityData(userSecurityDataService.getUserSecurityDataByEmail(UserTools.getCurrentUserName()));
        model.addAttribute("currentUserName",Objects.requireNonNull(UserTools.getCurrentUserName()));
        model.addAttribute("currentUserPerson",person);
        return "teacherSupportAboutMe";
    }

    @PostMapping("/teacherSupportAboutMe/hobby/new")
    String hobbyAction()
    {
        //to tak na razie zaby sie nie wywalilo
        return "teacherSupportAboutMe";
    }

//    @PostMapping("/teacherSupportAboutMe/BasicInfo/new")
//    public @ResponseBody BasicInfoDTO postBasicInfo(BasicInfoDTO basicInfoDTO)
//    {
//        Person person=new Person();
//        person=personService.getPersonByUserSecurityData(userSecurityDataService.getUserSecurityDataByEmail(UserTools.getCurrentUserName()));
//
//        person.setDegreeField(basicInfoDTO.getDegree());
//        person.setWorkAddressField(basicInfoDTO.getWorkplace());
//        person.setProfessionField(basicInfoDTO.getProfession());
//        person.setUsosPersonProfileLinkField(basicInfoDTO.getUsos());
//        person.setTwitterField(basicInfoDTO.getTwitter());
//        person.setFacebookField(basicInfoDTO.getFacebook());
//
//        personService.savePerson(person);
//        return basicInfoDTO;
//    }
}
