package com.nokia.teachersupport.faculty;

import com.nokia.teachersupport.fileUpload.IFileService;

import java.util.List;

public interface IFacultyService {
    List<Faculty> listOfAllFaculties();

    Faculty findFaculty(String facultyName);
    Faculty saveFaculty(Faculty faculty);
    Faculty findFacultyById(Integer Id);
    void deleteFaculty(Faculty faculty);
    void goDeleteFacultySiteAction(String facultyName, IFacultyService facultyService, IFileService fileService);
}
