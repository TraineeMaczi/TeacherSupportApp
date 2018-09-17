package com.nokia.teachersupport.newsP;

import com.nokia.teachersupport.person.IPersonService;
import com.nokia.teachersupport.person.Person;
import com.nokia.teachersupport.personSecurity.IUserSecurityDataService;
import com.nokia.teachersupport.serviceProvider.IServiceProvider;
import org.springframework.security.web.util.matcher.IpAddressMatcher;
import org.springframework.ui.Model;

import javax.persistence.Id;
import java.util.List;

public interface INewsService
{
    void addNews(News news, IServiceProvider serviceProvider);
    News goEditNews(EditNewsDTO editNewsDTO, IPersonService personService,IUserSecurityDataService userSecurityDataService);
    void deleteNewsByContent(String newsContent,IPersonService personService,IUserSecurityDataService userSecurityDataService);
    List<News> cleanMyNews(Person person,IPersonService personService);
}
