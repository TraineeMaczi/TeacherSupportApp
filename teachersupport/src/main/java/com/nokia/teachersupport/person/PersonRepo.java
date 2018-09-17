package com.nokia.teachersupport.person;


import com.nokia.teachersupport.personSecurity.UserSecurityData;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PersonRepo  extends CrudRepository<Person, Integer> {
    List<Person> findAll();
    Person findByUserSecurityDataField(UserSecurityData userSecurityData);
}
