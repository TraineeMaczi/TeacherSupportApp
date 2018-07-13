package com.nokia.teachersupport.controllers;

import com.nokia.teachersupport.entity.Publications;
import com.nokia.teachersupport.service.IPublicationsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class PublicationsController {

    /* To cos to tak naprawde nie zwraca string tylko tutaj mamy parsowanie calej str html na string jakby
     * strone index on nam zparsuje na string ktory jest czytelny dla app  */
    private IPublicationsService publicationService;

    @Autowired
    public PublicationsController(IPublicationsService publicationService){this.publicationService=publicationService;}
    @GetMapping("/teacherSupportPublications")
    String publications(Model model)
    {
        model.addAttribute("publications",publicationService.listOfAllPublications());
    model.addAttribute("newPublication",new Publications());
        return "teacherSupportPublications";
    }
    @PostMapping("/publications/new")
    String addNewPublications(Publications publications)
    {
        publicationService.savePublications(publications);
        return "redirect:/teacherSupportPublications";
    }
}


