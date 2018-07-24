package com.nokia.teachersupport.newsP;

import java.util.List;

public interface INewsService
{
    List<News> listOfAllNews();

    News getNews(Integer id);
    News saveNews(News news);
    void deleteNews(Integer id);

}
