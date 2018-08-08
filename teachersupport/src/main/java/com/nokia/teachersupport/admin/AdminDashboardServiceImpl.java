package com.nokia.teachersupport.admin;

import com.nokia.teachersupport.currentUser.CurrentUser;
import com.nokia.teachersupport.faculty.Faculty;
import com.nokia.teachersupport.faculty.FacultyRepo;
import com.nokia.teachersupport.person.Person;
import com.nokia.teachersupport.person.PersonRepo;
import com.nokia.teachersupport.personSecurity.UserSecurityData;
import com.nokia.teachersupport.personSecurity.UserSecurityDataRepo;
import com.nokia.teachersupport.personSecurity.personRegister.RegisterDTO;
import com.nokia.teachersupport.personSecurity.personRegister.verificationToken.TokenRepo;
import com.nokia.teachersupport.personSecurity.personRegister.verificationToken.VerificationToken;
import com.nokia.teachersupport.roles.RoleRepo;
import com.nokia.teachersupport.roles.SecutityRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.Null;
import java.io.BufferedReader;
import java.io.File;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class AdminDashboardServiceImpl implements IAdminDashboardService {

    private PersonRepo aDSPersonRepoInstance;
    private UserSecurityDataRepo aDSUserSecurityDataRepoInstance;
    private FacultyRepo facultyRepo;
    private RoleRepo roleRepo;
    private TokenRepo tokenRepo;
    @Autowired
    private IAdminDashboardService adminDashboardService;

    @Autowired
    public AdminDashboardServiceImpl(TokenRepo tokenRepo) {
        this.tokenRepo = tokenRepo;
    }

    @Autowired
    public void setFacultyRepo(FacultyRepo facultyRepo) {
        this.facultyRepo = facultyRepo;
    }

    @Autowired
    public void setaDSUserSecurityDataRepoInstance(UserSecurityDataRepo userSecurityDataRepoInstance) {
        this.aDSUserSecurityDataRepoInstance = userSecurityDataRepoInstance;
    }

    @Autowired
    public void setaDSPersonRepoInstance(PersonRepo personRepo) {
        this.aDSPersonRepoInstance = personRepo;
    }


    @Autowired
    public void setRoleRepo(RoleRepo roleRepo) {
        this.roleRepo = roleRepo;
    }

    @Override
    public void deleteUserPersonDataAdminAction(Integer userID) {
        aDSPersonRepoInstance.deleteById(userID);
    }

    @Override
    public void deleteUserSecurityDataAdminAction(Integer userID) {
        aDSUserSecurityDataRepoInstance.deleteById(userID);
    }

    @Override
    public void deleteUserFacultyDataAdminAction(Integer facultyID) {
        facultyRepo.deleteById(facultyID);
    }


    @Override
    public void deleteUserRoleDataAdminAction(Integer roleID) {
        roleRepo.deleteById(roleID);
    }

    @Override
    public SecutityRole saveUserRoleDataAdminAction(SecutityRole secutityRole) {
        return roleRepo.save(secutityRole);
    }


    @Override
    public Person saveUserPersonDataAdminAction(Person person) {
        return aDSPersonRepoInstance.save(person);
    }

    @Override
    public UserSecurityData saveUserSecurityDataAdminAction(UserSecurityData usd) {
        return aDSUserSecurityDataRepoInstance.save(usd);
    }

    @Override
    public Faculty saveUserFacultyDataAdminAction(Faculty faculty) {
        return facultyRepo.save(faculty);
    }

    @Override
    public Faculty getFacultyData(Faculty faculty) {
        Optional<Faculty> facultyOpt = facultyRepo.findById(faculty.getId());
        Faculty tmp = facultyOpt.orElse(new Faculty());
        return tmp;
    }

    @Override
    public Faculty getFacultyByName(String facultyName) {
        Faculty facultyOpt = facultyRepo.findByFacultyNameField(facultyName);
        return facultyOpt;
    }

    @Override
    public UserSecurityData getUserSecurityDataByEmail(String email) {
        UserSecurityData usd = aDSUserSecurityDataRepoInstance.findByEmail(email);
        return usd;
    }

    @Override
    public List<Faculty> listOfAllFaculties() {
        return facultyRepo.findAll();
    }

    @Override
    public SecutityRole getRoleByName(String rName) {
        SecutityRole secutityRole = roleRepo.findByRoleName(rName);
        return secutityRole;
    }

    @Override
    public List<Person> listOfAllPersons() {
        return aDSPersonRepoInstance.findAll();
    }

    @Override
    public UserSecurityData registerNewUserAction(RegisterDTO registerDTO) {
        UserSecurityData userSecurityData = aDSUserSecurityDataRepoInstance.findByEmail(registerDTO.getUserName_Email());
        userSecurityData.setActive(true);
        userSecurityData.setPassword(registerDTO.getUserPass());
        userSecurityData.setMatchingPassword(registerDTO.getUserConfirmPass());
        return userSecurityData;
    }

    @Override
    public void createVerificationToken(UserSecurityData user, String token) {
        VerificationToken myToken = new VerificationToken(token, user);
        tokenRepo.save(myToken);
    }

    @Override
    public VerificationToken getVerificationToken(String VerificationToken) {
        return tokenRepo.findByToken(VerificationToken);
    }

    @Override
    public void deleteFacultyAdminAction(Faculty faculty) {
        facultyRepo.delete(faculty);
    }


    public RoleRepo getRoleRepo() {
        return roleRepo;
    }

    @Override
    public boolean saveUsersFromFile(InputStream stream) {
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
                if ((adminDashboardService.getUserSecurityDataByEmail(myEmail) == null) &&
                        (adminDashboardService.getFacultyByName(myFaculty) != null) &&
                        (adminDashboardService.getRoleByName(myRole) != null)) {
                    Person person = new Person();
                    UserSecurityData userSecurityData = new UserSecurityData();
                    Faculty faculty = adminDashboardService.getFacultyByName(myFaculty);
                    SecutityRole secutityRole = adminDashboardService.getRoleByName(myRole);
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
                    adminDashboardService.saveUserPersonDataAdminAction(person);
                    adminDashboardService.saveUserSecurityDataAdminAction(userSecurityData);
                    adminDashboardService.saveUserFacultyDataAdminAction(faculty);
                    adminDashboardService.saveUserRoleDataAdminAction(secutityRole);
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
    public void dashModel(Model model) {
        model.addAttribute("userDataForAdminAction", new UserDTOForAdminAction()); //ladujemy dane do obiektow DTO
        model.addAttribute("newFaculty", new Faculty());
        model.addAttribute("hAllFaculty", adminDashboardService.listOfAllFaculties());
        model.addAttribute("currentUsers", adminDashboardService.listOfAllPersons());
        model.addAttribute("selectedFaculty", new Faculty()); // to zbiera wydzila do usuniecia
        model.addAttribute("currentUserName", Objects.requireNonNull(CurrentUser.getCurrentUserName()));
    }

    @Override
    public void addUser(UserDTOForAdminAction userDTOForAdminActionDTO) {
        //jak nie mam takiego e-mail w bazie jeszcze i jak wydzial istnieje i jesli rola istnieje
        if ((adminDashboardService.getUserSecurityDataByEmail(userDTOForAdminActionDTO.getUserEmailDTOField()) == null) &&
                (adminDashboardService.getFacultyByName(userDTOForAdminActionDTO.getUserFacultyDTOField()) != null) &&
                (adminDashboardService.getRoleByName(userDTOForAdminActionDTO.getUserRoleDTOField()) != null)) {

            Person person = new Person();
            UserSecurityData userSecurityData = new UserSecurityData();
            Faculty faculty = adminDashboardService.getFacultyByName(userDTOForAdminActionDTO.getUserFacultyDTOField());
            SecutityRole secutityRole = adminDashboardService.getRoleByName(userDTOForAdminActionDTO.getUserRoleDTOField());
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


            adminDashboardService.saveUserPersonDataAdminAction(person);
            adminDashboardService.saveUserSecurityDataAdminAction(userSecurityData);

            //Czy ja mam ten faculty i role  zapisywac XD ?
            adminDashboardService.saveUserFacultyDataAdminAction(faculty);
            adminDashboardService.saveUserRoleDataAdminAction(secutityRole);
        }
    }

}
