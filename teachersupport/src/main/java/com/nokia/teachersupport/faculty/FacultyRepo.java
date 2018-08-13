package com.nokia.teachersupport.faculty;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FacultyRepo extends CrudRepository<Faculty, Integer> {
    List<Faculty> findAll();

    Faculty findByFacultyNameField(String facultyName); //!UWAGA NA  TO
    Faculty findByid(Integer Id);
}

