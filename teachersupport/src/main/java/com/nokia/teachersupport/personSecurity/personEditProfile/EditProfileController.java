package com.nokia.teachersupport.personSecurity.personEditProfile;

import com.nokia.teachersupport.serviceProvider.IServiceProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class EditProfileController {

   IServiceProvider serviceProvider;

    @Autowired
    public EditProfileController(IServiceProvider serviceProvider) {
        this.serviceProvider=serviceProvider;
    }

    /* To cos to tak naprawde nie zwraca string tylko tutaj mamy parsowanie calej str html na string jakby
     * strone index on nam zparsuje na string ktory jest czytelny dla app  */
    @GetMapping("/teacherSupportEditProfile")
    String editprofile(Model model) {
        serviceProvider.getIModelService().editProfileModel(model);
        return "teacherSupportEditProfile";
    }
}