package com.nokia.teachersupport.service;
import com.nokia.teachersupport.entity.News;

import java.util.List;

public interface INewsService
{
    List<News> listOfAllNews();

    News getNews(Integer id);
    News saveNews(News news);
    void deleteNews(Integer id);

}
