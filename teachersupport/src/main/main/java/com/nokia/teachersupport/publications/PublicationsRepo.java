package com.nokia.teachersupport.publications;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PublicationsRepo extends CrudRepository<Publications, Integer> {
    List<Publications> findAll();
}

