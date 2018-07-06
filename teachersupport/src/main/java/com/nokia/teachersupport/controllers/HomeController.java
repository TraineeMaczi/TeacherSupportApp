package com.nokia.teachersupport.controllers;
import com.nokia.teachersupport.service.NewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {
    private NewsService newsService;

    @Autowired
    public HomeController(NewsService newsService) {
        this.newsService = newsService;
    }

    @RequestMapping("/TSHome")
    String tshome(Model model){
        model.addAttribute("news", newsService.listAllNews());
        return "TSHome";
    }
}
