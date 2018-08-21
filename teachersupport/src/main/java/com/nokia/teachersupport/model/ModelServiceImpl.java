package com.nokia.teachersupport.model;

import com.nokia.teachersupport.admin.UserDTOForAdminAction;
import com.nokia.teachersupport.currentUser.CurrentUser;
import com.nokia.teachersupport.faculty.Faculty;
import com.nokia.teachersupport.faculty.IFacultyService;
import com.nokia.teachersupport.person.IPersonService;
import com.nokia.teachersupport.person.Person;
import com.nokia.teachersupport.personSecurity.IUserSecurityDataService;
import com.nokia.teachersupport.publications.EditPublicationDTO;
import com.nokia.teachersupport.publications.IPublicationsService;
import com.nokia.teachersupport.publications.Publications;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import java.util.Objects;

@Service
public class ModelServiceImpl implements IModelService {

    private IFacultyService facultyService;
    private IPersonService personService;
    private IUserSecurityDataService userSecurityDataService;
    private IPublicationsService publicationsService;

    @Autowired
    public ModelServiceImpl(IFacultyService facultyService, IPersonService personService, IUserSecurityDataService userSecurityDataService, IPublicationsService publicationsService) {
        this.facultyService = facultyService;
        this.personService = personService;
        this.userSecurityDataService = userSecurityDataService;
        this.publicationsService = publicationsService;
    }


    @Override
    public void adminDashboardModel(Model model) {
        model.addAttribute("userDataForAdminAction", new UserDTOForAdminAction()); //ladujemy dane do obiektow DTO
        model.addAttribute("newFaculty", new Faculty());
        model.addAttribute("hAllFaculty", facultyService.listOfAllFaculties());
        model.addAttribute("currentUsers", personService.listOfAllPersons());
        model.addAttribute("currentUserName", Objects.requireNonNull(CurrentUser.getCurrentUserName()));
    }

    @Override
    public void generateModel(Model model) {
        model.addAttribute("currentUserName", Objects.requireNonNull(CurrentUser.getCurrentUserName()));
    }

    @Override
    public void aboutMeModel(Model model) {
        Person person = personService.getPersonByUserSecurityData(userSecurityDataService.getUserSecurityDataByEmail(CurrentUser.getCurrentUserName()));
        model.addAttribute("currentUserName", Objects.requireNonNull(CurrentUser.getCurrentUserName()));
        model.addAttribute("currentUserPerson", person);

    }

    @Override
    public void contactModel(Model model) {
        Person person = personService.getPersonByUserSecurityData(userSecurityDataService.getUserSecurityDataByEmail(CurrentUser.getCurrentUserName()));
        model.addAttribute("currentUserName", Objects.requireNonNull(CurrentUser.getCurrentUserName()));
        model.addAttribute("currentUserPerson", person);
        model.addAttribute("meetMeDataList", person.getPersonMeetMeDataList());
    }

    @Override
    public void indexModel(Model model) {
        Person person = personService.getPersonByUserSecurityData(userSecurityDataService.getUserSecurityDataByEmail(CurrentUser.getCurrentUserName()));
        model.addAttribute("currentUserPerson", person);
        model.addAttribute("currentUserName", Objects.requireNonNull(CurrentUser.getCurrentUserName()));
        model.addAttribute("facultyList", facultyService.listOfAllFaculties());
    }

    @Override
    public void editProfileModel(Model model) {
        model.addAttribute("currentUserName", Objects.requireNonNull(CurrentUser.getCurrentUserName()));
    }

    @Override
    public void publicationsModel(Model model) {
        model.addAttribute("currentUserName", Objects.requireNonNull(CurrentUser.getCurrentUserName()));
        model.addAttribute("publications", publicationsService.listOfAllPublications());
        model.addAttribute("newPublication", new Publications());
        model.addAttribute("editPubliPostObj", new EditPublicationDTO());
    }
}
