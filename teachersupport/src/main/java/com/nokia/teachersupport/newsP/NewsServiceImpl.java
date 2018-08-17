package com.nokia.teachersupport.newsP;

import com.nokia.teachersupport.currentUser.CurrentUser;
import com.nokia.teachersupport.person.IPersonService;
import com.nokia.teachersupport.person.Person;
import com.nokia.teachersupport.personSecurity.IUserSecurityDataService;
import com.nokia.teachersupport.personSecurity.UserSecurityData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class NewsServiceImpl implements INewsService {

    private NewsRepo newsRepo;
    @Autowired
    private IPersonService personService;
    @Autowired
    private IUserSecurityDataService userSecurityDataService;
    @Autowired
    public void setNewsRepo(NewsRepo newsRepo) {
        this.newsRepo = newsRepo;
    }

    @Override
    public List<News> listOfAllNews() {
        return newsRepo.findAll();
    }

    @Override
    public News getNews(Integer id) {
        Optional<News> newsOpt = newsRepo.findById(id);
        News news = newsOpt.orElse(new News());
        return news;
    }


    @Override
    public News saveNews(News news) {
        return newsRepo.save(news);
    }



    @Override
    public void deleteNews(Integer id) {
        newsRepo.deleteById(id);
    }

    @Override
    public void tshomemodel(Model model) throws InterruptedException {
        Person person = new Person();
        person = personService.getPersonByUserSecurityData(userSecurityDataService.getUserSecurityDataByEmail(CurrentUser.getCurrentUserName()));
        model.addAttribute("logInUser", person);
        model.addAttribute("news", person.getPersonNewsList());
        model.addAttribute("newNews", new News());
        model.addAttribute("currentUserName", Objects.requireNonNull(CurrentUser.getCurrentUserName()));
        model.addAttribute("editNewsPostObj", new EditNewsDTO());
    }

    @Override
    public void addNews(News news) {
        String userName = CurrentUser.getCurrentUserName();
        UserSecurityData userSecurityData = userSecurityDataService.getUserSecurityDataByEmail(userName);
        Person tmpPerson = personService.getPersonByUserSecurityData(userSecurityData);
        news.setNewsOwner(tmpPerson);
        tmpPerson.addNewsToMyList(news);
        personService.savePerson(tmpPerson);
        newsRepo.save(news);
    }

    @Override
    public News findNewsByContent(String content) {
        return newsRepo.findByNewsContentField(content);
    }

    public News goEditNews(EditNewsDTO editNewsDTO)
    {
        News news=new News();
        if(newsRepo.findByNewsContentField(editNewsDTO.getOldContent())!=null) {
            news = newsRepo.findByNewsContentField(editNewsDTO.getOldContent());
            news.setNewsContentField(editNewsDTO.getNewContent());
            newsRepo.save(news);
        }
        return news;
    }
}
