package com.nokia.teachersupport.repositories;

import com.nokia.teachersupport.entity.News;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface NewsRepo extends CrudRepository<News, Integer>{
    List<News> findAll();
}
