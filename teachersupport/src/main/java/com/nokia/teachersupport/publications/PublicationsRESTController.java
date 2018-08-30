package com.nokia.teachersupport.publications;

import com.nokia.teachersupport.person.IPersonService;
import com.nokia.teachersupport.person.ServiceResponse;
import com.nokia.teachersupport.personSecurity.IUserSecurityDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PublicationsRESTController {

    private IPublicationsService publicationService;
    private IPersonService personService;
    private IUserSecurityDataService userSecurityDataService;

    @Autowired
    public PublicationsRESTController(IPublicationsService publicationsService,IPersonService personService,IUserSecurityDataService userSecurityDataService) {
        this.publicationService = publicationsService;
        this.personService=personService;
        this.userSecurityDataService=userSecurityDataService;
    }



    @PostMapping("/publications/delete")
    public ResponseEntity<Object> deletePublications(@RequestBody String publiContent) {
        publicationService.deletePublicationByContent(publiContent,personService,userSecurityDataService);
        ServiceResponse<String> response = new ServiceResponse<String>("success", publiContent);
        return new ResponseEntity<Object>(response, HttpStatus.OK);
    }


}
