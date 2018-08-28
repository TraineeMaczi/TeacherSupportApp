package com.nokia.teachersupport.person;

import com.nokia.teachersupport.personSecurity.IUserSecurityDataService;
import com.nokia.teachersupport.personSecurity.UserSecurityData;
import com.nokia.teachersupport.personSecurity.UserSecurityDataRepo;
import com.nokia.teachersupport.personSecurity.UserSecurityDataServiceImpl;
import com.nokia.teachersupport.roles.SecutityRole;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

@RunWith(MockitoJUnitRunner.class)
public class PersonServiceImplTest {
//    Person person1 = new Person();
//    Person person2 = new Person();
//    Person person3 = new Person();
//    List<Person> persons = new ArrayList<>();
//    List<Person> admins = new ArrayList<>();
//    UserSecurityData userSecurityData = new UserSecurityData();
//    SecutityRole secutityRole = new SecutityRole();
//    List<SecutityRole> secutityRoles = new ArrayList<>();
//    @InjectMocks
//    UserSecurityDataServiceImpl userSecurityDataService;
//    @InjectMocks
//    PersonServiceImpl personService;
//    @Mock
//    PersonRepo personRepo;
//    public void deleteAllPersons(IUserSecurityDataService userSecurityDataService) {
//        boolean toDelete;
//        for (Person person : personRepo.findAll()) {
//            toDelete = true;
//            for (SecutityRole securityRole : person.getUserSecurityDataField().getMyRoles())
//                if (securityRole.getRoleName().equals("ADMIN"))
//                    toDelete = false;
//            if (toDelete)
//                deletePerson(person,userSecurityDataService);
//        }
//    }
//    @Before
//    public void SetUp() {
//        secutityRole.setRoleName("ADMIN");
//        secutityRoles.add(secutityRole);
//        userSecurityData.setMyRoles(secutityRoles);
//        person1.setUserSecurityDataField(userSecurityData);
//        persons.add(person1);
//        admins.add(person1);
//        secutityRole.setRoleName("USER");
//        secutityRoles.clear();
//        secutityRoles.add(secutityRole);
//        userSecurityData.setMyRoles(secutityRoles);
//        person2.setUserSecurityDataField(userSecurityData);
//        persons.add(person2);
//        userSecurityData.setMyRoles(secutityRoles);
//        person3.setUserSecurityDataField(userSecurityData);
//        persons.add(person3);
//        when(personRepo.findAll()).thenReturn(persons);
//        for (Person person : persons) {
//            when(personService.deletePerson(person, userSecurityDataService)).thenReturn(persons.remove(person));
//        }
//    }
//
//    @Test
//    public void deleteAllPersons() {
//        personService.deleteAllPersons(userSecurityDataService);
//        assertEquals(admins, persons);
//
//    }
}