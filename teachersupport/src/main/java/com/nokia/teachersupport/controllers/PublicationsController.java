package com.nokia.teachersupport.controllers;

import com.nokia.teachersupport.entity.Publications;
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
    private IPublicationService publicationService;
    @Autowired
    public PublicationsController(IPublicationService publicationService){this.publicationService=publicationService;}
    @GetMapping("/teacherSupportPublications")
    String publications(Model model)
    {
        model.addAtribute("publications",publicationService.listOfAllPublications());
    model.addAtribute("newPublication",new Publications());
        return "teacherSupportPublications";
    }
    @PostMapping("/publications/new")
    String addNewPublications(Publications publications)
    {
        publicationService.savePublications(publications);
        return "redirect:/teacherSupportPublications";
    }
}


