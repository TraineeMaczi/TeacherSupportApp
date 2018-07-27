package com.nokia.teachersupport.personSecurity.personLogIn;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class LogInController {
    @RequestMapping(value = "/teacherSupportLogIn",method = RequestMethod.GET)
    public String login(){
        return "teacherSupportLogIn";
    }
}
