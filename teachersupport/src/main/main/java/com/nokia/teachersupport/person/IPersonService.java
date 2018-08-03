package com.nokia.teachersupport.person;

import com.nokia.teachersupport.personSecurity.UserSecurityData;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface IPersonService {

    List<Person> listOfAllPersons();

    Person getPerson(Integer id);
    Person savePerson(Person person);
    void deletePerson(Integer id);
    Person getPersonByUserSecurityData(UserSecurityData userSecurityData);
    //Person getPersonByName(String name);

}
