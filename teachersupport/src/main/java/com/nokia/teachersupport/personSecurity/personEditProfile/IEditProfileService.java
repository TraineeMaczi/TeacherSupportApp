package com.nokia.teachersupport.personSecurity.personEditProfile;

import com.nokia.teachersupport.serviceProvider.IServiceProvider;

public interface IEditProfileService {
    boolean saveNameChange(String name, String surname, IServiceProvider serviceProvider);
    boolean savePasswordChange(String password, String confirmPassword,IServiceProvider serviceProvider);
    boolean saveEmailChange(String email, String confirmEmail,IServiceProvider serviceProvider);


}
