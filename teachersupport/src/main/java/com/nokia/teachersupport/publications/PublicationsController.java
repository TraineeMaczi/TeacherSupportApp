package com.nokia.teachersupport.publications;

import com.nokia.teachersupport.currentUser.CurrentUser;
import com.nokia.teachersupport.publications.Publications;
import com.nokia.teachersupport.publications.IPublicationsService;
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

    @Autowired
    public PublicationsController(IPublicationsService publicationService) {
        this.publicationService = publicationService;
    }

    @GetMapping("/teacherSupportPublications")
    String publications(Model model) {
        model.addAttribute("currentUserName", Objects.requireNonNull(CurrentUser.getCurrentUserName()));
        model.addAttribute("publications", publicationService.listOfAllPublications());
        model.addAttribute("newPublication", new Publications());
        model.addAttribute("editPubliPostObj",new EditPublicationDTO());
        return "teacherSupportPublications";
    }

    @PostMapping("/publications/new")
    String addNewPublications(Publications publications) {
        publicationService.savePublications(publications);
        return "redirect:/teacherSupportPublications";
    }

    @PostMapping("/publications/delete")
    String deletePublications(@RequestParam("id") Integer id) {
        publicationService.deletePublications(id);
        return "redirect:/teacherSupportPublications";
    }
    @PostMapping("/teacherSupportPublications/editPubli")
    String editPublications(EditPublicationDTO editPublicationDTO) {
        publicationService.goEditPublications(editPublicationDTO);
        return "redirect:/teacherSupportPublications";
    }
}


