package com.nokia.teachersupport.person;

import com.nokia.teachersupport.personSecurity.UserSecurityData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PersonServiceImpl implements IPersonService {

    private PersonRepo personRepo;

    @Autowired
    public PersonServiceImpl(PersonRepo personRepo) {
        this.personRepo = personRepo;
    }

    @Override
    public List<Person> listOfAllPersons() {
        return personRepo.findAll();
    }

    @Override
    public Person getPerson(Integer id) {
        Optional<Person> Opt = personRepo.findById(id);
        Person person = Opt.orElse(new Person());
        return person;
    }

    @Override
    public Person savePerson(Person person) {
        return personRepo.save(person);
    }

    @Override
    public void deletePerson(Integer id) {
    personRepo.deleteById(id);


    }

    @Override
    public Person getPersonByUserSecurityData(UserSecurityData userSecurityData) {
        return personRepo.findByUserSecurityDataField(userSecurityData);
    }

//    @Override
//    public Person getPersonByName(String name) {
//      return personRepo.findByNameField(name);
//    }
}
