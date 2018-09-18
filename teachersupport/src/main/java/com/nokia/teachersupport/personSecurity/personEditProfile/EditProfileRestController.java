package com.nokia.teachersupport.personSecurity.personEditProfile;

import com.nokia.teachersupport.serviceProvider.IServiceProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EditProfileRestController {
    private IServiceProvider serviceProvider;

    @Autowired
    public EditProfileRestController(IServiceProvider serviceProvider) {
        this.serviceProvider=serviceProvider;
    }

    @PostMapping("/change/name/{name}/{surname}")
    public String changeName(@PathVariable String name, @PathVariable String surname) {
        if(serviceProvider.getIEditProfileService().saveNameChange(name, surname))
            return "SUCCES";
        return "Error name and surname can not be empty";
    }

    @PostMapping("/change/email")
    public String changeEmail(@RequestParam String email, @RequestParam String confirmEmail) {
        if (serviceProvider.getIEditProfileService().saveEmailChange(email, confirmEmail))
            return "SUCCES";
        return "Error " + confirmEmail + " is different than " + email;
    }

    @PostMapping("/change/password")
    public String changePassword(@RequestParam String password, @RequestParam String confirmPassword) {
        if (serviceProvider.getIEditProfileService().savePasswordChange(password, confirmPassword))
            return "SUCCES";
        return "Error " + confirmPassword + " is different than " + password;
    }
}
