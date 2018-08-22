package com.nokia.teachersupport.newsP;

import org.springframework.ui.Model;

import java.util.List;

public interface INewsService
{
    List<News> listOfAllNews();

    News getNews(Integer id);
    News saveNews(News news);
    void deleteNews(Integer id);
    void addNews(News news);
    News findNewsByContent(String content);
    News goEditNews(EditNewsDTO editNewsDTO);
    void deleteNewsByContent(String newsContent);


}
