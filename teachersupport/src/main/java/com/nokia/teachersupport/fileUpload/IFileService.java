package com.nokia.teachersupport.fileUpload;


import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface IFileService {
    FileModel saveFile(FileModel fileModel);

    boolean saveMultipartFile(MultipartFile file, String type) throws IOException;
    FileModel findFileByName(String name);
}
