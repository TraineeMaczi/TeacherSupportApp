package com.nokia.teachersupport.person;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;


import static org.junit.Assert.*;
@RunWith(MockitoJUnitRunner.class)
public class PersonServiceImplTest {

    @InjectMocks
    PersonServiceImpl personService;
    @Mock
    PersonRepo personRepo;


    @Test
    public void setPersonBasicInfoSettingFieldsTest() {

        Person person=new Person();
        person.setPhoneNumberField("1234");
        person.setFacebookField("fb");
        person.setTwitterField("t");
        person.setUsosPersonProfileLinkField("usos");
        person.setProfessionField("profession");
        person.setDegreeField("degree");
        person.setWorkAddressField("work");

       BasicInfoDTO basicInfoDTO=new BasicInfoDTO();
        basicInfoDTO.setPhone("1234");
        basicInfoDTO.setFacebook("fb");
        basicInfoDTO.setTwitter("t");
        basicInfoDTO.setUsos("usos");
        basicInfoDTO.setProfession("profession");
        basicInfoDTO.setDegree("degree");
        basicInfoDTO.setWorkplace("work");

        Person personAfterSet=personService.setPersonBasicInfo(basicInfoDTO,new Person());
        assertEquals(person.getFacebookField(),personAfterSet.getFacebookField());
        assertEquals(person.getPhoneNumberField(),personAfterSet.getPhoneNumberField());
        assertEquals(person.getTwitterField(),personAfterSet.getTwitterField());
        assertEquals(person.getUsosPersonProfileLinkField(),personAfterSet.getUsosPersonProfileLinkField());
        assertEquals(person.getProfessionField(),personAfterSet.getProfessionField());
        assertEquals(person.getDegreeField(),personAfterSet.getDegreeField());
        assertEquals(person.getWorkAddressField(),personAfterSet.getWorkAddressField());
    }
}