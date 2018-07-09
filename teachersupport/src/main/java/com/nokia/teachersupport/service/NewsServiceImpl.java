package com.nokia.teachersupport.service;

import com.nokia.teachersupport.entity.News;
import com.nokia.teachersupport.repositories.NewsRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
}
