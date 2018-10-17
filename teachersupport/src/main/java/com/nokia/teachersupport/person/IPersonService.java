package com.nokia.teachersupport.person;

import com.nokia.teachersupport.admin.UserDTOForAdminAction;
import com.nokia.teachersupport.faculty.Faculty;
import com.nokia.teachersupport.personSecurity.UserSecurityData;
import com.nokia.teachersupport.serviceProvider.IServiceProvider;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.io.InputStream;
import java.util.List;

public interface IPersonService {

    List<Person> listOfAllPersons();

    Person getPerson(Integer id);
    Person savePerson(Person person);
    boolean deletePerson(Person person,IServiceProvider serviceProvider,
                         HttpSession session);
    Person getPersonByUserSecurityData(UserSecurityData userSecurityData);
    void setPersonBasicInfo(BasicInfoDTO basicInfoDTO,Person person);
    void deleteAllPersons(IServiceProvider serviceProvider,HttpSession session);
    boolean savePersonsFromFile(InputStream stream, IServiceProvider serviceProvider);
    void addUser(UserDTOForAdminAction userDTOForAdminActionDTO,IServiceProvider serviceProvider);
    Faculty goSaveMyFaculty(String facultyName,IServiceProvider serviceProvider);
    List<String> goGiveMeFacultyPhoto(IServiceProvider serviceProvider);
    List<Integer> goGiveMeFacultyId(IServiceProvider serviceProvider);
    BasicInfoDTO goAddBasicInfo(BasicInfoDTO basicInfoDTO,IServiceProvider serviceProvider);
    String goAddHobbyInfo(String hobbyInfo,IServiceProvider serviceProvider);
    void goUploadPhoto(MultipartFile file, IServiceProvider serviceProvider);
    String goGivePhoto(IServiceProvider serviceProvider);
    Person getCurrentPerson(IServiceProvider serviceProvider);
}
