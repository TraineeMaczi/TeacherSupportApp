package com.nokia.teachersupport.newsP;

import com.nokia.teachersupport.person.ServiceResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class NewsRESTController {

    private INewsService newsService;

    @Autowired
    public NewsRESTController(INewsService newsService) {
        this.newsService = newsService;
    }

    @PostMapping("/tshome/delete")
    public ResponseEntity<Object> deleteNews(@RequestBody String newsContent) {
        newsService.deleteNewsByContent(newsContent);
        ServiceResponse<String> response = new ServiceResponse<String>("success", newsContent);
        return new ResponseEntity<Object>(response, HttpStatus.OK);

    }

}

