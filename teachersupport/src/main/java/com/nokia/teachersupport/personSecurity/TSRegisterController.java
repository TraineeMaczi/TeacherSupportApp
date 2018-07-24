package com.nokia.teachersupport.personSecurity;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;


@Controller
public class TSRegisterController {

    @Autowired
    private IUserService userService;

    @GetMapping("/teacherSupportRegister")
    public String tSRegisterGet(WebRequest request, Model model) {
        UserSecurityData userUserSecurityData = new UserSecurityData();
        model.addAttribute("user", userUserSecurityData); //UWAGA tego user mamy w widoku jako zmienna po tym on pozna  XD
        return "teacherSupportRegister";
    }

    @PostMapping ("/teacherSupportRegister")
    public ModelAndView registerUserAccount
            (@ModelAttribute("user") @Valid UserSecurityData accountDto,
             BindingResult result, WebRequest request, Errors errors) {
        UserSecurityData registered = new UserSecurityData();
        if (!result.hasErrors()) {
            registered = createUserAccount(accountDto, result);
        }
        if (registered == null) {
            result.rejectValue("email", "message.regError");
        }

        if (result.hasErrors()) {
            return new ModelAndView("teacherSupportRegister", "user", accountDto);
        }
        else {
            return new ModelAndView("teacherSupportLogIn", "user", accountDto);
        }

    }
    private UserSecurityData createUserAccount(UserSecurityData accountDto, BindingResult result) {
        UserSecurityData registered = null;
        try {

            registered = userService.registerNewUserAccount(accountDto);
        } catch (EmailExistsException e) {
            return null;
        }
        return registered;
    }

}