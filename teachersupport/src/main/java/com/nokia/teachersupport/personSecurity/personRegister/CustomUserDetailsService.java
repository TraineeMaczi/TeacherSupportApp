package com.nokia.teachersupport.personSecurity.personRegister;

import com.nokia.teachersupport.person.Person;
import com.nokia.teachersupport.person.PersonRepo;
import com.nokia.teachersupport.personSecurity.UserSecurityData;
import com.nokia.teachersupport.personSecurity.UserSecurityDataRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class CustomUserDetailsService implements UserDetailsService {

    private UserSecurityDataRepo userSecurityDataRepo;

    @Autowired
    public CustomUserDetailsService(UserSecurityDataRepo userSecurityDataRepo) {
        this.userSecurityDataRepo= userSecurityDataRepo;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserSecurityData user=userSecurityDataRepo.findByEmail(username);
    if(user !=null)
    {
    return new CustomUserDetails(user);
    }
    else
    {
       throw new UsernameNotFoundException("Username not found");
    }

    }
}
