package com.nokia.teachersupport.person;

import com.nokia.teachersupport.admin.UserDTOForAdminAction;
import com.nokia.teachersupport.personSecurity.UserSecurityData;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.io.InputStream;
import java.util.List;

public interface IPersonService {

    List<Person> listOfAllPersons();

    Person getPerson(Integer id);
    Person savePerson(Person person);
    void deletePerson(Person person);
    void deleteAllPersons();
    Person getPersonByUserSecurityData(UserSecurityData userSecurityData);
    void setPersonBasicInfo(BasicInfoDTO basicInfoDTO,Person person);
    boolean savePersonsFromFile(InputStream stream);
    void addUser(UserDTOForAdminAction userDTOForAdminActionDTO);
}
