package com.nokia.teachersupport.admin;

import com.nokia.teachersupport.faculty.Faculty;
import com.nokia.teachersupport.person.Person;
import com.nokia.teachersupport.personSecurity.UserSecurityData;

public interface IAdminDashboardService {
    void deleteUserPersonDataAdminAction(Integer userID);
    void deleteUserSecurityDataAdminAction(Integer userID);
    void deleteUserFacultyDataAdminAction(Integer facultyID);
    Person saveUserPersonDataAdminAction(Person person);
    UserSecurityData saveUserSecurityDataAdminAction(UserSecurityData usd);
    Faculty saveUserFacultyDataAdminAction(Faculty faculty);
    Faculty getFacultyData(Faculty faculty);
    Faculty getFacultyByName(String facultyName);
    UserSecurityData getUserSecurityDataByEmail(String email);
}
