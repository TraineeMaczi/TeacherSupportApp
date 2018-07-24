package com.nokia.teachersupport.publications;

import java.util.List;

public interface IPublicationsService {
    List<Publications> listOfAllPublications();

    Publications getPublications(Integer id);
    Publications savePublications(Publications publications);
    void deletePublications(Integer id);
}
