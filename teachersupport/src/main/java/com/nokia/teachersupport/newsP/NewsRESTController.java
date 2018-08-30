package com.nokia.teachersupport.newsP;

import com.nokia.teachersupport.person.IPersonService;
import com.nokia.teachersupport.person.ServiceResponse;
import com.nokia.teachersupport.personSecurity.IUserSecurityDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.Id;

@RestController
public class NewsRESTController {

    private INewsService newsService;
    private IPersonService personService;
    private IUserSecurityDataService userSecurityDataService;

    @Autowired
    public NewsRESTController(INewsService newsService,IPersonService personService,IUserSecurityDataService userSecurityDataService) {
        this.newsService = newsService;
        this.personService=personService;
        this.userSecurityDataService=userSecurityDataService;
    }
    
    @PostMapping("/tshome/delete")
    public ResponseEntity<Object> deleteNews(@RequestBody String newsContent) {
        newsService.deleteNewsByContent(newsContent,personService,userSecurityDataService);
        ServiceResponse<String> response = new ServiceResponse<String>("success", newsContent);
        return new ResponseEntity<Object>(response, HttpStatus.OK);

    }
}

