package com.nokia.teachersupport.repositories;

import com.nokia.teachersupport.entity.News;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NewsRepo extends CrudRepository<News, Integer>{
    List<News> findAll();
}

