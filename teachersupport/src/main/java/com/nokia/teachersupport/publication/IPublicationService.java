package com.nokia.teachersupport.publication;

import com.nokia.teachersupport.person.IPersonService;
import com.nokia.teachersupport.person.Person;
import com.nokia.teachersupport.personSecurity.IUserSecurityDataService;
import com.nokia.teachersupport.serviceProvider.IServiceProvider;

import java.util.List;

public interface IPublicationService {
    Publication goEditPublication(EditPublicationDTO editPublicationDTO, IServiceProvider serviceProvider);

    void deletePublicationByContent(String publiContent,IServiceProvider serviceProvider);

    List<Publication> cleanMyPublications(Person person, IServiceProvider serviceProvider);

    void addNewPublication(Publication publication,IServiceProvider serviceProvider);
}
