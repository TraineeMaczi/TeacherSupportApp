/*To cos implementuje obserwatora , ktory reaguje na ContextRefreshedEvent*/
package com.nokia.teachersupport.newsP;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
//TODO lets create global loader for all entities
public class NewsLoader implements ApplicationListener<ApplicationReadyEvent> {
    private NewsRepo newsRepo;

    @Autowired
    public NewsLoader(NewsRepo newsRepo) {
        this.newsRepo = newsRepo;
    }

    @Override
    public void onApplicationEvent(ApplicationReadyEvent applicationReadyEvent) {
        insertNewNews();
    }

    //TODO cant private method be transactional
    @Transactional
    private void insertNewNews() {

    }
}
