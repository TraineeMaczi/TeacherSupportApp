package com.nokia.teachersupport.fileUpload;


import com.nokia.teachersupport.faculty.IFacultyService;
import com.nokia.teachersupport.person.IPersonService;
import com.nokia.teachersupport.personSecurity.IUserSecurityDataService;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Optional;

public interface IFileService {
    FileModel saveFile(FileModel fileModel);

    FileModel saveMultipartFile(MultipartFile file, String type) throws IOException;

    FileModel findFileByName(String name);

    void dleteFileById(Integer id);

    FileModel findFileById(Integer id);

    void goDeleteLocalResource(Integer id, HttpSession session, IPersonService personService, IUserSecurityDataService userSecurityDataService,IFileService fileService);
    void goUploadMultipartFile(FileModel fileModel, String facultyName, IFileService fileService, IFacultyService facultyService);
}
