package com.nokia.teachersupport.newsP;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.Assert.*;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class NewsServiceImplTest {

    EditNewsDTO editNewsDTO=new EditNewsDTO();
    News news=new News();


    @InjectMocks
    INewsService newsService;

    @Mock
    NewsRepo newsRepo;

    @Before
    public void SetUp()
    {
        news.setNewsContentField(editNewsDTO.getOldContent());
        editNewsDTO.setOldContent("Old Content");
        editNewsDTO.setNewContent("New Content");
        when(newsRepo.findByNewsContentField(editNewsDTO.getOldContent())).thenReturn(news);
    }
    @Test
    public void goEditNews() {

        //assertEquals(,newsService.goEditNews(editNewsDTO));

    }
}


//    public News goEditNews(EditNewsDTO editNewsDTO)
//    {
//        News news=new News();
//        if(newsRepo.findByNewsContentField(editNewsDTO.getOldContent())!=null) {
//            news = newsRepo.findByNewsContentField(editNewsDTO.getOldContent());
//            news.setNewsContentField(editNewsDTO.getNewContent());
//            newsRepo.save(news);
//        }
//        return news;
//    }