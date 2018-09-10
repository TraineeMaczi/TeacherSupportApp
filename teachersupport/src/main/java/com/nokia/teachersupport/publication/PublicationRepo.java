package com.nokia.teachersupport.publication;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PublicationRepo extends CrudRepository<Publication, Integer> {
    List<Publication> findAll();
    Publication findByPublicationInfoField(String publicationInfoField);
}

