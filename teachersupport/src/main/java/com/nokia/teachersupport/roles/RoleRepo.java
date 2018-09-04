package com.nokia.teachersupport.roles;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RoleRepo extends CrudRepository<SecurityRole, Integer> {
    List<SecurityRole> findAll();
    SecurityRole findByRoleName(String roleName);
}

