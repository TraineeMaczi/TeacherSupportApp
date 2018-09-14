package com.nokia.teachersupport.personSecurity;

import com.nokia.teachersupport.roles.SecurityRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserSecurityDataServiceImpl implements IUserSecurityDataService {
    private UserSecurityDataRepo userSecurityDataRepo;

    @Autowired
    public UserSecurityDataServiceImpl(UserSecurityDataRepo userSecurityDataRepo) {
        this.userSecurityDataRepo = userSecurityDataRepo;
    }

    @Override
    public UserSecurityData saveUserSecurityData(UserSecurityData usd) {
        return userSecurityDataRepo.save(usd);
    }

    @Override
    public void deleteUserSecurityData(Integer id) {
        userSecurityDataRepo.deleteById(id);
    }

    @Override
    public UserSecurityData getUserSecurityDataByEmail(String email) {
        return userSecurityDataRepo.findByEmail(email);
    }

    @Override
    public boolean isAdmin(UserSecurityData user) {
        for (SecurityRole role : user.getMyRoles()) {
            if (role.getRoleName().equals("ADMIN")) {
                return true;
            }
        }
        return false;
    }


}
