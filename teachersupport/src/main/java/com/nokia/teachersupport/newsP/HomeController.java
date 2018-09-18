package com.nokia.teachersupport.newsP;

import com.nokia.teachersupport.serviceProvider.IServiceProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;


@Controller
public class HomeController {

    private IServiceProvider serviceProvider;
    @Autowired
    public HomeController(IServiceProvider serviceProvider)
    {
        this.serviceProvider=serviceProvider;
    }

    @GetMapping("/teacherSupportHome")
    String tshome(Model model) throws InterruptedException {
        serviceProvider.getIModelService().homeModel(model,serviceProvider);
        return "teacherSupportHome";
    }

    @PostMapping("/tshome/new")
    String addNewNews(News news) {
        serviceProvider.getINewsService().addNews(news,serviceProvider);
        return "redirect:/teacherSupportHome";
    }

    @PostMapping("/teacherSupportHome/editNews")
    String editNews(EditNewsDTO editNewsDTO) {
        serviceProvider.getINewsService().goEditNews(editNewsDTO,serviceProvider);
        return "redirect:/teacherSupportHome";
    }

}
