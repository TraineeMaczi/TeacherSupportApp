package com.nokia.teachersupport.personSecurity.personRegister;

import com.nokia.teachersupport.admin.IAdminDashboardService;
import com.nokia.teachersupport.personSecurity.UserSecurityData;
import com.nokia.teachersupport.personSecurity.personRegister.verificationToken.VerificationToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.Calendar;
import java.util.Locale;

@Controller
public class RegisterController {


    @Autowired
    ApplicationEventPublisher eventPublisher;

    private IAdminDashboardService adminDashboardService;

    //Z tego interesuje mnie tylko securityDataRepo

    @Autowired
    public RegisterController(IAdminDashboardService adminDashboardService) {
        this.adminDashboardService = adminDashboardService;
    }

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


        if((adminDashboardService.getUserSecurityDataByEmail(registerDTO.getUserName_Email())!=null)&&
                (adminDashboardService.getUserSecurityDataByEmail(registerDTO.getUserName_Email()).getActive()==false))
        {
            String appUrl = request.getContextPath();

            UserSecurityData registered=adminDashboardService.getUserSecurityDataByEmail(registerDTO.getUserName_Email());

            eventPublisher.publishEvent(new OnRegistrationCompleteEvent
                    (registered, request.getLocale(), appUrl));
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

        VerificationToken verificationToken = adminDashboardService.getVerificationToken(token);
        if (verificationToken == null) {
            return "redirect:/teacherSupportRegisterF";
        }

        UserSecurityData user = verificationToken.getUser();
        Calendar cal = Calendar.getInstance();

        if ((verificationToken.getExpiryDate().getTime() - cal.getTime().getTime()) <= 0) {
            return "redirect:/teacherSupportRegisterF";
        }

        user.setActive(true);
        adminDashboardService.saveUserSecurityDataAdminAction(user);

        return "redirect:/teacherSupportRegisterS";
    }

}
