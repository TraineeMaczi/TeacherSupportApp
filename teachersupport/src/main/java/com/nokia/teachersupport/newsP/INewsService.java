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
    News goEditNews(EditNewsDTO editNewsDTO,IServiceProvider serviceProvider);
    void deleteNewsByContent(String newsContent,IServiceProvider serviceProvider);
    List<News> cleanMyNews(Person person,IServiceProvider serviceProvider);
}
