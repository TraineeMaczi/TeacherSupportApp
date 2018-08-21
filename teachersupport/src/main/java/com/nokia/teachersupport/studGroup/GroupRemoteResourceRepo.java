package com.nokia.teachersupport.studGroup;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GroupRemoteResourceRepo extends CrudRepository<GroupRemoteResource, Integer> {
    List<GroupRemoteResource> findAll();
}
