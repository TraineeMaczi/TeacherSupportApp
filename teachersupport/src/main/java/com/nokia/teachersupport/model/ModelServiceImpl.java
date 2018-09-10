package com.nokia.teachersupport.model;

import com.nokia.teachersupport.admin.UserDTOForAdminAction;
import com.nokia.teachersupport.publication.IPublicationService;
import com.nokia.teachersupport.publication.Publication;
import com.nokia.teachersupport.studGroup.IStudGroupService;
import com.nokia.teachersupport.studGroup.StudGroup;
import com.nokia.teachersupport.tools.CurrentUser;
import com.nokia.teachersupport.faculty.Faculty;
import com.nokia.teachersupport.faculty.IFacultyService;
import com.nokia.teachersupport.newsP.EditNewsDTO;
import com.nokia.teachersupport.newsP.News;
import com.nokia.teachersupport.person.IPersonService;
import com.nokia.teachersupport.person.Person;
import com.nokia.teachersupport.personSecurity.IUserSecurityDataService;
import com.nokia.teachersupport.publication.EditPublicationDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import javax.servlet.http.HttpSession;
import java.util.Objects;

@Service
public class ModelServiceImpl implements IModelService {

    private IFacultyService facultyService;
    private IPersonService personService;
    private IUserSecurityDataService userSecurityDataService;


    @Autowired
    public ModelServiceImpl(IFacultyService facultyService, IPersonService personService, IUserSecurityDataService userSecurityDataService) {
        this.facultyService = facultyService;
        this.personService = personService;
        this.userSecurityDataService = userSecurityDataService;

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
        Person person = personService.getCurrentPerson(userSecurityDataService);
        model.addAttribute("currentUserName", Objects.requireNonNull(CurrentUser.getCurrentUserName()));
        model.addAttribute("currentUserPerson", person);

    }

    @Override
    public void contactModel(Model model) {
        Person person =  personService.getCurrentPerson(userSecurityDataService);
        model.addAttribute("currentUserName", Objects.requireNonNull(CurrentUser.getCurrentUserName()));
        model.addAttribute("currentUserPerson", person);
        model.addAttribute("meetMeDataList", person.getPersonMeetMeDataList());
    }

    @Override
    public void indexModel(Model model) {
        Person person =  personService.getCurrentPerson(userSecurityDataService);
        model.addAttribute("currentUserPerson", person);
        model.addAttribute("currentUserName", Objects.requireNonNull(CurrentUser.getCurrentUserName()));
        model.addAttribute("facultyList", facultyService.listOfAllFaculties());
    }

    @Override
    public void editProfileModel(Model model) {
        model.addAttribute("currentUserName", Objects.requireNonNull(CurrentUser.getCurrentUserName()));
    }

    @Override
    public void publicationModel(Model model) {
        Person person =  personService.getCurrentPerson(userSecurityDataService);
        model.addAttribute("currentUserName", Objects.requireNonNull(CurrentUser.getCurrentUserName()));
        model.addAttribute("publication", person.getPersonPublicationList());
        model.addAttribute("newPublication", new Publication());
        model.addAttribute("editPubliPostObj", new EditPublicationDTO());
    }

    @Override
    public void homeModel(Model model) {
        Person person =  personService.getCurrentPerson(userSecurityDataService);
        model.addAttribute("logInUser", person);
        model.addAttribute("news", person.getPersonNewsList());
        model.addAttribute("newNews", new News());
        model.addAttribute("currentUserName", Objects.requireNonNull(CurrentUser.getCurrentUserName()));
        model.addAttribute("editNewsPostObj", new EditNewsDTO());
    }

    @Override
    public void studGroupModel(Model model,HttpSession session) {
        Person person= personService.getCurrentPerson(userSecurityDataService);
        model.addAttribute("newStudGroupUserAction", new StudGroup());
        model.addAttribute("currentGroups",person.getPersonStudGroupList());
        model.addAttribute("currentUserName",Objects.requireNonNull(CurrentUser.getCurrentUserName()));
        model.addAttribute("hAllFaculty", facultyService.listOfAllFaculties());
        String groupName=(String)session.getAttribute("currentStudGroupName");
        if(groupName != null && !groupName.equals("")) {
            model.addAttribute("groupRemoteFiles",person.doIHaveAGroupWithName(groupName).getGroupsResourcesList());
        }
    }
}
