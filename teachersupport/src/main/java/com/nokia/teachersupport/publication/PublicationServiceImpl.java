package com.nokia.teachersupport.publication;

import com.nokia.teachersupport.person.IPersonService;
import com.nokia.teachersupport.person.Person;
import com.nokia.teachersupport.personSecurity.IUserSecurityDataService;
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
    public List<Publication> listOfAllPublications() {
        return publicationRepo.findAll();
    }

    @Override
    public Publication getPublication(Integer id) {
        Optional<Publication> publicationsOpt = publicationRepo.findById(id);
        Publication publication = publicationsOpt.orElse(new Publication());
        return publication;
    }

    @Override
    public Publication savePublication(Publication publication) {
        return publicationRepo.save(publication);
    }

    @Override
    public void deletePublication(Integer id) {
        publicationRepo.deleteById(id);
    }

    @Override
    public Publication getByPublicationInfoField(String publicationsInfoField) {
        return publicationRepo.findByPublicationInfoField(publicationsInfoField);
    }

    @Override
    public Publication goEditPublication(EditPublicationDTO editPublicationDTO, IPersonService personService, IUserSecurityDataService userSecurityDataService) {
        Publication publication = new Publication();
        Person person = personService.getCurrentPerson(userSecurityDataService);
        if (person.doIHaveAPublicationWithContent(editPublicationDTO.getOldContent()) != null && !editPublicationDTO.getNewContent().equals("")) {
        publication=person.doIHaveAPublicationWithContent(editPublicationDTO.getOldContent());
        publication.setPublicationInfoField(editPublicationDTO.getNewContent());
        publicationRepo.save(publication);
        }
        return publication;
    }

    @Override
    public void deletePublicationByContent(String publiContent, IPersonService personService, IUserSecurityDataService userSecurityDataService) {
        Person person = personService.getCurrentPerson(userSecurityDataService);
        Publication publi=person.doIHaveAPublicationWithContent(publiContent);
        publicationRepo.delete(publi);
    }

    @Override
    public List<Publication> cleanMyPublications(Person person, IPersonService personService) {
        List<Publication> publicationList =person.getPersonPublicationList();
        for(Publication publi: publicationList)
        {
            publicationRepo.delete(publi);
        }
        publicationList.clear();
        personService.savePerson(person);
        return publicationList;
    }

    @Override
    public void addNewPublication(Publication publication, IPersonService personService, IUserSecurityDataService userSecurityDataService) {
        Person person = personService.getCurrentPerson(userSecurityDataService);
        if(person.doIHaveAPublicationWithContent(publication.getPublicationInfoField())==null) {
            publication.setPublicationOwner(person);
            person.addPubicationsToMyList(publication);
            personService.savePerson(person);
            publicationRepo.save(publication);
        }

    }


}

