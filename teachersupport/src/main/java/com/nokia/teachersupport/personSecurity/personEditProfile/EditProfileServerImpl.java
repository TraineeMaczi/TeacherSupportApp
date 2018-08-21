package com.nokia.teachersupport.personSecurity.personEditProfile;

import com.nokia.teachersupport.currentUser.CurrentUser;
import com.nokia.teachersupport.person.IPersonService;
import com.nokia.teachersupport.person.Person;
import com.nokia.teachersupport.personSecurity.IUserSecurityDataService;
import com.nokia.teachersupport.personSecurity.UserSecurityData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Collection;
@Service
public class EditProfileServerImpl implements IEditProfileService{
    private IPersonService personService;
    private IUserSecurityDataService userSecurityDataService;

    @Autowired
    public EditProfileServerImpl(IPersonService personService, IUserSecurityDataService userSecurityDataService) {
        this.personService = personService;
        this.userSecurityDataService = userSecurityDataService;
    }


    @Override
    public boolean saveNameChange(String name, String surname) {
        Person person= personService.getPersonByUserSecurityData(userSecurityDataService.getUserSecurityDataByEmail(CurrentUser.getCurrentUserName()));
        person.setNameField(name);
        person.setSurnameField(surname);
        personService.savePerson(person);
        return true;
    }

    @Override
    public boolean savePasswordChange(String password, String confirmPassword) {
        if(!password.equals(confirmPassword))
            return false;
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
        return true;
    }

    @Override
    public boolean saveEmailChange(String email, String confirmEmail) {
        if(!email.equals(confirmEmail))
            return false;
        Person person= personService.getPersonByUserSecurityData(userSecurityDataService.getUserSecurityDataByEmail(CurrentUser.getCurrentUserName()));
        UserSecurityData userSecurityData=person.getUserSecurityDataField();
        userSecurityData.setEmail(email);
        person.setUserSecurityDataField(userSecurityData);
        userSecurityDataService.saveUserSecurityData(userSecurityData);
        personService.savePerson(person);
        Collection<SimpleGrantedAuthority> nowAuthorities =(Collection<SimpleGrantedAuthority>) SecurityContextHolder
                .getContext().getAuthentication().getAuthorities();
        UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(email, userSecurityData.getPassword(), nowAuthorities);
        SecurityContextHolder.getContext().setAuthentication(authentication);
        return true;
    }
}
