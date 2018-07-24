package com.nokia.teachersupport.personSecurity;


public interface IUserService {
    UserSecurityData registerNewUserAccount(UserSecurityData accountDto)
            throws EmailExistsException;




}
