package com.nokia.teachersupport.fileUpload;

import com.nokia.teachersupport.person.IPersonService;
import com.nokia.teachersupport.personSecurity.IUserSecurityDataService;
import com.nokia.teachersupport.studGroup.IStudGroupService;
import com.nokia.teachersupport.studGroup.StudGroup;
import com.nokia.teachersupport.studGroup.StudGroupServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Service
public class FileServiceImpl implements IFileService {

    private FileRepository fileRepository;
    private IStudGroupService studGroupService;

    @Autowired
    public FileServiceImpl(FileRepository fileRepository, IStudGroupService studGroupService) {
        this.fileRepository = fileRepository;
        this.studGroupService = studGroupService;
    }


    @Override
    public FileModel saveFile(FileModel fileModel) {
        return fileRepository.save(fileModel);
    }

    @Override
    public FileModel saveMultipartFile(MultipartFile file, String type) throws IOException {
        FileModel fileModel = new FileModel(file.getOriginalFilename(), type, file.getBytes());
        if (type.contains("res") && type.length() > 8) {
            String pom = type.substring(8);
            System.out.println(pom);
            StudGroup studGroup = studGroupService.getStudGroupByName(pom);
            fileModel.setFilesOfGroup(studGroup);
            List<FileModel> fileModels = studGroup.getFileModels();
            fileModels.add(fileModel);
            studGroup.setFileModels(fileModels);
            studGroupService.saveStudGroup(studGroup);
        }
        if (file.getOriginalFilename().equals(""))
            return fileModel;
        fileRepository.save(fileModel);
        return fileModel;
    }

    @Override
    public FileModel findFileByName(String name) {
        return fileRepository.findByName(name);
    }

    @Override
    public void dleteFileById(Integer id) {
        fileRepository.deleteById(id);
    }

    @Override
    public FileModel findFileById(Integer id) {

        return fileRepository.findById(id).orElse(new FileModel());

    }
}
