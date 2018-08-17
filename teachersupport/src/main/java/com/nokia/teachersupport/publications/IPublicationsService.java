package com.nokia.teachersupport.publications;

import java.util.List;

public interface IPublicationsService {
    List<Publications> listOfAllPublications();

    Publications getPublications(Integer id);
    Publications savePublications(Publications publications);
    void deletePublications(Integer id);
    Publications getByPublicationsInfoField(String publicationsInfoField);
    Publications goEditPublications(EditPublicationDTO editPublicationDTO);
    void deletePublicationByContent(String publiContent);
}
