package com.nokia.teachersupport.admin;

import com.nokia.teachersupport.faculty.Faculty;
import com.nokia.teachersupport.person.Person;
import com.nokia.teachersupport.personSecurity.UserSecurityData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class AdminDashBoardControler {

    private IAdminDashboardService adminDashboardService;

    @Autowired
    public AdminDashBoardControler(IAdminDashboardService adminDashboardsSrvice) {
        this.adminDashboardService = adminDashboardService;
    }

    @GetMapping("/teacherSupportAdminDashboard")
    String dash(Model model) {
        model.addAttribute("userDataForAdminAction", new UserDTOForAdminAction()); //ladujemy dane do obiektow DTO
        model.addAttribute("faculty",new Faculty());
        return "teacherSupportAdminDashboard";
    }

    @PostMapping("/teacherSupportAdminDashboard/newUserAdminAction")
    String addNewUser(UserDTOForAdminAction userDTOForAdminActionDTO) {
//jak nie mam takiego e-mail w bazie jeszcze
        if (adminDashboardService.getUserSecurityDataByEmail(userDTOForAdminActionDTO.getUserEmailDTOField()) != null) {
            Person person = new Person();
            UserSecurityData userSecurityData = new UserSecurityData();
            Faculty faculty = adminDashboardService.getFacultyByName(userDTOForAdminActionDTO.getUserNameDTOField());

            person.setNameField(userDTOForAdminActionDTO.getUserNameDTOField());
            person.setSurnameField(userDTOForAdminActionDTO.getUserSurnameDTOField());


            faculty.addPersonToFaculty(person);//!
            person.setFacultyField(faculty); //to nie wiem czy potrzebne bo sa zabezpieczenia w obie str

            userSecurityData.setActive(false);
            userSecurityData.setEmail(userDTOForAdminActionDTO.getUserEmailDTOField());
            userSecurityData.setPassword("NULL"); //UWAGA CHYBA NIE DA SIE NA TO ZALOGOWAC
            userSecurityData.setMatchingPassword("NULL");
            person.setUserSecurityDataField(userSecurityData);


            adminDashboardService.saveUserPersonDataAdminAction(person);
            adminDashboardService.saveUserSecurityDataAdminAction(userSecurityData);

            //Czy ja mam ten faculty zapisywac XD ?
            adminDashboardService.saveUserFacultyDataAdminAction(faculty);
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