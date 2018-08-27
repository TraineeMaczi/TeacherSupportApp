package com.nokia.teachersupport.faculty;

import com.nokia.teachersupport.fileUpload.IFileService;
import com.nokia.teachersupport.person.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FacultyServiceImpl implements IFacultyService {
    FacultyRepo facultyRepo;

    @Autowired
    public FacultyServiceImpl(FacultyRepo facultyRepo) {
        this.facultyRepo = facultyRepo;
    }


    @Override
    public List<Faculty> listOfAllFaculties() {
        return facultyRepo.findAll();
    }

    @Override
    public Faculty findFaculty(String facultyName) {
        return facultyRepo.findByFacultyNameField(facultyName);
    }

    @Override
    public Faculty saveFaculty(Faculty faculty) {
        return facultyRepo.save(faculty);
    }

    @Override
    public Faculty findFacultyById(Integer Id) {
        return facultyRepo.findByid(Id);
    }

    @Override
    public void deleteFaculty(Faculty faculty) {
        facultyRepo.delete(faculty);
    }

    @Override
    public void goDeleteFacultySiteAction(String facultyName, IFileService fileService) {

        if (findFaculty(facultyName) != null) {
            Faculty faculty = findFaculty(facultyName);
            fileService.dleteFileById(faculty.getFile().getId());
            faculty.setFile(null);

            List<Person> myPersons = faculty.getFacultyAndPersonList();
            for (Integer i = 0; !myPersons.isEmpty(); i++) {
                Person currentPerson = myPersons.get(i);
                currentPerson.deleteFaculty(faculty);
                myPersons.remove(currentPerson);
            }
            deleteFaculty(faculty);
        }

    }
}
