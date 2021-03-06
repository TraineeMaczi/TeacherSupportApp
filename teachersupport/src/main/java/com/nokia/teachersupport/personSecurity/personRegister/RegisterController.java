package com.nokia.teachersupport.personSecurity.personRegister;


import com.nokia.teachersupport.personSecurity.UserSecurityData;
import com.nokia.teachersupport.personSecurity.personRegister.verificationToken.VerificationToken;
import com.nokia.teachersupport.serviceProvider.IServiceProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.Calendar;
import java.util.Locale;

@Controller
public class RegisterController {


    @Autowired
    ApplicationEventPublisher eventPublisher;
   @Autowired
    IServiceProvider serviceProvider;




    @GetMapping("/teacherSupportRegister")
    String goRegister(WebRequest request, Model model) {
        RegisterDTO registerDTO = new RegisterDTO();
        model.addAttribute("registerUserAction", registerDTO);
        return "teacherSupportRegister";
    }

    @PostMapping("/teacherSupportRegister")
    public ModelAndView registerUserAccount(
            @ModelAttribute("registerUserAction") @Valid RegisterDTO registerDTO,
            BindingResult result, WebRequest request, Errors errors) {

//sprawdzic czy mamy takiego w repo po email i czy jedgo active jest 0


        if((serviceProvider.getIUserSecurityDataService().getUserSecurityDataByEmail(registerDTO.getUserName_Email())!=null)&&
                (serviceProvider.getIUserSecurityDataService().getUserSecurityDataByEmail(registerDTO.getUserName_Email()).getActive().equals(false)))
        {
            String appUrl = request.getContextPath();

            UserSecurityData registered=serviceProvider.getIUserSecurityDataService().getUserSecurityDataByEmail(registerDTO.getUserName_Email());

          //!!UWAGA
           if(registerDTO.getUserPass().equals(registerDTO.getUserConfirmPass()))
                eventPublisher.publishEvent(new OnRegistrationCompleteEvent
                        (registered, request.getLocale(), appUrl,registerDTO.getUserPass()));
          else
               return new ModelAndView("teacherSupportRegisterF");


            //Tu trzeba wyslac e-mail potwierdzajacy
        return new ModelAndView("teacherSupportRegisterS");
        }
        else
        {
            //na razie jak cos nie tak to mowi ze blad i tyle nawet jak hasla nie poda czy cos
            return new ModelAndView("teacherSupportRegisterF");
        }

    }

    @GetMapping("/teacherSupportRegister/regitrationConfirm")
    public String confirmRegistration
            (WebRequest request, Model model, @RequestParam("token") String token) {

        Locale locale = request.getLocale();

        VerificationToken verificationToken = serviceProvider.getITokenService().getVerificationToken(token);
        if (verificationToken == null) {
            return "redirect:/teacherSupportRegisterF";
        }

        UserSecurityData user = verificationToken.getUser();
        Calendar cal = Calendar.getInstance();

        if ((verificationToken.getExpiryDate().getTime() - cal.getTime().getTime()) <= 0) {
            return "redirect:/teacherSupportRegisterF";
        }

        user.setPassword(verificationToken.getPassword());
        user.setMatchingPassword( verificationToken.getPassword());
        user.setActive(true);
        serviceProvider.getIUserSecurityDataService().saveUserSecurityData(user);

        return "redirect:/teacherSupportRegisterS";
    }

}
