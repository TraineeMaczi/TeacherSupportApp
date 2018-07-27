package com.nokia.teachersupport.personSecurity.personRegister;

import com.nokia.teachersupport.admin.IAdminDashboardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class RegisterController {

    private IAdminDashboardService adminDashboardService;

    //Z tego interesuje mnie tylko securityDataRepo

    @Autowired
    public RegisterController(IAdminDashboardService adminDashboardService)
    {this.adminDashboardService=adminDashboardService;}

@GetMapping("/teacherSupportRegister")
    String goRegister(Model model)
    {
        return "teacherSupportRegister";
    }
}
