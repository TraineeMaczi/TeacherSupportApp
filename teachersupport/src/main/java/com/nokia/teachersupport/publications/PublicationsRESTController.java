package com.nokia.teachersupport.publications;

import com.nokia.teachersupport.person.ServiceResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PublicationsRESTController {

    private IPublicationsService publicationService;

    @Autowired
    public PublicationsRESTController(IPublicationsService publicationsService) {
        this.publicationService = publicationsService;
    }



    @PostMapping("/publications/delete")
    public ResponseEntity<Object> deletePublications(@RequestBody String publiContent) {
        publicationService.deletePublicationByContent(publiContent);
        ServiceResponse<String> response = new ServiceResponse<String>("success", publiContent);
        return new ResponseEntity<Object>(response, HttpStatus.OK);
    }


}
