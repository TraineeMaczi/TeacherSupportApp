package com.nokia.teachersupport.file;

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


    @Autowired
    public FileServiceImpl(FileRepository fileRepository) {

        this.fileRepository = fileRepository;

    }

    @Override
    public File saveFile(File file) {
        return fileRepository.save(file);
    }

    @Override
    public File saveMultipartFile(MultipartFile file, String type, IServiceProvider serviceProvider) throws IOException {
        File fileModel = new File(file.getOriginalFilename(), type, file.getBytes());
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
    public File findFileById(Integer id) {

        return fileRepository.findById(id).orElse(new File());

    }


    @Override
    public void goUploadMultipartFile(File file, String facultyName, IServiceProvider serviceProvider) {
        Faculty faculty = new Faculty();
        faculty.setFacultyNameField(facultyName);
        faculty.setFile(file);
        serviceProvider.getIFacultyService().saveFaculty(faculty);
    }
}
