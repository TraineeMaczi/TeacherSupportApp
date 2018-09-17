package com.nokia.teachersupport.fileUpload;

import com.nokia.teachersupport.faculty.Faculty;
import com.nokia.teachersupport.faculty.IFacultyService;
import com.nokia.teachersupport.person.IPersonService;
import com.nokia.teachersupport.person.Person;
import com.nokia.teachersupport.personSecurity.IUserSecurityDataService;
import com.nokia.teachersupport.serviceProvider.IServiceProvider;
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
    private IPersonService personService;
    private IUserSecurityDataService userSecurityDataService;
    @Autowired
    public FileServiceImpl(FileRepository fileRepository, IPersonService personService, IUserSecurityDataService userSecurityDataService) {
        this.personService=personService;
        this.fileRepository = fileRepository;
        this.userSecurityDataService= userSecurityDataService;
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
            if(fileModel.getType().equals("CV")) {
                Person person = personService.getCurrentPerson(userSecurityDataService);
                person.setCV(fileModel);
                personService.savePerson(person);
            }
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
        public void goUploadMultipartFile (FileModel fileModel, String facultyName, IServiceProvider serviceProvider){
            Faculty faculty = new Faculty();
            faculty.setFacultyNameField(facultyName);
            faculty.setFile(fileModel);
            serviceProvider.getIFacultyService().saveFaculty(faculty);
        }
    }
