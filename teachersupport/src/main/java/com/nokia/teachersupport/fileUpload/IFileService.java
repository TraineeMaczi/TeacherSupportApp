package com.nokia.teachersupport.fileUpload;


import com.nokia.teachersupport.serviceProvider.IServiceProvider;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface IFileService {
    FileModel saveFile(FileModel fileModel);

    FileModel saveMultipartFile(MultipartFile file, String type) throws IOException;

    void deleteFileById(Integer id);

    FileModel findFileById(Integer id);

    void goUploadMultipartFile(FileModel fileModel, String facultyName, IServiceProvider serviceProvider);
}
