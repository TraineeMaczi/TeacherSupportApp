package com.nokia.teachersupport.publications;

import com.nokia.teachersupport.publications.PublicationsRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class PublicationsLoader implements ApplicationListener<ApplicationReadyEvent> {
    private PublicationsRepo publicationsRepo;

    @Autowired
    public PublicationsLoader(PublicationsRepo publicationsRepo) {
        this.publicationsRepo = publicationsRepo;
    }

    @Override
    public void onApplicationEvent(ApplicationReadyEvent applicationReadyEvent) {
        insertNewPublications();
    }

    @Transactional
    private void insertNewPublications() {
//        Publications newPublications1 = new Publications();
//        newPublications1.setPublicationsInfoField("Publication 1 Hi I am Publication !");
//        publicationsRepo.save(newPublications1);
//
//        Publications newPublications2 = new Publications();
//        newPublications2.setPublicationsInfoField("Publication 2 Hi I am Publication !");
//        publicationsRepo.save(newPublications2);
    }
}
