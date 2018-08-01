package com.nokia.teachersupport.admin;

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

import java.util.List;
import java.util.Optional;

@Service
public class AdminDashboardServiceImpl implements IAdminDashboardService {
    //TODO naming...

    private PersonRepo aDSPersonRepoInstance;
    private UserSecurityDataRepo aDSUserSecurityDataRepoInstance;
    private FacultyRepo facultyRepo;
    private RoleRepo roleRepo;
    private TokenRepo tokenRepo;

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
        //TODO invstigate new faculty???
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

}
