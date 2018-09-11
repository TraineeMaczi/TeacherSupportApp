package com.nokia.teachersupport.person;

import com.nokia.teachersupport.personSecurity.IUserSecurityDataService;
import com.nokia.teachersupport.tools.CurrentUser;
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
        meetMe.setTimeField(((meetMeDTO.getTimeFromFieldH().length() == 1) ? "0" : "") + meetMeDTO.getTimeFromFieldH() + ":" + ((meetMeDTO.getTimeFromFieldM().length() == 1) ? "0" : "") + meetMeDTO.getTimeFromFieldM() + "-" + ((meetMeDTO.getTimeToFieldH().length() == 1) ? "0" : "") + meetMeDTO.getTimeToFieldH() + ":" + ((meetMeDTO.getTimeToFieldM().length() == 1) ? "0" : "") + meetMeDTO.getTimeToFieldM());
        return meetMe;
    }

    public void addContactInfo(Person person, MeetMe meetMe) {
        meetMe.setMeetMeOwner(person);
        person.addMeetMeToMyList(meetMe);
        this.saveMeetMe(meetMe);
        personRepo.save(person);
    }

    @Override
    public MeetMeDTO goAddContactInfo(MeetMeDTO meetMeDTO, IPersonService personService, IUserSecurityDataService userSecurityDataService) {
        Person person = personService.getCurrentPerson(userSecurityDataService);
        MeetMe meetMe = meetMeDTOIntoMeetMe(meetMeDTO);
        addContactInfo(person, meetMe);
        return meetMeDTO;
    }

    @Override
    public Integer goDeleteContactInfo(Integer id, IUserSecurityDataService userSecurityDataService, IPersonService personService) {
        Person person = personService.getCurrentPerson(userSecurityDataService);
        MeetMe meetMe = getMeetMe(id);
        person.getPersonMeetMeDataList().remove(meetMe);
        deleteMeetMe(id);
        personService.savePerson(person);
        return id;
    }

    @Override
    public boolean checkMeetMeDTOIntegrity(MeetMeDTO meetMeDTO) {
        if (meetMeDTO.getPlaceField().equals("") || meetMeDTO.getOfficeField().equals("") || meetMeDTO.getDayField().equals("") || meetMeDTO.getTimeFromFieldH().equals("") ||
                meetMeDTO.getTimeFromFieldM().equals("") || meetMeDTO.getTimeToFieldM().equals("") || meetMeDTO.getTimeToFieldH().equals("")) {
            return false;
        } else {
            try {
                double fromH = Double.parseDouble(meetMeDTO.getTimeFromFieldH());
                double fromM = Double.parseDouble(meetMeDTO.getTimeFromFieldM());
                double toH = Double.parseDouble(meetMeDTO.getTimeToFieldH());
                double toM = Double.parseDouble(meetMeDTO.getTimeToFieldM());
                if (toM < 0 || toH < 0 || fromH < 0 || fromM < 0) {
                    return false;
                }
                if (toM > 60 || toH > 24 || fromH > 24 || fromM > 60) {
                    return false;
                }
                if (fromH * 60 + fromM > toH * 60 + toM)
                    return false;

            } catch (NumberFormatException nfe) {
                return false;
            }
            return true;
        }
    }

    @Override
    public List<MeetMe> cleanMyMeetMeData(Person person, IPersonService personService) {
        List<MeetMe> personMeetMeList = person.getPersonMeetMeDataList();

        for (MeetMe mm : personMeetMeList) {
            meetMeRepo.delete(mm);
        }
        personMeetMeList.clear();
        personService.savePerson(person);

        return personMeetMeList;
    }
}
