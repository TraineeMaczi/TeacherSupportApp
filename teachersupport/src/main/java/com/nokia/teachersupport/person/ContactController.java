package com.nokia.teachersupport.person;

import com.nokia.teachersupport.infrastructure.tools.UserTools;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Objects;

@Controller
public class ContactController {

    /* To cos to tak naprawde nie zwraca string tylko tutaj mamy parsowanie calej str html na string jakby
     * strone index on nam zparsuje na string ktory jest czytelny dla app  */
    @GetMapping("/teacherSupportContact")
    String contact(Model model){
        model.addAttribute("currentUserName",Objects.requireNonNull(UserTools.getCurrentUserName()));
        return "teacherSupportContact";
    }
}
