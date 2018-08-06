package com.nokia.teachersupport.fileUpload;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FileServiceImpl implements IFileService {
    private FileRepository fileRepository;
    @Autowired
    public FileServiceImpl(FileRepository fileRepository) {
        this.fileRepository = fileRepository;
    }

    @Override
    public FileModel savefile(FileModel fileModel) {
        return fileRepository.save(fileModel);
    }
}
