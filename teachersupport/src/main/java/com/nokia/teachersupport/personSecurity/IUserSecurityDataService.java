package com.nokia.teachersupport.personSecurity;

import com.nokia.teachersupport.faculty.IFacultyService;
import com.nokia.teachersupport.fileUpload.IFileService;
import com.nokia.teachersupport.person.IPersonService;
import com.nokia.teachersupport.roles.IRoleService;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface IUserSecurityDataService {
    List<UserSecurityData> listOfAllNews();

    UserSecurityData getUserSecurityData(Integer id);
    UserSecurityData saveUserSecurityData(UserSecurityData usd);
    void deleteUserSecurityData(Integer id);
    UserSecurityData getUserSecurityDataByEmail(String email);

    boolean isAdmin(UserSecurityData user);
}
