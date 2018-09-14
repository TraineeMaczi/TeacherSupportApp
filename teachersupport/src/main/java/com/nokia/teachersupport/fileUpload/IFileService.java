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

    void deleteFileById(Integer id);

    FileModel findFileById(Integer id);

    void goUploadMultipartFile(FileModel fileModel, String facultyName, IFileService fileService, IFacultyService facultyService);
}
