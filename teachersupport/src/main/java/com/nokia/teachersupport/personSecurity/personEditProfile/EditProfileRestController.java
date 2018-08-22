package com.nokia.teachersupport.personSecurity.personEditProfile;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EditProfileRestController {
    private IEditProfileService editProfileService;

    @Autowired
    public EditProfileRestController(IEditProfileService editProfileService) {
        this.editProfileService = editProfileService;
    }

    @PostMapping("/change/name/{name}/{surname}")
    public void changeName(@PathVariable String name, @PathVariable String surname) {
        editProfileService.saveNameChange(name, surname);
    }

    @PostMapping("/change/email")
    public String changeEmail(@RequestParam String email, @RequestParam String confirmEmail) {
        if (editProfileService.saveEmailChange(email, confirmEmail))
            return "SUCCES";
        else
            return "Error " + confirmEmail + " is different than " + email;
    }

    @PostMapping("/change/password")
    public String changePassword(@RequestParam String password, @RequestParam String confirmPassword) {
        if (editProfileService.savePasswordChange(password, confirmPassword))
            return "SUCCES";
        else
            return "Error " + confirmPassword + " is different than " + password;
    }
}
