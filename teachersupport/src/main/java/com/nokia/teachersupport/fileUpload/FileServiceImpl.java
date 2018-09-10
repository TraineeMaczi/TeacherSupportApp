package com.nokia.teachersupport.fileUpload;

import com.nokia.teachersupport.faculty.Faculty;
import com.nokia.teachersupport.faculty.IFacultyService;
import com.nokia.teachersupport.person.IPersonService;
import com.nokia.teachersupport.person.Person;
import com.nokia.teachersupport.personSecurity.IUserSecurityDataService;
import com.nokia.teachersupport.studGroup.IStudGroupService;
import com.nokia.teachersupport.studGroup.StudGroup;
import com.nokia.teachersupport.studGroup.StudGroupServiceImpl;
import com.nokia.teachersupport.tools.CurrentUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Service
public class FileServiceImpl implements IFileService {

    private FileRepository fileRepository;



    @Autowired
    public FileServiceImpl(FileRepository fileRepository) {
        this.fileRepository = fileRepository;
    }


    @Override
    public FileModel saveFile(FileModel fileModel) {
        return fileRepository.save(fileModel);
    }

    @Override
    public FileModel saveMultipartFile(MultipartFile file, String type) throws IOException {
        FileModel fileModel = new FileModel(file.getOriginalFilename(), type, file.getBytes());
            if (file.getOriginalFilename().equals("") )
                return fileModel;
            fileRepository.save(fileModel);

            return fileModel;
        }


        @Override
        public void deleteFileById (Integer id){
            fileRepository.deleteById(id);
        }

        @Override
        public FileModel findFileById (Integer id){

            return fileRepository.findById(id).orElse(new FileModel());

        }


        @Override
        public void goUploadMultipartFile (FileModel fileModel, String facultyName, IFileService
        fileService, IFacultyService facultyService){
            Faculty faculty = new Faculty();
            faculty.setFacultyNameField(facultyName);
            faculty.setFile(fileModel);
            facultyService.saveFaculty(faculty);
        }
    }
