package com.nokia.teachersupport.roles;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RoleRepo extends CrudRepository<SecutityRole, Integer> {
    List<SecutityRole> findAll();
    SecutityRole findByRoleName(String roleName);
}

