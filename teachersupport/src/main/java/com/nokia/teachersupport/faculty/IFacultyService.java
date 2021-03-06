package com.nokia.teachersupport.faculty;

import com.nokia.teachersupport.serviceProvider.IServiceProvider;

import java.util.List;

public interface IFacultyService {
    List<Faculty> listOfAllFaculties();
    Faculty findFaculty(String facultyName);
    Faculty saveFaculty(Faculty faculty);
    void deleteFaculty(Faculty faculty);
    void goDeleteFacultySiteAction(String facultyName, IServiceProvider serviceProvider);
}
