package com.nokia.teachersupport.personSecurity.personRegister;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class teacherSupportRegisterSController {


    @GetMapping("/teacherSupportRegisterS")
    String goTSRS()
    {
        return "teacherSupportRegisterS";
    }

}
