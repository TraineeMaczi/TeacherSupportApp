package com.nokia.teachersupport.fileUpload;


import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Optional;

public interface IFileService {
    FileModel saveFile(FileModel fileModel);

    FileModel saveMultipartFile(MultipartFile file, String type) throws IOException;

    FileModel findFileByName(String name);

    void dleteFileById(Integer id);

    FileModel findFileById(Integer id);
}
