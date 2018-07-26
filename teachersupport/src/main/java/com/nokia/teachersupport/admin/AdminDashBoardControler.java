package com.nokia.teachersupport.admin;


import com.nokia.teachersupport.faculty.Faculty;
import com.nokia.teachersupport.person.Person;
import com.nokia.teachersupport.personSecurity.UserSecurityData;
import com.nokia.teachersupport.roles.SecutityRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
public class AdminDashBoardControler {

    private IAdminDashboardService adminDashboardService;

    @Autowired
    public AdminDashBoardControler(IAdminDashboardService adminDashboardsSrvice) {
       this.adminDashboardService=adminDashboardsSrvice;
    }

    @GetMapping("/teacherSupportAdminDashboard")
    String dash(Model model) {
        model.addAttribute("userDataForAdminAction", new UserDTOForAdminAction()); //ladujemy dane do obiektow DTO
        model.addAttribute("newFaculty",new Faculty());
        model.addAttribute("hAllFaculty",adminDashboardService.listOfAllFaculties());
        model.addAttribute("currentUsers",adminDashboardService.listOfAllPersons());

        return "teacherSupportAdminDashboard";
    }

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

    @PostMapping("/teacherSupportAdminDashboard/newFacultyAdminAction")
    String addNewFaculty(Faculty faculty) {
        if (adminDashboardService.getFacultyByName(faculty.getFacultyNameField()) == null) {
            adminDashboardService.saveUserFacultyDataAdminAction(faculty);
        }
        return "redirect:/teacherSupportAdminDashboard";
    }


}