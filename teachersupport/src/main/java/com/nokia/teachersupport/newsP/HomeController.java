package com.nokia.teachersupport.newsP;

import com.nokia.teachersupport.currentUser.CurrentUser;
import com.nokia.teachersupport.person.IPersonService;
import com.nokia.teachersupport.person.Person;
import com.nokia.teachersupport.personSecurity.IUserSecurityDataService;
import com.nokia.teachersupport.personSecurity.UserSecurityData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Objects;

@Controller
public class HomeController {
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
    String tshome(Model model) throws InterruptedException {

        Person person = new Person();
        person = personService.getPersonByUserSecurityData(userSecurityDataService.getUserSecurityDataByEmail(CurrentUser.getCurrentUserName()));
        model.addAttribute("logInUser", person);
        model.addAttribute("news", person.getPersonNewsList());
        model.addAttribute("newNews", new News());
        model.addAttribute("currentUserName", Objects.requireNonNull(CurrentUser.getCurrentUserName()));
        return "teacherSupportHome";
    }

    @PostMapping("/tshome/new")
    String addNewNews(News news) {
        String userName = CurrentUser.getCurrentUserName();
        UserSecurityData userSecurityData = userSecurityDataService.getUserSecurityDataByEmail(userName);
        Person tmpPerson = personService.getPersonByUserSecurityData(userSecurityData);
        news.setNewsOwner(tmpPerson);
        tmpPerson.addNewsToMyList(news);
        personService.savePerson(tmpPerson);
        newsService.saveNews(news);
        return "redirect:/teacherSupportHome";
    }
    @PostMapping("/tshome/delete")
    String deleteNews(@RequestParam("id") Integer id) {
        newsService.deleteNews(id);
        return "SUCCES";
    }
}
