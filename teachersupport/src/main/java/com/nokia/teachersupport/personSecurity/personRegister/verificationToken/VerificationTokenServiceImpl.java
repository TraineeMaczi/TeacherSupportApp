package com.nokia.teachersupport.personSecurity.personRegister.verificationToken;

import com.nokia.teachersupport.personSecurity.UserSecurityData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class VerificationTokenServiceImpl implements IVerificationTokenService {
    @Autowired
    TokenRepo tokenRepo;
    @Override
    public VerificationToken getVerificationToken(String VerificationToken) {
        return tokenRepo.findByToken(VerificationToken);
    }

    @Override
    public void createVerificationToken(UserSecurityData user, String token,String password) {
        VerificationToken myToken = new VerificationToken(token, user,password);
        tokenRepo.save(myToken);
    }
}
