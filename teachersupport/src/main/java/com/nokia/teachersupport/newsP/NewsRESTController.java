package com.nokia.teachersupport.newsP;

import com.nokia.teachersupport.person.IPersonService;
import com.nokia.teachersupport.person.ServiceResponse;
import com.nokia.teachersupport.personSecurity.IUserSecurityDataService;
import com.nokia.teachersupport.serviceProvider.IServiceProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.Id;

@RestController
public class NewsRESTController {

   private IServiceProvider serviceProvider;

    @Autowired
    public NewsRESTController(IServiceProvider serviceProvider) {
      this.serviceProvider=serviceProvider;
    }
    
    @PostMapping("/tshome/delete")
    public ResponseEntity<Object> deleteNews(@RequestBody String newsContent) {
        serviceProvider.getINewsService().deleteNewsByContent(newsContent,serviceProvider);
        ServiceResponse<String> response = new ServiceResponse<String>("success", newsContent);
        return new ResponseEntity<Object>(response, HttpStatus.OK);

    }
}

