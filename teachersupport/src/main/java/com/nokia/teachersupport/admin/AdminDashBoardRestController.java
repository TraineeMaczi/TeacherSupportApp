package com.nokia.teachersupport.admin;

import com.nokia.teachersupport.faculty.Faculty;
import com.nokia.teachersupport.person.Person;
import com.nokia.teachersupport.personSecurity.UserSecurityData;
import com.nokia.teachersupport.roles.SecutityRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

@RestController
public class AdminDashBoardRestController {
    @Autowired
    private IAdminDashboardService adminDashboardService;

    public File multipartToFile(MultipartFile multipart) throws IllegalStateException, IOException {
        File tmpFile = new File(System.getProperty("java.io.tmpdir") + System.getProperty("file.separator") +
                multipart.getOriginalFilename());
        multipart.transferTo(tmpFile);
        return tmpFile;
    }
    //Nie przyjmuje polskich znakow
    @PostMapping("/teacherSupportAdminDashboard/newUserAdminActionFromFile")
    String addNewUsersFromFile(@RequestParam("uploadfile") MultipartFile file) {
        if (file.isEmpty())
            return "FAIL! \n" +
                    "You did not choose a file.";
        try {
            File convFile = multipartToFile(file);
            FileReader fr = new FileReader(convFile);
            BufferedReader bufferedReader = new BufferedReader(fr);
            String newline = bufferedReader.readLine();
            do {
                String[] parts = newline.split(",");
                String myName = parts[0];
                String mySurname = parts[1];
                String myFaculty = parts[2];
                String myRole = parts[3];
                String myEmail = parts[4];
                if( (adminDashboardService.getFacultyByName(myFaculty) == null))
                    return myFaculty;
                if ((adminDashboardService.getUserSecurityDataByEmail(myEmail) == null) &&
                        (adminDashboardService.getFacultyByName(myFaculty) != null) &&
                        (adminDashboardService.getRoleByName(myRole) != null)) {
                    Person person = new Person();
                    UserSecurityData userSecurityData = new UserSecurityData();
                    Faculty faculty = adminDashboardService.getFacultyByName(myFaculty);
                    SecutityRole secutityRole = adminDashboardService.getRoleByName(myRole);
                    person.setNameField(myName);
                    person.setSurnameField(mySurname);
                    faculty.addPersonToFaculty(person);//!
                    person.setFacultyField(faculty); //to nie wiem czy potrzebne bo sa zabezpieczenia w obie str
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

            return "SUCCES";
        } catch (Exception e) {
            return "FAIL! ";
        }
    }
}
