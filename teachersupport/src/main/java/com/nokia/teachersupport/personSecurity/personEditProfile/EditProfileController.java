package com.nokia.teachersupport.personSecurity.personEditProfile;

import com.nokia.teachersupport.currentUser.CurrentUser;
import com.nokia.teachersupport.model.IModelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Objects;

@Controller
public class EditProfileController {

    IModelService modelService;
    @Autowired
    public EditProfileController(IModelService modelService) {
        this.modelService = modelService;
    }

    /* To cos to tak naprawde nie zwraca string tylko tutaj mamy parsowanie calej str html na string jakby
     * strone index on nam zparsuje na string ktory jest czytelny dla app  */
    @GetMapping("/teacherSupportEditProfile")
    String editprofile(Model model)
    {
        modelService.editProfileModel(model);
        return "teacherSupportEditProfile";
    }
}