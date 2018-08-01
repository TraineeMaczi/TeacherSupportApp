package com.nokia.teachersupport.newsP;

import com.nokia.teachersupport.infrastructure.tools.UserTools;
import com.nokia.teachersupport.person.IPersonService;
import com.nokia.teachersupport.person.Person;
import com.nokia.teachersupport.personSecurity.IUserSecurityDataService;
import com.nokia.teachersupport.personSecurity.UserSecurityData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Objects;

@Controller
public class HomeController {
    //TODO naming convention to more meaningful
    private INewsService newsService;
    private IPersonService personService;
    private IUserSecurityDataService userSecurityDataService;


    @Autowired
    public HomeController(INewsService newsService, IPersonService personService, IUserSecurityDataService userSecurityDataService) {
        this.newsService = newsService;
        this.personService = personService;
        this.userSecurityDataService = userSecurityDataService;
    }

    @GetMapping("/teacherSupportHome")
    String tshome(Model model) {
        //TODO why new?
        Person person = new Person();
        //TODO what is that near?
        person = personService.getPersonByUserSecurityData(userSecurityDataService.getUserSecurityDataByEmail(UserTools.getCurrentUserName()));
        //TODO so many obj
        model.addAttribute("logInUser", person);
        model.addAttribute("news", person.getPersonNewsList());
        model.addAttribute("newNews", new News());
        model.addAttribute("currentUserName", Objects.requireNonNull(UserTools.getCurrentUserName()));
        return "teacherSupportHome";
    }

    @PostMapping("/tshome/new")
        //tu zmienilam z malych
    String addNewNews(News news) {
        String userName = UserTools.getCurrentUserName();
        UserSecurityData userSecurityData = userSecurityDataService.getUserSecurityDataByEmail(userName);
        Person tmpPerson = personService.getPersonByUserSecurityData(userSecurityData);
        news.setNewsOwner(tmpPerson);
        tmpPerson.addNewsToMyList(news);
        personService.savePerson(tmpPerson);
        newsService.saveNews(news);
        return "redirect:/teacherSupportHome";
    }
}
