package com.nokia.teachersupport.person;

import com.nokia.teachersupport.admin.UserDTOForAdminAction;
import com.nokia.teachersupport.faculty.Faculty;
import com.nokia.teachersupport.faculty.IFacultyService;
import com.nokia.teachersupport.fileUpload.FileModel;
import com.nokia.teachersupport.fileUpload.IFileService;
import com.nokia.teachersupport.personSecurity.IUserSecurityDataService;
import com.nokia.teachersupport.personSecurity.UserSecurityData;
import com.nokia.teachersupport.personSecurity.UserSecurityDataServiceImpl;
import com.nokia.teachersupport.roles.IRoleService;
import com.nokia.teachersupport.roles.RoleRepo;
import com.nokia.teachersupport.roles.SecutityRole;
import com.nokia.teachersupport.tools.CurrentUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

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
    public boolean deletePerson(Person person,IUserSecurityDataService userSecurityDataService) {
        Faculty faculty = person.getFacultyField();
if(faculty !=null) {
    faculty.getFacultyAndPersonList().remove(person);
    person.deleteFaculty(faculty);
}
        for (SecutityRole secutityRole : person.getUserSecurityDataField().getMyRoles())
            secutityRole.getSecurityInsAndRoles().remove(person.getUserSecurityDataField());
        person.getUserSecurityDataField().getMyRoles().removeAll(person.getUserSecurityDataField().getMyRoles());
        userSecurityDataService.deleteUserSecurityData(person.getUserSecurityDataField().getId());
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

        if (!basicInfoDTO.getUsos().equals("")) person.setUsosPersonProfileLinkField(basicInfoDTO.getUsos());

        if (!basicInfoDTO.getTwitter().equals("")) person.setTwitterField(basicInfoDTO.getTwitter());

        if (!basicInfoDTO.getFacebook().equals("")) person.setFacebookField(basicInfoDTO.getFacebook());

        if (!basicInfoDTO.getPhone().equals("")) person.setPhoneNumberField(basicInfoDTO.getPhone());


    }

    @Override
    public void deleteAllPersons(IUserSecurityDataService userSecurityDataService) {
        boolean toDelete;
        for (Person person : personRepo.findAll()) {
            toDelete = true;
            for (SecutityRole securityRole : person.getUserSecurityDataField().getMyRoles())
                if (securityRole.getRoleName().equals("ADMIN"))
                    toDelete = false;
            if (toDelete)
                deletePerson(person,userSecurityDataService);
        }
    }

    @Override
    public boolean savePersonsFromFile(InputStream stream, IUserSecurityDataService userSecurityDataService, IFacultyService facultyService, IRoleService roleService) {
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
                if ((userSecurityDataService.getUserSecurityDataByEmail(myEmail) == null) &&
                        (facultyService.findFaculty(myFaculty) != null) &&
                        (roleService.findByRoleName(myRole) != null)) {
                    Person person = new Person();
                    UserSecurityData userSecurityData = new UserSecurityData();
                    Faculty faculty = facultyService.findFaculty(myFaculty);
                    SecutityRole secutityRole = roleService.findByRoleName(myRole);
                    person.setNameField(myName);
                    person.setSurnameField(mySurname);
                    faculty.addPersonToFaculty(person);
                    person.setFacultyField(faculty);
                    userSecurityData.setActive(false);
                    userSecurityData.setEmail(myEmail);
                    userSecurityData.setPassword("NULL");
                    userSecurityData.setMatchingPassword("NULL");
                    userSecurityData.addARole(secutityRole);
                    secutityRole.addUserSecurityDataToRole(userSecurityData);
                    person.setUserSecurityDataField(userSecurityData);
                    personRepo.save(person);
                    userSecurityDataService.saveUserSecurityData(userSecurityData);
                    facultyService.saveFaculty(faculty);
                    roleService.save(secutityRole);
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
    public void addUser(UserDTOForAdminAction userDTOForAdminActionDTO, IUserSecurityDataService userSecurityDataService, IFacultyService facultyService,IRoleService roleService) {
        //jak nie mam takiego e-mail w bazie jeszcze i jak wydzial istnieje i jesli rola istnieje
        if ((userSecurityDataService.getUserSecurityDataByEmail(userDTOForAdminActionDTO.getUserEmailDTOField()) == null) &&
                (facultyService.findFaculty(userDTOForAdminActionDTO.getUserFacultyDTOField()) != null) &&
                (roleService.findByRoleName(userDTOForAdminActionDTO.getUserRoleDTOField()) != null)) {

            Person person = new Person();
            UserSecurityData userSecurityData = new UserSecurityData();
            Faculty faculty = facultyService.findFaculty(userDTOForAdminActionDTO.getUserFacultyDTOField());
            SecutityRole secutityRole = roleService.findByRoleName(userDTOForAdminActionDTO.getUserRoleDTOField());
            //Tak samo jak dla faculty musi byc security rolke

            person.setNameField(userDTOForAdminActionDTO.getUserNameDTOField());
            person.setSurnameField(userDTOForAdminActionDTO.getUserSurnameDTOField());


            faculty.addPersonToFaculty(person);//!
            person.setFacultyField(faculty); //to nie wiem czy potrzebne bo sa zabezpieczenia w obie str

            userSecurityData.setActive(false);
            userSecurityData.setEmail(userDTOForAdminActionDTO.getUserEmailDTOField());
            userSecurityData.setPassword("NULL"); //UWAGA CHYBA NIE DA SIE NA TO ZALOGOWAC
            userSecurityData.setMatchingPassword("NULL");

            userSecurityData.addARole(secutityRole);
            secutityRole.addUserSecurityDataToRole(userSecurityData);

            person.setUserSecurityDataField(userSecurityData);


            personRepo.save(person);
            userSecurityDataService.saveUserSecurityData(userSecurityData);
            facultyService.saveFaculty(faculty);
            roleService.save(secutityRole);
        }
    }

    @Override
    public Faculty goSaveMyFaculty(String facultyName, IPersonService personService, IFacultyService facultyService,IUserSecurityDataService userSecurityDataService) {
        Person person=personService.getPersonByUserSecurityData(userSecurityDataService.getUserSecurityDataByEmail(CurrentUser.getCurrentUserName()));
        person.setFacultyField(facultyService.findFaculty(facultyName));
        Faculty faculty=facultyService.findFaculty(facultyName);
        faculty.addPersonToFaculty(person);
        facultyService.saveFaculty(faculty);
        personService.savePerson(person);
        return faculty;
    }

    @Override
    public List<String> goGiveMeFacultyPhoto(IFacultyService facultyService) {
        List<Faculty>faculties=facultyService.listOfAllFaculties();
        List<String>pic= new ArrayList<>();
        String pom;
        for (Faculty faculty: faculties)
        {
            if(faculty.getFile()!=null) {
                pom = Base64.getEncoder().encodeToString(faculty.getFile().getPic());
                pom = "data:image/jpeg;base64,"+pom;
            }
            else pom = "img/logo.jpg";
            pic.add(pom);
        }
        return pic;
    }

    @Override
    public List<Integer> goGiveMeFacultyId(IFacultyService facultyService) {
        List<Faculty>faculties=facultyService.listOfAllFaculties();
        List<Integer>Id= new ArrayList<>();
        for (Faculty faculty: faculties)
            Id.add(faculty.getId());
        return Id;
    }

    @Override
    public BasicInfoDTO goAddBasicInfo(BasicInfoDTO basicInfoDTO, IUserSecurityDataService userSecurityDataService, IPersonService personService) {
        Person person=personService.getPersonByUserSecurityData(userSecurityDataService.getUserSecurityDataByEmail(CurrentUser.getCurrentUserName()));
        personService.setPersonBasicInfo(basicInfoDTO,person);
        personService.savePerson(person);
        return basicInfoDTO;
    }

    @Override
    public String goAddHobbyInfo(String hobbyInfo, IPersonService personService, IUserSecurityDataService userSecurityDataService) {

        Person person=personService.getPersonByUserSecurityData(userSecurityDataService.getUserSecurityDataByEmail(CurrentUser.getCurrentUserName()));

        if (!hobbyInfo.equals(""))person.setHobbyField(hobbyInfo);

        personService.savePerson(person);

        return hobbyInfo;
    }

    @Override
    public void goUploadPhoto(MultipartFile file, IFileService fileService, IPersonService personService, IUserSecurityDataService userSecurityDataService) {
       try{
           FileModel fileModel=fileService.saveMultipartFile(file, "personFoto");
           Person person=personService.getPersonByUserSecurityData(userSecurityDataService.getUserSecurityDataByEmail(CurrentUser.getCurrentUserName()));
           person.setFoto(fileModel);
           personService.savePerson(person);
       }
       catch (Exception ignored) { } //Uwaga na to
    }

    @Override
    public String goGivePhoto(IPersonService personService, IUserSecurityDataService userSecurityDataService) {
        Person person=personService.getPersonByUserSecurityData(userSecurityDataService.getUserSecurityDataByEmail(CurrentUser.getCurrentUserName()));
        String pom;
        if(person.getFoto()==null)
            pom="img/logo.jpg";
        else
            pom="data:image/jpeg;base64,"+ Base64.getEncoder().encodeToString(person.getFoto().getPic());
        return pom;
    }

    @Override
    public void goUploadCv(MultipartFile file, IFileService fileService, IPersonService personService, IUserSecurityDataService userSecurityDataService) {
        try {
            FileModel fileModel = fileService.saveMultipartFile(file, "personCV");
            Person person = personService.getPersonByUserSecurityData(userSecurityDataService.getUserSecurityDataByEmail(CurrentUser.getCurrentUserName()));
            person.setCV(fileModel);
            personService.savePerson(person);
        }
        catch(Exception ignored) { }
    }
}
