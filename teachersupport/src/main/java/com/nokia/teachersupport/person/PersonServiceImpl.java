package com.nokia.teachersupport.person;

import com.nokia.teachersupport.admin.UserDTOForAdminAction;
import com.nokia.teachersupport.faculty.Faculty;
import com.nokia.teachersupport.faculty.IFacultyService;
import com.nokia.teachersupport.personSecurity.IUserSecurityDataService;
import com.nokia.teachersupport.personSecurity.UserSecurityData;
import com.nokia.teachersupport.personSecurity.UserSecurityDataServiceImpl;
import com.nokia.teachersupport.roles.IRoleService;
import com.nokia.teachersupport.roles.RoleRepo;
import com.nokia.teachersupport.roles.SecutityRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Optional;

@Service
public class PersonServiceImpl implements IPersonService {

    private PersonRepo personRepo;
    private IUserSecurityDataService userSecurityDataService;
    private IFacultyService facultyService;
    private IRoleService roleService;

    @Autowired
    public PersonServiceImpl(PersonRepo personRepo, IUserSecurityDataService userSecurityDataService, IFacultyService facultyService, IRoleService roleService) {
        this.personRepo = personRepo;
        this.userSecurityDataService = userSecurityDataService;
        this.facultyService = facultyService;
        this.roleService = roleService;
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
    public void deletePerson(Person person) {
        Faculty faculty = person.getFacultyField();
        faculty.getFacultyAndPersonList().remove(person);
        person.deleteFaculty(faculty);
        for (SecutityRole secutityRole : person.getUserSecurityDataField().getMyRoles())
            secutityRole.getSecurityInsAndRoles().remove(person.getUserSecurityDataField());
        person.getUserSecurityDataField().getMyRoles().removeAll(person.getUserSecurityDataField().getMyRoles());
        userSecurityDataService.deleteUserSecurityData(person.getUserSecurityDataField().getId());
        personRepo.delete(person);
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
    public void deleteAllPersons() {
        boolean toDelete;
        for (Person person : personRepo.findAll()) {
            toDelete = true;
            for (SecutityRole securityRole : person.getUserSecurityDataField().getMyRoles())
                if (securityRole.getRoleName().equals("ADMIN"))
                    toDelete = false;
            if (toDelete)
                deletePerson(person);
        }
    }

    @Override
    public boolean savePersonsFromFile(InputStream stream) {
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
    public void addUser(UserDTOForAdminAction userDTOForAdminActionDTO) {
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
}
