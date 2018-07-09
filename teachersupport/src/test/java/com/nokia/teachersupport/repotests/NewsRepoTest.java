package com.nokia.teachersupport.repotests;


import com.nokia.teachersupport.TeachersupportApplication;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;


@RunWith(SpringRunner.class)
@SpringBootTest(classes = TeachersupportApplication.class)
@WebAppConfiguration
public class NewsRepoTest {
    @Test
    public void contextLoads() {
    }
}
