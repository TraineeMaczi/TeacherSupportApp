package com.nokia.teachersupport.personSecurity.personRegister;

import com.nokia.teachersupport.admin.IAdminDashboardService;
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
public class RegisterController {

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

            //Tu trzeba wyslac e-mail potwierdzajacy
        return new ModelAndView("teacherSupportRegisterS");
        }
        else
        {
            //na razie jak cos nie tak to mowi ze blad i tyle nawet jak hasla nie poda czy cos
            return new ModelAndView("teacherSupportRegisterF");
        }

    }

}
