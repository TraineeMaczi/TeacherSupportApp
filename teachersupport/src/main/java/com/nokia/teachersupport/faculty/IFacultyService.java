package com.nokia.teachersupport.faculty;

import java.util.List;

public interface IFacultyService {
    List<Faculty> listOfAllFaculties();

    Faculty findFaculty(String facultyName);
    Faculty saveFaculty(Faculty faculty);
    Faculty findFacultyById(Integer Id);
}
