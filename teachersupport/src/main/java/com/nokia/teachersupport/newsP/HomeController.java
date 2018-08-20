package com.nokia.teachersupport.newsP;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
public class HomeController {
    private INewsService newsService;

    @Autowired
    public HomeController(INewsService newsService) {
        this.newsService = newsService;
    }

    @GetMapping("/teacherSupportHome")
    String tshome(Model model) throws InterruptedException {
        newsService.tshomemodel(model);
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
