package com.nokia.teachersupport.personSecurity.personEditProfile;

public interface IEditProfileService {
    boolean saveNameChange(String name, String surname);
    boolean savePasswordChange(String password, String confirmPassword);
    boolean saveEmailChange(String email, String confirmEmail);


}
