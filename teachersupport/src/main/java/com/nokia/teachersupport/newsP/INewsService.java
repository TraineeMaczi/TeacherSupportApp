package com.nokia.teachersupport.newsP;

import com.nokia.teachersupport.person.IPersonService;
import com.nokia.teachersupport.person.Person;
import com.nokia.teachersupport.personSecurity.IUserSecurityDataService;
import org.springframework.security.web.util.matcher.IpAddressMatcher;
import org.springframework.ui.Model;

import javax.persistence.Id;
import java.util.List;

public interface INewsService
{
    List<News> listOfAllNews();

    News getNews(Integer id);
    News saveNews(News news);
    void deleteNews(Integer id);
    void addNews(News news, IPersonService personService, IUserSecurityDataService userSecurityDataService);
    News findNewsByContent(String content);
    News goEditNews(EditNewsDTO editNewsDTO, IPersonService personService,IUserSecurityDataService userSecurityDataService);
    void deleteNewsByContent(String newsContent,IPersonService personService,IUserSecurityDataService userSecurityDataService);
    void deleteNewsById(Integer newsId);
    List<News> cleanMyNews(Person person,IPersonService personService);
}
