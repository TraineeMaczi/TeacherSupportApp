package com.nokia.teachersupport.context;

import com.nokia.teachersupport.person.IPersonService;
import com.nokia.teachersupport.person.Person;
import com.nokia.teachersupport.personSecurity.IUserSecurityDataService;
import com.nokia.teachersupport.tools.CurrentUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.thymeleaf.context.Context;

import java.util.Base64;
@Service
public class ContextServiceImpl implements IContextService {
    private IPersonService personService;
    private IUserSecurityDataService userSecurityDataService;

    @Autowired
    public ContextServiceImpl(IPersonService personService, IUserSecurityDataService userSecurityDataService) {
        this.personService = personService;
        this.userSecurityDataService = userSecurityDataService;
    }

    @Override
    public void homeContext(Context context) {
        context.clearVariables();
        Person person = personService.getPersonByUserSecurityData(userSecurityDataService.getUserSecurityDataByEmail(CurrentUser.getCurrentUserName()));
        String pictureCode;
        context.setVariable("news", person.getPersonNewsList());
        pictureCode = Base64.getEncoder().encodeToString(person.getFoto().getPic());
        pictureCode = "data:image/jpeg;base64,"+pictureCode;
        context.setVariable("photo",pictureCode);
        pictureCode = Base64.getEncoder().encodeToString(person.getFacultyField().getFile().getPic());
        pictureCode = "data:image/jpeg;base64,"+pictureCode;
        context.setVariable("facultyPhoto",pictureCode);
        context.setVariable("name", person.getNameField());
        context.setVariable("surname", person.getSurnameField());
    }

    @Override
    public void aboutMeContext(Context context) {
        context.clearVariables();
        Person person = personService.getPersonByUserSecurityData(userSecurityDataService.getUserSecurityDataByEmail(CurrentUser.getCurrentUserName()));
        String pictureCode;
        pictureCode = Base64.getEncoder().encodeToString(person.getFoto().getPic());
        pictureCode = "data:image/jpeg;base64,"+pictureCode;
        context.setVariable("photo",pictureCode);
        pictureCode = Base64.getEncoder().encodeToString(person.getFacultyField().getFile().getPic());
        pictureCode = "data:image/jpeg;base64,"+pictureCode;
        context.setVariable("facultyPhoto",pictureCode);
        context.setVariable("name", person.getNameField());
        context.setVariable("surname", person.getSurnameField());
        context.setVariable("currentUserPerson", person);
    }

    @Override
    public void publicationsContext(Context context) {
        context.clearVariables();
        Person person = personService.getPersonByUserSecurityData(userSecurityDataService.getUserSecurityDataByEmail(CurrentUser.getCurrentUserName()));
        String pictureCode;
        context.setVariable("publications", person.getPersonPublicationsList());
        pictureCode = Base64.getEncoder().encodeToString(person.getFoto().getPic());
        pictureCode = "data:image/jpeg;base64,"+pictureCode;
        context.setVariable("photo",pictureCode);
        pictureCode = Base64.getEncoder().encodeToString(person.getFacultyField().getFile().getPic());
        pictureCode = "data:image/jpeg;base64,"+pictureCode;
        context.setVariable("facultyPhoto",pictureCode);
        context.setVariable("name", person.getNameField());
        context.setVariable("surname", person.getSurnameField());
    }

    @Override
    public void studentContext(Context context) {

    }

    @Override
    public void contactContext(Context context) {
        context.clearVariables();
        Person person = personService.getPersonByUserSecurityData(userSecurityDataService.getUserSecurityDataByEmail(CurrentUser.getCurrentUserName()));
        String pictureCode;
        context.setVariable("meetMeDataList", person.getPersonMeetMeDataList());
        pictureCode = Base64.getEncoder().encodeToString(person.getFoto().getPic());
        pictureCode = "data:image/jpeg;base64,"+pictureCode;
        context.setVariable("photo",pictureCode);
        pictureCode = Base64.getEncoder().encodeToString(person.getFacultyField().getFile().getPic());
        pictureCode = "data:image/jpeg;base64,"+pictureCode;
        context.setVariable("facultyPhoto",pictureCode);
        context.setVariable("name", person.getNameField());
        context.setVariable("surname", person.getSurnameField());
    }
}
