package com.nokia.teachersupport.service;

import com.nokia.teachersupport.entity.Publications;
import com.nokia.teachersupport.repositories.PublicationsRepo;
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
    public Publications getPublications(Integer id)
    {
        Optional<Publications> publicationsOpt=publicationsRepo.findById(id);
        Publications publications=publicationsOpt.orElse(new Publications());
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
}

