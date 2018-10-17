package com.nokia.teachersupport.publication;

import com.nokia.teachersupport.person.ServiceResponse;
import com.nokia.teachersupport.serviceProvider.IServiceProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PublicationRESTController {

   private IServiceProvider serviceProvider;

    @Autowired
    public PublicationRESTController(IServiceProvider serviceProvider) {
       this.serviceProvider=serviceProvider;
    }



    @PostMapping("/publication/delete")
    public ResponseEntity<Object> deletePublications(@RequestBody String publiContent) {
        serviceProvider.getIPublicationService().deletePublicationByContent(publiContent,serviceProvider);
        ServiceResponse<String> response = new ServiceResponse<String>("success", publiContent);
        return new ResponseEntity<Object>(response, HttpStatus.OK);
    }


}
