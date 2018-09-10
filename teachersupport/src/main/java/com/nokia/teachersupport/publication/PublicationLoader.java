package com.nokia.teachersupport.publication;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class PublicationLoader implements ApplicationListener<ApplicationReadyEvent> {
    private PublicationRepo publicationRepo;

    @Autowired
    public PublicationLoader(PublicationRepo publicationRepo) {
        this.publicationRepo = publicationRepo;
    }

    @Override
    public void onApplicationEvent(ApplicationReadyEvent applicationReadyEvent) {
        insertNewPublications();
    }

    @Transactional
    private void insertNewPublications() {
//        Publication newPublications1 = new Publication();
//        newPublications1.setPublicationsInfoField("Publication 1 Hi I am Publication !");
//        publicationRepo.save(newPublications1);
//
//        Publication newPublications2 = new Publication();
//        newPublications2.setPublicationsInfoField("Publication 2 Hi I am Publication !");
//        publicationRepo.save(newPublications2);
    }
}
