package com.nokia.teachersupport.personSecurity.personEditProfile;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class EditProfileController {

    /* To cos to tak naprawde nie zwraca string tylko tutaj mamy parsowanie calej str html na string jakby
     * strone index on nam zparsuje na string ktory jest czytelny dla app  */
    @RequestMapping("/teacherSupportEditProfile")
    String editprofile(){
        return "teacherSupportEditProfile";
    }
}