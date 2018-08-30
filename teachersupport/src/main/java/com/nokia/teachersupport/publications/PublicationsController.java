package com.nokia.teachersupport.publications;

import com.nokia.teachersupport.model.IModelService;
import com.nokia.teachersupport.person.IPersonService;
import com.nokia.teachersupport.personSecurity.IUserSecurityDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class PublicationsController {

    /* To cos to tak naprawde nie zwraca string tylko tutaj mamy parsowanie calej str html na string jakby
     * strone index on nam zparsuje na string ktory jest czytelny dla app  */
    private IPublicationsService publicationService;
    private IModelService modelService;
    private IPersonService personService;
    private IUserSecurityDataService userSecurityDataService;
    @Autowired
    public PublicationsController(IPublicationsService publicationService, IModelService modelService,IPersonService personService,IUserSecurityDataService userSecurityDataService) {
        this.publicationService = publicationService;
        this.modelService=modelService;
        this.personService=personService;
        this.userSecurityDataService=userSecurityDataService;
    }

    @GetMapping("/teacherSupportPublications")
    String publications(Model model) {
        modelService.publicationsModel(model);
        return "teacherSupportPublications";
    }

    @PostMapping("/publications/new")
    String addNewPublications(Publications publications) {

        publicationService.addNewPublication(publications,personService,userSecurityDataService);

        return "redirect:/teacherSupportPublications";
    }


    @PostMapping("/teacherSupportPublications/editPubli")
    String editPublications(EditPublicationDTO editPublicationDTO) {
        publicationService.goEditPublications(editPublicationDTO,personService,userSecurityDataService);
        return "redirect:/teacherSupportPublications";
    }
}


