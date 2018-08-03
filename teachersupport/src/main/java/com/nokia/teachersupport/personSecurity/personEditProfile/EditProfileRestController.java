package com.nokia.teachersupport.personSecurity.personEditProfile;

import com.nokia.teachersupport.currentUser.CurrentUser;
import com.nokia.teachersupport.person.IPersonService;
import com.nokia.teachersupport.person.Person;
import com.nokia.teachersupport.personSecurity.IUserSecurityDataService;

import com.nokia.teachersupport.personSecurity.UserSecurityData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EditProfileRestController {
    @Autowired
    private IPersonService personService;
    @Autowired
    private IUserSecurityDataService userSecurityDataService;
    @PostMapping("/change/name/{name}/{surname}")
    public void changeName(@PathVariable String name, @PathVariable String surname){
        Person person= personService.getPersonByUserSecurityData(userSecurityDataService.getUserSecurityDataByEmail(CurrentUser.getCurrentUserName()));
        person.setNameField(name);
        person.setSurnameField(surname);
        personService.savePerson(person);
    }
    @PostMapping("/change/email/{email}/{confirm}")
    public String changeEmail(@PathVariable String email, @PathVariable String confirm){
        if(!email.equals(confirm)){
            return "FAIL!";
        }
        Person person= personService.getPersonByUserSecurityData(userSecurityDataService.getUserSecurityDataByEmail(CurrentUser.getCurrentUserName()));
        UserSecurityData userSecurityData=person.getUserSecurityDataField();
        userSecurityData.setEmail(email);
        person.setUserSecurityDataField(userSecurityData);
        userSecurityDataService.saveUserSecurityData(userSecurityData);
        personService.savePerson(person);
        return "SUCCES";
    }
}
