package com.nokia.teachersupport.applicationlistner;

import com.nokia.teachersupport.entity.Publications;
import com.nokia.teachersupport.repositories.NewsRepo;
import com.nokia.teachersupport.repositories.PublicationsRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

@Component
public class PublicationsLoader implements ApplicationListener<ContextRefreshedEvent> {
    private PublicationsRepo publicationsRepo;

    @Autowired
    public void setPublicationsRepo(PublicationsRepo publicationsRepo) {
        this.publicationsRepo = publicationsRepo;
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        Publications newPublications1 =new Publications();
        newPublications1.setPublicationsInfoField("Publication 1 Hi I am Publication !");
        publicationsRepo.save(newPublications1);

        Publications newPublications2 =new Publications();
        newPublications2.setPublicationsInfoField("Publication 2 Hi I am Publication !");
        publicationsRepo.save(newPublications2);
    }
}
