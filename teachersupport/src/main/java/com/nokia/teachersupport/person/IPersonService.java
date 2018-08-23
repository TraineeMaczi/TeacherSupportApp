package com.nokia.teachersupport.person;

import com.nokia.teachersupport.admin.UserDTOForAdminAction;
import com.nokia.teachersupport.faculty.IFacultyService;
import com.nokia.teachersupport.personSecurity.IUserSecurityDataService;
import com.nokia.teachersupport.personSecurity.UserSecurityData;
import com.nokia.teachersupport.roles.IRoleService;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.io.InputStream;
import java.util.List;

public interface IPersonService {

    List<Person> listOfAllPersons();

    Person getPerson(Integer id);
    Person savePerson(Person person);



    void deletePerson(Person person, IUserSecurityDataService userSecurityDataService);

    Person getPersonByUserSecurityData(UserSecurityData userSecurityData);
    void setPersonBasicInfo(BasicInfoDTO basicInfoDTO,Person person);


    void deleteAllPersons(IUserSecurityDataService userSecurityDataService);







    boolean savePersonsFromFile(InputStream stream, IUserSecurityDataService userSecurityDataService, IFacultyService facultyService, IRoleService roleService);



    void addUser(UserDTOForAdminAction userDTOForAdminActionDTO, IUserSecurityDataService userSecurityDataService, IFacultyService facultyService, IRoleService roleService);
}
