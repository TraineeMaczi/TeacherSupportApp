package com.nokia.teachersupport.controllers;

import com.nokia.teachersupport.entity.News;
import com.nokia.teachersupport.service.INewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class HomeController {
    private INewsService newsService;

    @Autowired
    public HomeController(INewsService newsService) {
        this.newsService = newsService;
    }

    @GetMapping("/teacherSupportHome")
    String tshome(Model model){
        model.addAttribute("news", newsService.listOfAllNews());
        model.addAttribute("newNews", new News());
        return "teacherSupportHome";
    }

    @PostMapping("/tshome/new") //tu zmienilam z malych
    String addNewNews(News news){
        newsService.saveNews(news);
        return "redirect:/teacherSupportHome";
    }
}
