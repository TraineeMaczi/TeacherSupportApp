package com.nokia.teachersupport.newsP;

import com.nokia.teachersupport.model.IModelService;
import com.nokia.teachersupport.person.IPersonService;
import com.nokia.teachersupport.personSecurity.IUserSecurityDataService;
import com.nokia.teachersupport.serviceProvider.IServiceProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.thymeleaf.model.IModel;


@Controller
public class HomeController {
    private INewsService newsService;
    private IModelService modelService;
    private IPersonService personService;
    private IUserSecurityDataService userSecurityDataService;
    private IServiceProvider serviceProvider;
    @Autowired
    public HomeController(IServiceProvider serviceProvider,INewsService newsServic, IModelService modelService,IPersonService personService,IUserSecurityDataService userSecurityDataService)
    {
        this.modelService=modelService;
        this.newsService=newsServic;
        this.personService=personService;
        this.userSecurityDataService=userSecurityDataService;
        this.serviceProvider=serviceProvider;
    }

    @GetMapping("/teacherSupportHome")
    String tshome(Model model) throws InterruptedException {
        modelService.homeModel(model);
        return "teacherSupportHome";
    }

    @PostMapping("/tshome/new")
    String addNewNews(News news) {
        newsService.addNews(news,serviceProvider);
        return "redirect:/teacherSupportHome";
    }

    @PostMapping("/teacherSupportHome/editNews")
    String editNews(EditNewsDTO editNewsDTO) {
        newsService.goEditNews(editNewsDTO,personService,userSecurityDataService);
        return "redirect:/teacherSupportHome";
    }

}
