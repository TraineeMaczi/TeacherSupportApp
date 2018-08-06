package com.nokia.teachersupport.person;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MeetMeRepo extends CrudRepository<MeetMe, Integer> {
    List<MeetMe> findAll();

}


