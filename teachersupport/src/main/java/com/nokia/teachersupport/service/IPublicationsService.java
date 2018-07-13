package com.nokia.teachersupport.service;

import com.nokia.teachersupport.entity.Publications;

import java.util.List;

public interface IPublicationsService {
    List<Publications> listOfAllPublications();

    Publications getPublications(Integer id);
    Publications savePublications(Publications publications);
    void deletePublications(Integer id);
}
