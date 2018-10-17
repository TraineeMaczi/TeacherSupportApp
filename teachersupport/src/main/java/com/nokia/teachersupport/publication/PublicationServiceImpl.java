package com.nokia.teachersupport.publication;

import com.nokia.teachersupport.person.IPersonService;
import com.nokia.teachersupport.person.Person;
import com.nokia.teachersupport.personSecurity.IUserSecurityDataService;
import com.nokia.teachersupport.serviceProvider.IServiceProvider;
import com.nokia.teachersupport.tools.CurrentUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PublicationServiceImpl implements IPublicationService {
    private PublicationRepo publicationRepo;

    @Autowired
    public void setPublicationRepo(PublicationRepo publicationRepo) {
        this.publicationRepo = publicationRepo;
    }


    @Override
    public Publication goEditPublication(EditPublicationDTO editPublicationDTO, IServiceProvider serviceProvider) {
        Publication publication = new Publication();
        Person person = serviceProvider.getIPersonService().getCurrentPerson(serviceProvider);
        if (person.doIHaveAPublicationWithContent(editPublicationDTO.getOldContent()) != null && !editPublicationDTO.getNewContent().equals("")) {
        publication=person.doIHaveAPublicationWithContent(editPublicationDTO.getOldContent());
        publication.setPublicationInfoField(editPublicationDTO.getNewContent());
        publicationRepo.save(publication);
        }
        return publication;
    }

    @Override
    public void deletePublicationByContent(String publiContent, IServiceProvider serviceProvider) {
        Person person = serviceProvider.getIPersonService().getCurrentPerson(serviceProvider);
        Publication publi=person.doIHaveAPublicationWithContent(publiContent);
        publicationRepo.delete(publi);
    }

    @Override
    public List<Publication> cleanMyPublications(Person person,IServiceProvider serviceProvider) {
        List<Publication> publicationList =person.getPersonPublicationList();
        for(Publication publi: publicationList)
        {
            publicationRepo.delete(publi);
        }
        publicationList.clear();
        serviceProvider.getIPersonService().savePerson(person);
        return publicationList;
    }

    @Override
    public void addNewPublication(Publication publication,IServiceProvider serviceProvider) {
        Person person = serviceProvider.getIPersonService().getCurrentPerson(serviceProvider);
        if(person.doIHaveAPublicationWithContent(publication.getPublicationInfoField())==null) {
            publication.setPublicationOwner(person);
            person.addPubicationsToMyList(publication);
            serviceProvider.getIPersonService().savePerson(person);
            publicationRepo.save(publication);
        }

    }


}

