package com.nokia.teachersupport.personSecurity;

import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.data.repository.CrudRepository;

import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserSecurityDataRepo extends CrudRepository<UserSecurityData, Integer> {
    List<UserSecurityData> findAll();
    UserSecurityData findByEmail(String email);
}

