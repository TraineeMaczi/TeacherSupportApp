package com.nokia.teachersupport.publications;

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
    public Publications goEditPublications(EditPublicationDTO editPublicationDTO) {
        Publications publication = new Publications();
        if (publicationsRepo.findByPublicationsInfoField(editPublicationDTO.oldContent) != null) {
        publication=publicationsRepo.findByPublicationsInfoField(editPublicationDTO.oldContent);
        publication.setPublicationsInfoField(editPublicationDTO.getNewContent());
        publicationsRepo.save(publication);
        }
        return publication;
    }

    @Override
    public void deletePublicationByContent(String publiContent) {
        publicationsRepo.delete(publicationsRepo.findByPublicationsInfoField(publiContent));
    }

    @Override
    public boolean publicationExists(Publications publications) {
        return publicationsRepo.findByPublicationsInfoField(publications.getPublicationsInfoField()) == null;
    }
}

