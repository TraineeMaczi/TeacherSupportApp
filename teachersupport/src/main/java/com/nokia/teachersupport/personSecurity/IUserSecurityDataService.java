package com.nokia.teachersupport.personSecurity;

public interface IUserSecurityDataService {
    UserSecurityData saveUserSecurityData(UserSecurityData usd);
    void deleteUserSecurityData(Integer id);
    UserSecurityData getUserSecurityDataByEmail(String email);
    boolean isAdmin(UserSecurityData user);
}
