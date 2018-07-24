package com.nokia.teachersupport.person;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IndexController {

    /* To cos to tak naprawde nie zwraca string tylko tutaj mamy parsowanie calej str html na string jakby
     * strone index on nam zparsuje na string ktory jest czytelny dla app  */
    @GetMapping("/")
    String index() {
        return "teacherSupportIndex";
    }


}
