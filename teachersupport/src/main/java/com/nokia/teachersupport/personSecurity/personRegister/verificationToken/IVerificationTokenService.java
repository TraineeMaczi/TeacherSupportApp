package com.nokia.teachersupport.personSecurity.personRegister.verificationToken;

import com.nokia.teachersupport.personSecurity.UserSecurityData;

public interface IVerificationTokenService {
    VerificationToken getVerificationToken(String VerificationToken);
    void createVerificationToken(UserSecurityData user, String token,String pass);
}
