package com.nokia.teachersupport.studGroup;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudGroupRepo extends CrudRepository<StudGroup, Integer> {
    List<StudGroup> findAll();
}
