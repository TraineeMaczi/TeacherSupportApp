package com.nokia.teachersupport.newsP;

import com.nokia.teachersupport.tools.CurrentUser;
import com.nokia.teachersupport.person.IPersonService;
import com.nokia.teachersupport.person.Person;
import com.nokia.teachersupport.personSecurity.IUserSecurityDataService;
import com.nokia.teachersupport.personSecurity.UserSecurityData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.web.util.matcher.IpAddressMatcher;
import org.springframework.stereotype.Service;

import javax.persistence.Id;
import java.util.List;
import java.util.Optional;

@Service
public class NewsServiceImpl implements INewsService {

    private NewsRepo newsRepo;

    @Autowired
    public void setNewsRepo(NewsRepo newsRepo) {
        this.newsRepo = newsRepo;
    }


    @Override
    public void addNews(News news, IPersonService personService, IUserSecurityDataService userSecurityDataService) {
        Person person = personService.getCurrentPerson(userSecurityDataService);
        if (person.doIHaveANewsWithContent(news.getNewsContentField()) == null) {
            String userName = CurrentUser.getCurrentUserName();
            UserSecurityData userSecurityData = userSecurityDataService.getUserSecurityDataByEmail(userName);
            Person tmpPerson = personService.getPersonByUserSecurityData(userSecurityData);
            news.setNewsOwner(tmpPerson);
            tmpPerson.addNewsToMyList(news);
            personService.savePerson(tmpPerson);
            newsRepo.save(news);
        }
    }


    @Override
    public News goEditNews(EditNewsDTO editNewsDTO, IPersonService personService, IUserSecurityDataService userSecurityDataService) {
        Person person = personService.getCurrentPerson(userSecurityDataService);
        News news = new News();

        if (person.doIHaveANewsWithContent(editNewsDTO.getOldContent()) != null && !editNewsDTO.getNewContent().equals("")) {
            news = person.doIHaveANewsWithContent(editNewsDTO.getOldContent());
            news.setNewsContentField(editNewsDTO.getNewContent());
            newsRepo.save(news);
        }
        return news;
    }

    @Override
    public void deleteNewsByContent(String newsContent, IPersonService personService, IUserSecurityDataService userSecurityDataService) {
        Person person = personService.getCurrentPerson(userSecurityDataService);
        News news = person.doIHaveANewsWithContent(newsContent);
        newsRepo.delete(news);
    }


    @Override
    public List<News> cleanMyNews(Person person, IPersonService personService) {
        List<News> newsPersonList = person.getPersonNewsList();
        for (News news : newsPersonList) {
            newsRepo.delete(news);
        }
        newsPersonList.clear();
        personService.savePerson(person);
        return newsPersonList;
    }
}
