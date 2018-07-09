/*To cos implementuje obserwatora , ktory reaguje na ContextRefreshedEvent*/
package com.nokia.teachersupport.applicationlistner;

import com.nokia.teachersupport.entity.News;
import com.nokia.teachersupport.repositories.NewsRepo;
//import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

//import java.math.BigDecimal;

@Component
public class NewsLoader implements ApplicationListener<ContextRefreshedEvent> {

    private NewsRepo newsRepo;

  //  private Logger log = Logger.getLogger(NewsLoader.class);

    @Autowired //tu sie konstruktor robi z tego
    public void setNewsRepo(NewsRepo newsRepo) {
        this.newsRepo = newsRepo;
    }

    @Override //tu jest nadpisanie metody typu co ma sie stac gdy stan sie zmienia
    public void onApplicationEvent(ContextRefreshedEvent event) {


        News newNews1=new News();
        newNews1.setContent("News 1: Tmp Test News: Hello World !");
        newsRepo.save(newNews1);

        News newNews2=new News();
        newNews2.setContent("News 2: Tmp Test News: Hello World !");
        newsRepo.save(newNews2);

    }
}
