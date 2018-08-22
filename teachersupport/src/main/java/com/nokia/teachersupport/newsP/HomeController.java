package com.nokia.teachersupport.newsP;

import com.nokia.teachersupport.model.IModelService;
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
    @Autowired
    public HomeController(INewsService newsServic, IModelService modelService)
    {
        this.modelService=modelService;
        this.newsService=newsServic;
    }

    @GetMapping("/teacherSupportHome")
    String tshome(Model model) throws InterruptedException {
        modelService.homeModel(model);
        return "teacherSupportHome";
    }

    @PostMapping("/tshome/new")
    String addNewNews(News news) {
        newsService.addNews(news);
        return "redirect:/teacherSupportHome";
    }

    @PostMapping("/teacherSupportHome/editNews")
    String editNews(EditNewsDTO editNewsDTO) {
        newsService.goEditNews(editNewsDTO);
        return "redirect:/teacherSupportHome";
    }

}
