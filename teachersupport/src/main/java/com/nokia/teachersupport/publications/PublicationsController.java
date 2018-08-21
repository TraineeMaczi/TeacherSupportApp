package com.nokia.teachersupport.publications;

import com.nokia.teachersupport.currentUser.CurrentUser;
import com.nokia.teachersupport.model.IModelService;
import org.hibernate.cfg.ImprovedNamingStrategy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Objects;

@Controller
public class PublicationsController {

    /* To cos to tak naprawde nie zwraca string tylko tutaj mamy parsowanie calej str html na string jakby
     * strone index on nam zparsuje na string ktory jest czytelny dla app  */
    private IPublicationsService publicationService;
    private IModelService modelService;
    @Autowired
    public PublicationsController(IPublicationsService publicationService, IModelService modelService) {
        this.publicationService = publicationService;
        this.modelService=modelService;
    }

    @GetMapping("/teacherSupportPublications")
    String publications(Model model) {
        modelService.publicationsModel(model);
        return "teacherSupportPublications";
    }

    @PostMapping("/publications/new")
    String addNewPublications(Publications publications) {
        publicationService.savePublications(publications);
        return "redirect:/teacherSupportPublications";
    }


    @PostMapping("/teacherSupportPublications/editPubli")
    String editPublications(EditPublicationDTO editPublicationDTO) {
        publicationService.goEditPublications(editPublicationDTO);
        return "redirect:/teacherSupportPublications";
    }
}


