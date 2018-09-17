package com.nokia.teachersupport.person;

import com.nokia.teachersupport.admin.UserDTOForAdminAction;
import com.nokia.teachersupport.faculty.Faculty;
import com.nokia.teachersupport.faculty.IFacultyService;
import com.nokia.teachersupport.fileUpload.FileModel;
import com.nokia.teachersupport.fileUpload.IFileService;
import com.nokia.teachersupport.newsP.INewsService;
import com.nokia.teachersupport.personSecurity.IUserSecurityDataService;
import com.nokia.teachersupport.personSecurity.UserSecurityData;
import com.nokia.teachersupport.publication.IPublicationService;
import com.nokia.teachersupport.roles.IRoleService;
import com.nokia.teachersupport.roles.SecurityRole;
import com.nokia.teachersupport.serviceProvider.IServiceProvider;
import com.nokia.teachersupport.studGroup.IGroupRemoteResourceService;
import com.nokia.teachersupport.studGroup.IStudGroupService;
import com.nokia.teachersupport.tools.CurrentUser;
import com.nokia.teachersupport.tools.GenerateLink;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.Optional;

@Service
public class PersonServiceImpl implements IPersonService {

    private PersonRepo personRepo;


    @Autowired
    public PersonServiceImpl(PersonRepo personRepo) {
        this.personRepo = personRepo;
    }

    @Override
    public List<Person> listOfAllPersons() {
        return personRepo.findAll();
    }

    @Override
    public Person getPerson(Integer id) {
        Optional<Person> Opt = personRepo.findById(id);
        Person person = Opt.orElse(new Person());
        return person;
    }

    @Override
    public Person savePerson(Person person) {
        return personRepo.save(person);
    }

    @Override
    public boolean deletePerson(Person person, IServiceProvider serviceProvider,
                                HttpSession session) {
        if(person.equals(getCurrentPerson(serviceProvider.getIUserSecurityDataService())))
            return false;
        Faculty faculty = person.getFacultyField();
        if (faculty != null) {
            faculty.getFacultyAndPersonList().remove(person);
            person.deleteFaculty(faculty);
        }
        for (SecurityRole securityRole : person.getUserSecurityDataField().getMyRoles())
            securityRole.getSecurityInsAndRoles().remove(person.getUserSecurityDataField());
        person.getUserSecurityDataField().getMyRoles().removeAll(person.getUserSecurityDataField().getMyRoles());
//TO DO wyczysc news publi i meetme

        serviceProvider.getIMeetMeService().cleanMyMeetMeData(person,this);
        serviceProvider.getINewsService().cleanMyNews(person,serviceProvider);
        serviceProvider.getIPublicationService().cleanMyPublications(person,this);
        serviceProvider.getIStudGroupService().cleanMyStudGrops(person,this,serviceProvider.getIFileService(),serviceProvider.getIGroupRemoteResourceService(),serviceProvider.getIUserSecurityDataService(),session);


        serviceProvider.getIUserSecurityDataService().deleteUserSecurityData(person.getUserSecurityDataField().getId());
        personRepo.delete(person);
        return true;
    }

    @Override
    public Person getPersonByUserSecurityData(UserSecurityData userSecurityData) {
        return personRepo.findByUserSecurityDataField(userSecurityData);
    }

    @Override
    public void setPersonBasicInfo(BasicInfoDTO basicInfoDTO, Person person) {
        if (!basicInfoDTO.getDegree().equals("")) person.setDegreeField(basicInfoDTO.getDegree());

        if (!basicInfoDTO.getWorkplace().equals("")) person.setWorkAddressField(basicInfoDTO.getWorkplace());

        if (!basicInfoDTO.getProfession().equals("")) person.setProfessionField(basicInfoDTO.getProfession());

        if (!basicInfoDTO.getUsos().equals("")) person.setUsosPersonProfileLinkField(GenerateLink.goGenerateLink(basicInfoDTO.getUsos()));

        if (!basicInfoDTO.getTwitter().equals("")) person.setTwitterField(GenerateLink.goGenerateLink(basicInfoDTO.getTwitter()));

        if (!basicInfoDTO.getFacebook().equals("")) person.setFacebookField(GenerateLink.goGenerateLink(basicInfoDTO.getFacebook()));

        if (!basicInfoDTO.getPhone().equals("")&&basicInfoDTO.getPhone().length()==9) person.setPhoneNumberField(basicInfoDTO.getPhone());


    }

    @Override
    public void deleteAllPersons(IServiceProvider serviceProvider,HttpSession session) {
        boolean toDelete;
        for (Person person : personRepo.findAll()) {
            toDelete = true;
            for (SecurityRole securityRole : person.getUserSecurityDataField().getMyRoles())
                if (securityRole.getRoleName().equals("ADMIN"))
                    toDelete = false;
            if (toDelete)
                deletePerson(person,serviceProvider,session);
        }
    }

    @Override
    public boolean savePersonsFromFile(InputStream stream, IServiceProvider serviceProvider) {
        String myName;
        String mySurname;
        String myFaculty;
        String myRole;
        String myEmail;
        try {
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(stream, StandardCharsets.UTF_8));
            String newline = bufferedReader.readLine();

            do {
                String[] parts = newline.split(",");
                myName = parts[0];
                mySurname = parts[1];
                myFaculty = parts[2];
                myRole = parts[3];
                myEmail = parts[4];
                SecurityRole securityRole;
                if ((serviceProvider.getIUserSecurityDataService().getUserSecurityDataByEmail(myEmail) == null) &&
                        (serviceProvider.getIFacultyService().findFaculty(myFaculty) != null) &&
                        ((serviceProvider.getIRoleService().findByRoleName(myRole) != null)||myRole.equals("BOTH"))) {
                    Person person = new Person();
                    UserSecurityData userSecurityData = new UserSecurityData();
                    Faculty faculty = serviceProvider.getIFacultyService().findFaculty(myFaculty);
                    if(myRole.equals("BOTH"))
                    {
                        securityRole = serviceProvider.getIRoleService().findByRoleName("USER");
                        userSecurityData.addARole(securityRole);
                        securityRole.addUserSecurityDataToRole(userSecurityData);
                        serviceProvider.getIRoleService().saveSecurityRole(securityRole);
                        myRole="ADMIN";
                    }
                    securityRole = serviceProvider.getIRoleService().findByRoleName(myRole);
                    person.setNameField(myName);
                    person.setSurnameField(mySurname);
                    faculty.addPersonToFaculty(person);
                    person.setFacultyField(faculty);
                    userSecurityData.setActive(false);
                    userSecurityData.setEmail(myEmail);
                    userSecurityData.setPassword("NULL");
                    userSecurityData.setMatchingPassword("NULL");
                    userSecurityData.addARole(securityRole);
                    securityRole.addUserSecurityDataToRole(userSecurityData);
                    person.setUserSecurityDataField(userSecurityData);
                    personRepo.save(person);
                    serviceProvider.getIUserSecurityDataService().saveUserSecurityData(userSecurityData);
                    serviceProvider.getIFacultyService().saveFaculty(faculty);
                        serviceProvider.getIRoleService().saveSecurityRole(securityRole);
                }
                newline = bufferedReader.readLine();

            } while (newline != null);
            bufferedReader.close();

            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public void addUser(UserDTOForAdminAction userDTOForAdminActionDTO, IServiceProvider serviceProvider) {
        //jak nie mam takiego e-mail w bazie jeszcze i jak wydzial istnieje i jesli rola istnieje
        if ((serviceProvider.getIUserSecurityDataService().getUserSecurityDataByEmail(userDTOForAdminActionDTO.getUserEmailDTOField()) == null) &&
                (serviceProvider.getIFacultyService().findFaculty(userDTOForAdminActionDTO.getUserFacultyDTOField()) != null))
        {

            Person person = new Person();
            UserSecurityData userSecurityData = new UserSecurityData();
            Faculty faculty = serviceProvider.getIFacultyService().findFaculty(userDTOForAdminActionDTO.getUserFacultyDTOField());

            SecurityRole securityRole;
            if (userDTOForAdminActionDTO.getUserRoleDTOField().equals("BOTH")) {
                securityRole =serviceProvider.getIRoleService().findByRoleName("USER");
                userSecurityData.addARole(securityRole);
                securityRole.addUserSecurityDataToRole(userSecurityData);
                serviceProvider.getIRoleService().saveSecurityRole(securityRole);
                securityRole = serviceProvider.getIRoleService().findByRoleName("ADMIN");

            } else {
                securityRole = serviceProvider.getIRoleService().findByRoleName(userDTOForAdminActionDTO.getUserRoleDTOField());
            }

            //Tak samo jak dla faculty musi byc security rolke

            person.setNameField(userDTOForAdminActionDTO.getUserNameDTOField());
            person.setSurnameField(userDTOForAdminActionDTO.getUserSurnameDTOField());


            faculty.addPersonToFaculty(person);//!
            person.setFacultyField(faculty); //to nie wiem czy potrzebne bo sa zabezpieczenia w obie str

            userSecurityData.setActive(false);
            userSecurityData.setEmail(userDTOForAdminActionDTO.getUserEmailDTOField());
            userSecurityData.setPassword("NULL"); //UWAGA CHYBA NIE DA SIE NA TO ZALOGOWAC
            userSecurityData.setMatchingPassword("NULL");
            serviceProvider.getIUserSecurityDataService().saveUserSecurityData(userSecurityData);
            userSecurityData.addARole(securityRole);
            securityRole.addUserSecurityDataToRole(userSecurityData);
            serviceProvider.getIRoleService().saveSecurityRole(securityRole);
            person.setUserSecurityDataField(userSecurityData);


            personRepo.save(person);
            serviceProvider.getIFacultyService().saveFaculty(faculty);
        }
    }

    @Override
    public Faculty goSaveMyFaculty(String facultyName, IPersonService personService, IFacultyService facultyService, IUserSecurityDataService userSecurityDataService) {
        Person person = getCurrentPerson(userSecurityDataService);
        person.setFacultyField(facultyService.findFaculty(facultyName));
        Faculty faculty = facultyService.findFaculty(facultyName);
        faculty.addPersonToFaculty(person);
        facultyService.saveFaculty(faculty);
        personService.savePerson(person);
        return faculty;
    }

    @Override
    public List<String> goGiveMeFacultyPhoto(IFacultyService facultyService) {
        List<Faculty> faculties = facultyService.listOfAllFaculties();
        List<String> pic = new ArrayList<>();
        String pom;
        for (Faculty faculty : faculties) {
            if (faculty.getFile() != null) {
                pom = Base64.getEncoder().encodeToString(faculty.getFile().getPic());
                pom = "data:image/jpeg;base64," + pom;
            } else pom = "img/logo.jpg";
            pic.add(pom);
        }
        return pic;
    }

    @Override
    public List<Integer> goGiveMeFacultyId(IFacultyService facultyService) {
        List<Faculty> faculties = facultyService.listOfAllFaculties();
        List<Integer> Id = new ArrayList<>();
        for (Faculty faculty : faculties)
            Id.add(faculty.getId());
        return Id;
    }

    @Override
    public BasicInfoDTO goAddBasicInfo(BasicInfoDTO basicInfoDTO, IUserSecurityDataService userSecurityDataService, IPersonService personService) {
        Person person = getCurrentPerson(userSecurityDataService);
        personService.setPersonBasicInfo(basicInfoDTO, person);
        personService.savePerson(person);
        return basicInfoDTO;
    }

    @Override
    public String goAddHobbyInfo(String hobbyInfo, IPersonService personService, IUserSecurityDataService userSecurityDataService) {

        Person person = getCurrentPerson(userSecurityDataService);

        if (!hobbyInfo.equals("")) person.setHobbyField(hobbyInfo);

        personService.savePerson(person);

        return hobbyInfo;
    }

    @Override
    public void goUploadPhoto(MultipartFile file, IFileService fileService, IPersonService personService, IUserSecurityDataService userSecurityDataService) {
        try {
            FileModel fileModel = fileService.saveMultipartFile(file, "personFoto");
            Person person =getCurrentPerson(userSecurityDataService);
            person.setFoto(fileModel);
            personService.savePerson(person);
        } catch (Exception ignored) {
        } //Uwaga na to
    }

    @Override
    public String goGivePhoto(IPersonService personService, IUserSecurityDataService userSecurityDataService) {
        Person person = getCurrentPerson(userSecurityDataService);
        String pom;
        if (person.getFoto() == null)
            pom = "img/logo.jpg";
        else
            pom = "data:image/jpeg;base64," + Base64.getEncoder().encodeToString(person.getFoto().getPic());
        return pom;
    }

    @Override
    public Person getCurrentPerson(IUserSecurityDataService userSecurityDataService) {
        return getPersonByUserSecurityData(userSecurityDataService.getUserSecurityDataByEmail(CurrentUser.getCurrentUserName()));
    }
}
