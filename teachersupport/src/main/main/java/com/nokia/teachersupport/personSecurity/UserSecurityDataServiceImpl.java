package com.nokia.teachersupport.personSecurity;

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
    public List<UserSecurityData> listOfAllNews() {
        return userSecurityDataRepo.findAll();
    }

    @Override
    public UserSecurityData getUserSecurityData(Integer id) {
        Optional<UserSecurityData> opt = userSecurityDataRepo.findById(id);
        UserSecurityData z = opt.orElse(new UserSecurityData());
        return z;
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
}
