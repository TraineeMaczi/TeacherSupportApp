package com.nokia.teachersupport.admin;

import com.nokia.teachersupport.faculty.Faculty;
import com.nokia.teachersupport.person.Person;
import com.nokia.teachersupport.personSecurity.UserSecurityData;
import com.nokia.teachersupport.personSecurity.personRegister.RegisterDTO;
import com.nokia.teachersupport.personSecurity.personRegister.verificationToken.VerificationToken;
import com.nokia.teachersupport.roles.SecutityRole;


import java.util.List;

public interface IAdminDashboardService {
    void deleteUserPersonDataAdminAction(Integer userID);
    void deleteUserSecurityDataAdminAction(Integer userID);
    void deleteUserFacultyDataAdminAction(Integer facultyID);
    void deleteUserRoleDataAdminAction(Integer roleID);
    SecutityRole saveUserRoleDataAdminAction(SecutityRole secutityRole);
    Person saveUserPersonDataAdminAction(Person person);
    UserSecurityData saveUserSecurityDataAdminAction(UserSecurityData usd);
    Faculty saveUserFacultyDataAdminAction(Faculty faculty);
    Faculty getFacultyData(Faculty faculty);
    Faculty getFacultyByName(String facultyName);
    UserSecurityData getUserSecurityDataByEmail(String email);
    List<Faculty> listOfAllFaculties();
    SecutityRole getRoleByName(String rName);
    List<Person> listOfAllPersons();
    UserSecurityData registerNewUserAction(RegisterDTO registerDTO);
    void createVerificationToken(UserSecurityData user, String token);
    VerificationToken getVerificationToken(String VerificationToken);
    void deleteFacultyAdminAction(Faculty faculty);
}
