package com.nokia.teachersupport.admin;


import com.nokia.teachersupport.currentUser.CurrentUser;
import com.nokia.teachersupport.faculty.Faculty;
import com.nokia.teachersupport.person.Person;
import com.nokia.teachersupport.personSecurity.UserSecurityData;
import com.nokia.teachersupport.roles.SecutityRole;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.util.Objects;


@Controller
public class AdminDashBoardControler {

    private IAdminDashboardService adminDashboardService;

    @Autowired
    public AdminDashBoardControler(IAdminDashboardService adminDashboardsSrvice) {
       this.adminDashboardService=adminDashboardsSrvice;
    }
    @PreAuthorize("hasAnyRole('ADMIN')")
    @GetMapping("/teacherSupportAdminDashboard")
    String dash(Model model) {
        model.addAttribute("userDataForAdminAction", new UserDTOForAdminAction()); //ladujemy dane do obiektow DTO
        model.addAttribute("newFaculty",new Faculty());
        model.addAttribute("hAllFaculty",adminDashboardService.listOfAllFaculties());
        model.addAttribute("currentUsers",adminDashboardService.listOfAllPersons());
        model.addAttribute("selectedFaculty",new Faculty()); // to zbiera wydzila do usuniecia
        model.addAttribute("currentUserName",Objects.requireNonNull(CurrentUser.getCurrentUserName()));

        return "teacherSupportAdminDashboard";
    }
    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    @PostMapping("/teacherSupportAdminDashboard/newUserAdminAction")
    String addNewUser(UserDTOForAdminAction userDTOForAdminActionDTO) {
//jak nie mam takiego e-mail w bazie jeszcze i jak wydzial istnieje i jesli rola istnieje
        if ((adminDashboardService.getUserSecurityDataByEmail(userDTOForAdminActionDTO.getUserEmailDTOField()) == null)&&
                (adminDashboardService.getFacultyByName(userDTOForAdminActionDTO.getUserFacultyDTOField()) !=null)&&
                (adminDashboardService.getRoleByName(userDTOForAdminActionDTO.getUserRoleDTOField()) !=null) ) {

            Person person = new Person();
            UserSecurityData userSecurityData = new UserSecurityData();
            Faculty faculty = adminDashboardService.getFacultyByName(userDTOForAdminActionDTO.getUserFacultyDTOField());
            SecutityRole secutityRole=adminDashboardService.getRoleByName(userDTOForAdminActionDTO.getUserRoleDTOField());
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

        return "redirect:/teacherSupportAdminDashboard";
    }
    @PreAuthorize("hasAnyRole('ADMIN')")
    @PostMapping("/teacherSupportAdminDashboard/newFacultyAdminAction")
    String addNewFaculty(Faculty faculty) {
        if (adminDashboardService.getFacultyByName(faculty.getFacultyNameField()) == null) {
            adminDashboardService.saveUserFacultyDataAdminAction(faculty);
        }
        return "redirect:/teacherSupportAdminDashboard";
    }

    @PreAuthorize("hasAnyRole('ADMIN')")
    @PostMapping("/teacherSupportAdminDashboard/deleteFacultyAdminAction")
    String deleteFaculty(Faculty faculty) {
        if (adminDashboardService.getFacultyByName(faculty.getFacultyNameField()) != null) {
        adminDashboardService.deleteFacultyAdminAction(faculty);
        }
        return "redirect:/teacherSupportAdminDashboard";
    }
    @PostMapping("/teacherSupportAdminDashboard/newUserAdminActionFromFile")
    String addNewUserFromFile(@RequestParam("uploadfile") MultipartFile file)  throws IOException {
        if(file.getOriginalFilename().equals(""))
            return "FAIL! \n" +
                    "You did not choose a file.";
        File convFile = new File(file.getOriginalFilename());
        convFile.createNewFile();
        FileOutputStream fos = new FileOutputStream(convFile);
        fos.write(file.getBytes());
        fos.close();
        FileReader fr = new FileReader(convFile);
        BufferedReader bufferedReader = new BufferedReader(fr);

        try {
            String[] parts = bufferedReader.readLine().split(",");
            do {
                    String myName=parts[0];
                    String mySurname=parts[1];
                    String myFaculty=parts[2];
                    String myRole=parts[3];
                    String myEmail=parts[4];
                if ((adminDashboardService.getUserSecurityDataByEmail(myEmail) == null)&&
                        (adminDashboardService.getFacultyByName(myFaculty) !=null)&&
                        (adminDashboardService.getRoleByName(myRole) !=null) ) {
                    Person person = new Person();
                    UserSecurityData userSecurityData = new UserSecurityData();
                    Faculty faculty = adminDashboardService.getFacultyByName(myFaculty);
                    SecutityRole secutityRole=adminDashboardService.getRoleByName(myRole);
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
                parts = bufferedReader.readLine().split(",");
            } while (parts[0] != null);
        } finally {
            bufferedReader.close();
        }


        return "SUCCES";
    }
}