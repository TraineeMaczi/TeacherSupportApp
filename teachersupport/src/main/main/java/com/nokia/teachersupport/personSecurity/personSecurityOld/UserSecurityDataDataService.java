//package com.nokia.teachersupport.personSecurity.personSecurityOld;
//
//
//import com.nokia.teachersupport.personSecurity.UserSecurityData;
//import com.nokia.teachersupport.personSecurity.UserSecurityDataRepo;
//import com.nokia.teachersupport.personSecurity.personSecurityOld.EmailExistsException;
//import com.nokia.teachersupport.personSecurity.personSecurityOld.IUserSecurityDataService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import javax.transaction.Transactional;
//
//@Service
//public class UserSecurityDataDataService implements IUserSecurityDataService {
//    @Autowired
//    private UserSecurityDataRepo repository;
//
//
//
//    @Transactional
//    @Override
//    public UserSecurityData registerNewUserAccount(UserSecurityData userSecurityData)
//            throws EmailExistsException {
//
//        if (emailExist(userSecurityData.getEmail(), userSecurityData.getActive())) {
//            throw new EmailExistsException("There is an account with that email address:"  + userSecurityData.getEmail());
//        }
//        UserSecurityData user = new UserSecurityData();
//
//        user.setPassword(userSecurityData.getPassword());
//        user.setEmail(userSecurityData.getEmail());
//        user.setActive(true);
//        return repository.save(user);
//    }
//    private boolean emailExist(String email, Boolean active) {
//        UserSecurityData user = repository.findByEmail(email);
//        return (user != null) && (active);
//
//    }
//
//
//}
