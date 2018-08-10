package com.nokia.teachersupport.person;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MeetMeServiceImpl implements IMeetMeService {

    private MeetMeRepo meetMeRepo;
    private PersonRepo personRepo;

    @Autowired
    public MeetMeServiceImpl(MeetMeRepo meetMeRepo, PersonRepo personRepo) {
        this.meetMeRepo = meetMeRepo;
        this.personRepo = personRepo;
    }

    @Override
    public List<MeetMe> listOfAllMeetMe() {
        return meetMeRepo.findAll();
    }

    @Override
    public MeetMe getMeetMe(Integer id) {
        Optional<MeetMe> Opt = meetMeRepo.findById(id);
        MeetMe meetMe = Opt.orElse(new MeetMe());
        return meetMe;
    }

    @Override
    public MeetMe saveMeetMe(MeetMe meetMe) {
        return meetMeRepo.save(meetMe);
    }

    @Override
    public void deleteMeetMe(Integer id) {
        meetMeRepo.deleteById(id);
    }

    @Override
    public MeetMe meetMeDTOIntoMeetMe(MeetMeDTO meetMeDTO) {
        MeetMe meetMe = new MeetMe();
        meetMe.setPlaceField(meetMeDTO.getPlaceField());
        meetMe.setOfficeField(meetMeDTO.getOfficeField());
        meetMe.setDayField(meetMeDTO.getDayField());
        meetMe.setTimeField(meetMeDTO.getTimeFromFieldH() + ":" + meetMeDTO.getTimeFromFieldM() + "-" + meetMeDTO.getTimeToFieldH() + ":" + meetMeDTO.getTimeToFieldM());
        return meetMe;
    }

    public void addContactInfo(Person person, MeetMe meetMe) {
        meetMe.setMeetMeOwner(person);
        person.addMeetMeToMyList(meetMe);
        this.saveMeetMe(meetMe);
        personRepo.save(person);
    }
}
