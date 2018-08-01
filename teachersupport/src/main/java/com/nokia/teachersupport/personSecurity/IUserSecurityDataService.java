package com.nokia.teachersupport.personSecurity;

import java.util.List;

public interface IUserSecurityDataService {
    List<UserSecurityData> listOfAllNews();

    UserSecurityData getUserSecurityData(Integer id);
    UserSecurityData saveUserSecurityData(UserSecurityData usd);
    void deleteUserSecurityData(Integer id);
    UserSecurityData getUserSecurityDataByEmail(String email);
}
