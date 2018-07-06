package com.nokia.teachersupport.service;

import com.nokia.teachersupport.entity.News;
import com.nokia.teachersupport.repositories.NewsRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class NewsService {
    private NewsRepo newsRepo;

    @Autowired
    public NewsService(NewsRepo newsRepo) {
        this.newsRepo = newsRepo;
    }

    @Transactional(readOnly = true)
    public List<News> listAllNews(){
        return newsRepo.findAll();
    }
 }
