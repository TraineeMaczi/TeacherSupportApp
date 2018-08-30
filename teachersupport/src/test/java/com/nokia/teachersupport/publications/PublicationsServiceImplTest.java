//package com.nokia.teachersupport.publications;
//
//import org.junit.Before;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.junit.MockitoJUnitRunner;
//
//import static org.junit.Assert.*;
//import static org.mockito.Mockito.when;
//
//@RunWith(MockitoJUnitRunner.class)
//public class PublicationsServiceImplTest {
//    EditPublicationDTO editPublicationDTO=new EditPublicationDTO();
//    Publications oldPuli=new Publications();
//    Publications newPubli=new Publications();
//
//    @InjectMocks
//    PublicationsServiceImpl publicationsService;
//
//    @Mock
//    PublicationsRepo publicationsRepo;
//    @Before
//    public void SetUp()
//    {
//        newPubli.setPublicationsInfoField("New Info");
//        oldPuli.setPublicationsInfoField("Old Info");
//        editPublicationDTO.setNewContent("New Info");
//        editPublicationDTO.setOldContent("Old Info");
//        when(publicationsRepo.findByPublicationsInfoField(editPublicationDTO.getOldContent())).thenReturn(oldPuli);
//        }
//    @Test
//    public void goEditPublications() {
//        assertEquals(newPubli.getPublicationsInfoField(),publicationsService.goEditPublications(editPublicationDTO,).getPublicationsInfoField()); //ed publi
//    }
//}
