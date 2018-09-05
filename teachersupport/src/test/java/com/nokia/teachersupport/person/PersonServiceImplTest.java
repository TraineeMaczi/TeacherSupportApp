package com.nokia.teachersupport.person;

import com.nokia.teachersupport.personSecurity.UserSecurityData;
import com.nokia.teachersupport.personSecurity.UserSecurityDataServiceImpl;
import com.nokia.teachersupport.roles.SecurityRole;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.anyObject;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

@RunWith(MockitoJUnitRunner.class)
public class PersonServiceImplTest {
    Person person1 = new Person();
    Person person2 = new Person();
    Person person3 = new Person();
    List<Person> persons = new ArrayList<>();
    List<Person> admins = new ArrayList<>();
    UserSecurityData userSecurityData = new UserSecurityData();
    SecurityRole securityRole = new SecurityRole();
    List<SecurityRole> securityRoles = new ArrayList<>();
    @InjectMocks
    UserSecurityDataServiceImpl userSecurityDataService;
    @Mock
    PersonServiceImpl personService;
    @Mock
    PersonRepo personRepo;
//    public void deleteAllPersons(IUserSecurityDataService userSecurityDataService) {
//        boolean toDelete;
//        for (Person person : personRepo.findAll()) {
//            toDelete = true;
//            for (SecurityRole securityRole : person.getUserSecurityDataField().getMyRoles())
//                if (securityRole.getRoleName().equals("ADMIN"))
//                    toDelete = false;
//            if (toDelete)
//                deletePerson(person,userSecurityDataService);
//        }
//    }
    @Before
    public void SetUp() {
        securityRole.setRoleName("ADMIN");
        securityRoles.add(securityRole);
        userSecurityData.setMyRoles(securityRoles);
        person1.setUserSecurityDataField(userSecurityData);
        persons.add(person1);
        admins.add(person1);
        securityRole.setRoleName("USER");
        securityRoles.clear();
        securityRoles.add(securityRole);
        userSecurityData.setMyRoles(securityRoles);
        person2.setUserSecurityDataField(userSecurityData);
        persons.add(person2);
        userSecurityData.setMyRoles(securityRoles);
        person3.setUserSecurityDataField(userSecurityData);
        persons.add(person3);
        when(personRepo.findAll()).thenReturn(persons);
        when(personService.deletePerson(person1, userSecurityDataService)).thenReturn(persons.remove(person1));
        when(personService.deletePerson(person2, userSecurityDataService)).thenReturn(persons.remove(person2));
        when(personService.deletePerson(person3, userSecurityDataService)).thenReturn(persons.remove(person3));
    }

    @Test
    public void deleteAllPersons() {

        personService.deleteAllPersons(userSecurityDataService);
        assertEquals(admins, persons);

    }
}