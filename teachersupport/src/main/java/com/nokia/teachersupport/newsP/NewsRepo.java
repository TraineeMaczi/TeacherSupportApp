package com.nokia.teachersupport.newsP;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NewsRepo extends CrudRepository<News, Integer>{
    List<News> findAll();
    News findByNewsContentField(String newsContent);
}

