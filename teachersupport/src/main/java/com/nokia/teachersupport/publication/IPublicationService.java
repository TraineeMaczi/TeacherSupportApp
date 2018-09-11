package com.nokia.teachersupport.publication;

import com.nokia.teachersupport.person.IPersonService;
import com.nokia.teachersupport.person.Person;
import com.nokia.teachersupport.personSecurity.IUserSecurityDataService;

import java.util.List;

public interface IPublicationService {
    Publication goEditPublication(EditPublicationDTO editPublicationDTO, IPersonService personService, IUserSecurityDataService userSecurityDataService);

    void deletePublicationByContent(String publiContent, IPersonService personService, IUserSecurityDataService userSecurityDataService);

    List<Publication> cleanMyPublications(Person person, IPersonService personService);

    void addNewPublication(Publication publication, IPersonService personService, IUserSecurityDataService userSecurityDataService);
}
