package com.nokia.teachersupport.personSecurity;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Arrays;

@Service
public class UserService implements IUserService {
    @Autowired
    private UserRepository repository;

    @Transactional
    @Override
    public UserSecurityData registerNewUserAccount(UserSecurityData userSecurityData)
            throws EmailExistsException {

        if (emailExist(userSecurityData.getEmail(),userSecurityData.getActive())) {
            throw new EmailExistsException("There is an account with that email address:"  + userSecurityData.getEmail());
        }
        UserSecurityData user = new UserSecurityData();

        user.setPassword(userSecurityData.getPassword());
        user.setEmail(userSecurityData.getEmail());
        user.addARole("ROLE_USER");
        //user.setMyRoles(Arrays.asList("ROLE_USER")); Zmienilam bo mam obiekty typu rola
        return repository.save(user);
    }
    private boolean emailExist(String email,Boolean active) {
        UserSecurityData user = repository.findByEmail(email);
        if ((user != null)&&(active==true)) {
            return true;
        }
        return false;
    }
}
