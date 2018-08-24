package com.nokia.teachersupport.person;

import com.nokia.teachersupport.personSecurity.IUserSecurityDataService;

import java.util.List;

public interface IMeetMeService {
    List<MeetMe> listOfAllMeetMe();
    MeetMe getMeetMe(Integer id);
    MeetMe saveMeetMe(MeetMe meetMe);
    void deleteMeetMe(Integer id);
    MeetMe meetMeDTOIntoMeetMe(MeetMeDTO meetMeDTO);
    void addContactInfo(Person person, MeetMe meetMe);
    MeetMeDTO goAddContactInfo(MeetMeDTO meetMeDTO,IPersonService personService,IUserSecurityDataService userSecurityDataService);
    Integer goDeleteContactInfo(Integer id, IUserSecurityDataService userSecurityDataService,IPersonService personService);
    boolean checkMeetMeDTOIntegrity(MeetMeDTO meetMeDTO);

}
