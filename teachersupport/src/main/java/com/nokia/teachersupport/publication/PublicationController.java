package com.nokia.teachersupport.publication;

import com.nokia.teachersupport.model.IModelService;
import com.nokia.teachersupport.person.IPersonService;
import com.nokia.teachersupport.personSecurity.IUserSecurityDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class PublicationController {

    /* To cos to tak naprawde nie zwraca string tylko tutaj mamy parsowanie calej str html na string jakby
     * strone index on nam zparsuje na string ktory jest czytelny dla app  */
    private IPublicationService publicationService;
    private IModelService modelService;
    private IPersonService personService;
    private IUserSecurityDataService userSecurityDataService;

    @Autowired
    public PublicationController(IPublicationService publicationService, IModelService modelService, IPersonService personService, IUserSecurityDataService userSecurityDataService) {
        this.publicationService = publicationService;
        this.modelService = modelService;
        this.personService = personService;
        this.userSecurityDataService = userSecurityDataService;
    }

    @GetMapping("/teacherSupportPublications")
    String publication(Model model) {
        modelService.publicationModel(model);
        return "teacherSupportPublications";
    }

    @PostMapping("/publication/new")
    String addNewPublication(Publication publication) {
        publicationService.addNewPublication(publication, personService, userSecurityDataService);
        return "redirect:/teacherSupportPublications";
    }


    @PostMapping("/teacherSupportPublications/editPubli")
    String editPublication(EditPublicationDTO editPublicationDTO) {
        publicationService.goEditPublication(editPublicationDTO, personService, userSecurityDataService);
        return "redirect:/teacherSupportPublications";
    }
}


