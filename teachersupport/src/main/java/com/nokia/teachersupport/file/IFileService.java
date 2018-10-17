package com.nokia.teachersupport.file;


import com.nokia.teachersupport.serviceProvider.IServiceProvider;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface IFileService {
    File saveFile(File file);

    File saveMultipartFile(MultipartFile file, String type, IServiceProvider serviceProvider) throws IOException;

    void deleteFileById(Integer id);

    File findFileById(Integer id);

    void goUploadMultipartFile(File file, String facultyName, IServiceProvider serviceProvider);
}
