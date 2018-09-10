package com.nokia.teachersupport.publications;

import com.nokia.teachersupport.person.IPersonService;
import com.nokia.teachersupport.person.Person;
import com.nokia.teachersupport.personSecurity.IUserSecurityDataService;

import java.util.List;

public interface IPublicationsService {
    List<Publications> listOfAllPublications();

    Publications getPublications(Integer id);
    Publications savePublications(Publications publications);
    void deletePublications(Integer id);
    Publications getByPublicationsInfoField(String publicationsInfoField);
    Publications goEditPublications(EditPublicationDTO editPublicationDTO,IPersonService personService,IUserSecurityDataService userSecurityDataService);
    void deletePublicationByContent(String publiContent, IPersonService personService, IUserSecurityDataService userSecurityDataService);
    List<Publications> cleanMyPublications(Person person,IPersonService personService);
    void addNewPublication(Publications publications,IPersonService personService,IUserSecurityDataService userSecurityDataService);
}
