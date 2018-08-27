package com.nokia.teachersupport.faculty;

import com.nokia.teachersupport.fileUpload.IFileService;
import com.nokia.teachersupport.person.MeetMeServiceImpl;
import com.nokia.teachersupport.person.Person;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.io.File;

import static org.junit.Assert.*;

@RunWith(MockitoJUnitRunner.class)
public class FacultyServiceImplTest {

    @InjectMocks
    MeetMeServiceImpl meetMeService;
    @Mock
    IFileService fileService;

    @Test
    public void goDeleteFacultySiteAction() {
    Faculty faculty=new Faculty();
    }

    //TO DO bo nie wiem jak to ogarnac bo tego persona czy file tez powinnismy miec w bazie
}