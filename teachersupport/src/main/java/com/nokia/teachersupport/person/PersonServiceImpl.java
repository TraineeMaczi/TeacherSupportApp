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

    @Override
    public void setPersonBasicInfo(BasicInfoDTO basicInfoDTO,Person person)
    {
        if(!basicInfoDTO.getDegree().equals("")) person.setDegreeField(basicInfoDTO.getDegree());

        if(!basicInfoDTO.getWorkplace().equals(""))   person.setWorkAddressField(basicInfoDTO.getWorkplace());

        if(!basicInfoDTO.getProfession().equals("")) person.setProfessionField(basicInfoDTO.getProfession());

        if(!basicInfoDTO.getUsos().equals("")) person.setUsosPersonProfileLinkField(basicInfoDTO.getUsos());

        if(!basicInfoDTO.getTwitter().equals("")) person.setTwitterField(basicInfoDTO.getTwitter());

        if(!basicInfoDTO.getFacebook().equals("")) person.setFacebookField(basicInfoDTO.getFacebook());

        if(!basicInfoDTO.getPhone().equals("")) person.setPhoneNumberField(basicInfoDTO.getPhone());


    }

}
