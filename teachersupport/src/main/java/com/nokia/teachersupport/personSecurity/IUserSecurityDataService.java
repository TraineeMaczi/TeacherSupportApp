package com.nokia.teachersupport.personSecurity;


public interface IUserSecurityDataService {
    UserSecurityData registerNewUserAccount(UserSecurityData accountDto)
            throws EmailExistsException;

}
