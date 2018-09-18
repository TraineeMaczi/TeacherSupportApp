package com.nokia.teachersupport.publication;

import com.nokia.teachersupport.serviceProvider.IServiceProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class PublicationController {
    private IServiceProvider serviceProvider;

    @Autowired
    public PublicationController(IServiceProvider serviceProvider) {
       this.serviceProvider=serviceProvider;
    }

    @GetMapping("/teacherSupportPublications")
    String publication(Model model) {
        serviceProvider.getIModelService().publicationModel(model,serviceProvider);
        return "teacherSupportPublications";
    }

    @PostMapping("/publication/new")
    String addNewPublication(Publication publication) {
        serviceProvider.getIPublicationService().addNewPublication(publication,serviceProvider);
        return "redirect:/teacherSupportPublications";
    }


    @PostMapping("/teacherSupportPublications/editPubli")
    String editPublication(EditPublicationDTO editPublicationDTO) {
        serviceProvider.getIPublicationService().goEditPublication(editPublicationDTO, serviceProvider);
        return "redirect:/teacherSupportPublications";
    }
}


