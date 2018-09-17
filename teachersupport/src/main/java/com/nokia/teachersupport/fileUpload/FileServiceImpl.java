package com.nokia.teachersupport.fileUpload;

import com.nokia.teachersupport.faculty.Faculty;
import com.nokia.teachersupport.person.Person;
import com.nokia.teachersupport.serviceProvider.IServiceProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
public class FileServiceImpl implements IFileService {

    private FileRepository fileRepository;
    private IServiceProvider serviceProvider;

    @Autowired
    public FileServiceImpl(FileRepository fileRepository, IServiceProvider serviceProvider) {

        this.fileRepository = fileRepository;
        this.serviceProvider = serviceProvider;
    }

    @Override
    public FileModel saveFile(FileModel fileModel) {
        return fileRepository.save(fileModel);
    }

    @Override
    public FileModel saveMultipartFile(MultipartFile file, String type) throws IOException {
        FileModel fileModel = new FileModel(file.getOriginalFilename(), type, file.getBytes());
        if (file.getOriginalFilename().equals(""))
            return fileModel;
        fileRepository.save(fileModel);
        if (fileModel.getType().equals("CV")) {
            Person person = serviceProvider.getIPersonService().getCurrentPerson(serviceProvider);
            person.setCV(fileModel);
            serviceProvider.getIPersonService().savePerson(person);
        }
        return fileModel;
    }


    @Override
    public void deleteFileById(Integer id) {
        fileRepository.deleteById(id);
    }

    @Override
    public FileModel findFileById(Integer id) {

        return fileRepository.findById(id).orElse(new FileModel());

    }


    @Override
    public void goUploadMultipartFile(FileModel fileModel, String facultyName, IServiceProvider serviceProvider) {
        Faculty faculty = new Faculty();
        faculty.setFacultyNameField(facultyName);
        faculty.setFile(fileModel);
        serviceProvider.getIFacultyService().saveFaculty(faculty);
    }
}
