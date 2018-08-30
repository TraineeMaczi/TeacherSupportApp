package com.nokia.teachersupport.publications;

import com.nokia.teachersupport.person.IPersonService;
import com.nokia.teachersupport.person.Person;
import com.nokia.teachersupport.personSecurity.IUserSecurityDataService;
import com.nokia.teachersupport.tools.CurrentUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PublicationsServiceImpl implements IPublicationsService {
    private PublicationsRepo publicationsRepo;

    @Autowired
    public void setPublicationsRepo(PublicationsRepo publicationsRepo) {
        this.publicationsRepo = publicationsRepo;
    }

    @Override
    public List<Publications> listOfAllPublications() {
        return publicationsRepo.findAll();
    }

    @Override
    public Publications getPublications(Integer id) {
        Optional<Publications> publicationsOpt = publicationsRepo.findById(id);
        Publications publications = publicationsOpt.orElse(new Publications());
        return publications;
    }

    @Override
    public Publications savePublications(Publications publications) {
        return publicationsRepo.save(publications);
    }

    @Override
    public void deletePublications(Integer id) {
        publicationsRepo.deleteById(id);
    }

    @Override
    public Publications getByPublicationsInfoField(String publicationsInfoField) {
        return publicationsRepo.findByPublicationsInfoField(publicationsInfoField);
    }

    @Override
    public Publications goEditPublications(EditPublicationDTO editPublicationDTO,IPersonService personService,IUserSecurityDataService userSecurityDataService) {
        Publications publication = new Publications();
        Person person = personService.getPersonByUserSecurityData(userSecurityDataService.getUserSecurityDataByEmail(CurrentUser.getCurrentUserName()));
        if (person.doIHaveAPublicationWithContent(editPublicationDTO.getOldContent()) != null && !editPublicationDTO.getNewContent().equals("")) {
        publication=person.doIHaveAPublicationWithContent(editPublicationDTO.getOldContent());
        publication.setPublicationsInfoField(editPublicationDTO.getNewContent());
        publicationsRepo.save(publication);
        }
        return publication;
    }

    @Override
    public void deletePublicationByContent(String publiContent, IPersonService personService, IUserSecurityDataService userSecurityDataService) {
        Person person = personService.getPersonByUserSecurityData(userSecurityDataService.getUserSecurityDataByEmail(CurrentUser.getCurrentUserName()));
        Publications publi=person.doIHaveAPublicationWithContent(publiContent);
        publicationsRepo.delete(publi);
    }

    @Override
    public void addNewPublication(Publications publications, IPersonService personService, IUserSecurityDataService userSecurityDataService) {
        Person person = personService.getPersonByUserSecurityData(userSecurityDataService.getUserSecurityDataByEmail(CurrentUser.getCurrentUserName()));
        if(person.doIHaveAPublicationWithContent(publications.getPublicationsInfoField())==null) {
            publications.setPublicationOwner(person);
            person.addPubicationsToMyList(publications);
            personService.savePerson(person);
            publicationsRepo.save(publications);
        }

    }


}

