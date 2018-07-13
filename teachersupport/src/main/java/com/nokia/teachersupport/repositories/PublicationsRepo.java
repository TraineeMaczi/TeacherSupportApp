package com.nokia.teachersupport.repositories;

import com.nokia.teachersupport.entity.Publications;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PublicationsRepo extends CrudRepository<Publications, Integer> {
    List<Publications> findAll();
}

