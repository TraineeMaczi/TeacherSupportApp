package com.nokia.teachersupport.admin;

import com.nokia.teachersupport.faculty.Faculty;
import com.nokia.teachersupport.faculty.FacultyRepo;
import com.nokia.teachersupport.person.Person;
import com.nokia.teachersupport.person.PersonRepo;
import com.nokia.teachersupport.personSecurity.UserSecurityData;
import com.nokia.teachersupport.personSecurity.UserSecurityDataRepo;
import org.springframework.stereotype.Service;

import java.util.Optional;
@Service
public class AdminDashboardServiceImpl implements IAdminDashboardService {

    private PersonRepo aDSPersonRepoInstance;
    private UserSecurityDataRepo aDSUserSecurityDataRepoInstance;
    private FacultyRepo facultyRepo;

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
        UserSecurityData usd=aDSUserSecurityDataRepoInstance.findByEmail(email);
        return usd;
    }


}
