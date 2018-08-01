package com.nokia.teachersupport.generate;

import com.nokia.teachersupport.infrastructure.tools.UserTools;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Objects;

@Controller
public class GenerateController {

    /* NOPE! To cos to tak naprawde nie zwraca string tylko tutaj mamy parsowanie calej str html na string jakby
     * strone index on nam zparsuje na string ktory jest czytelny dla app  */
    /**
     * Z tego co zrozumialem wczesniej GET powinien zwracac nam paczke zip, ktora wrzucamy do folderu
     * z wordpressem i odrazu to hula
     */
    //TODO discuss
    @GetMapping("/teacherSupportGenerate")
    String generate(Model model){
        model.addAttribute("currentUserName",Objects.requireNonNull(UserTools.getCurrentUserName()));
        return "teacherSupportGenerate";
    }
}
