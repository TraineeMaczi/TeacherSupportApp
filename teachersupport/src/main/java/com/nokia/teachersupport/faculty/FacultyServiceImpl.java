package com.nokia.teachersupport.faculty;

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
}
