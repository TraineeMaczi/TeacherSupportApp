package com.nokia.teachersupport.person;

import com.nokia.teachersupport.admin.UserDTOForAdminAction;
import com.nokia.teachersupport.faculty.Faculty;
import com.nokia.teachersupport.faculty.IFacultyService;
import com.nokia.teachersupport.fileUpload.IFileService;
import com.nokia.teachersupport.newsP.INewsService;
import com.nokia.teachersupport.personSecurity.IUserSecurityDataService;
import com.nokia.teachersupport.personSecurity.UserSecurityData;
import com.nokia.teachersupport.publications.IPublicationsService;
import com.nokia.teachersupport.roles.IRoleService;
import com.nokia.teachersupport.studGroup.IGroupRemoteResourceService;
import com.nokia.teachersupport.studGroup.IStudGroupService;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.io.InputStream;
import java.util.List;

public interface IPersonService {

    List<Person> listOfAllPersons();

    Person getPerson(Integer id);
    Person savePerson(Person person);
    boolean deletePerson(Person person, IUserSecurityDataService userSecurityDataService, IMeetMeService meetMeService,
                         INewsService newsService, IPublicationsService publicationsService, IStudGroupService studGroupService, IFileService fileService, IGroupRemoteResourceService remoteResourceService,
                         HttpSession session);
    Person getPersonByUserSecurityData(UserSecurityData userSecurityData);
    void setPersonBasicInfo(BasicInfoDTO basicInfoDTO,Person person);
    void deleteAllPersons(IUserSecurityDataService userSecurityDataService,IMeetMeService meetMeService,INewsService newsService,
                          IPublicationsService publicationsService,IStudGroupService studGroupService,IFileService fileService,IGroupRemoteResourceService remoteResourceService,HttpSession session);
    boolean savePersonsFromFile(InputStream stream, IUserSecurityDataService userSecurityDataService, IFacultyService facultyService, IRoleService roleService);
    void addUser(UserDTOForAdminAction userDTOForAdminActionDTO, IUserSecurityDataService userSecurityDataService, IFacultyService facultyService, IRoleService roleService);
    Faculty goSaveMyFaculty(String facultyName,IPersonService personService,IFacultyService facultyService,IUserSecurityDataService userSecurityDataService);
    List<String> goGiveMeFacultyPhoto(IFacultyService facultyService);
    List<Integer> goGiveMeFacultyId(IFacultyService facultyService);
    BasicInfoDTO goAddBasicInfo(BasicInfoDTO basicInfoDTO,IUserSecurityDataService userSecurityDataService,IPersonService personService);
    String goAddHobbyInfo(String hobbyInfo,IPersonService personService,IUserSecurityDataService userSecurityDataService);
    void goUploadPhoto(MultipartFile file, IFileService fileService,IPersonService personService,IUserSecurityDataService userSecurityDataService);
    String goGivePhoto(IPersonService personService,IUserSecurityDataService userSecurityDataService);
    void goUploadCv(MultipartFile file,IFileService fileService,IPersonService personService,IUserSecurityDataService userSecurityDataService);

}
