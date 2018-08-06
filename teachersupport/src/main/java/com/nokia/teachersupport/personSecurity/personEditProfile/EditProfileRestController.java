package com.nokia.teachersupport.personSecurity.personEditProfile;

import com.nokia.teachersupport.currentUser.CurrentUser;
import com.nokia.teachersupport.person.IPersonService;
import com.nokia.teachersupport.person.Person;
import com.nokia.teachersupport.personSecurity.IUserSecurityDataService;

import com.nokia.teachersupport.personSecurity.UserSecurityData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

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
    @PostMapping("/change/email")
    public String changeEmail(@RequestParam String email, @RequestParam String confirmEmail ){
        if(!email.equals(confirmEmail))
            return "Error "+confirmEmail+" is different than "+email;
        Person person= personService.getPersonByUserSecurityData(userSecurityDataService.getUserSecurityDataByEmail(CurrentUser.getCurrentUserName()));
        UserSecurityData userSecurityData=person.getUserSecurityDataField();
        userSecurityData.setEmail(email);
        person.setUserSecurityDataField(userSecurityData);
        userSecurityDataService.saveUserSecurityData(userSecurityData);
        personService.savePerson(person);
        Collection<SimpleGrantedAuthority> nowAuthorities =(Collection<SimpleGrantedAuthority>)SecurityContextHolder
                .getContext().getAuthentication().getAuthorities();
        UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(email, userSecurityData.getPassword(), nowAuthorities);
        SecurityContextHolder.getContext().setAuthentication(authentication);

       return "success";
    }
    @PostMapping("/change/password")
    public String changePassword(@RequestParam String password, @RequestParam String confirmPassword ){
        if(!password.equals(confirmPassword))
            return "Error "+confirmPassword+" is different than "+password;
        Person person= personService.getPersonByUserSecurityData(userSecurityDataService.getUserSecurityDataByEmail(CurrentUser.getCurrentUserName()));
        UserSecurityData userSecurityData=person.getUserSecurityDataField();
        userSecurityData.setPassword(password);
        person.setUserSecurityDataField(userSecurityData);
        userSecurityDataService.saveUserSecurityData(userSecurityData);
        personService.savePerson(person);
        Collection<SimpleGrantedAuthority> nowAuthorities =(Collection<SimpleGrantedAuthority>)SecurityContextHolder
                .getContext().getAuthentication().getAuthorities();
        UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userSecurityData.getEmail(), password, nowAuthorities);
        SecurityContextHolder.getContext().setAuthentication(authentication);
        return "success";
    }
}
