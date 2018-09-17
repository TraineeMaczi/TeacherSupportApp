package com.nokia.teachersupport.person;

import com.nokia.teachersupport.personSecurity.IUserSecurityDataService;
import com.nokia.teachersupport.serviceProvider.IServiceProvider;

import java.util.List;

public interface IMeetMeService {
    MeetMe getMeetMe(Integer id);
    MeetMe saveMeetMe(MeetMe meetMe);
    void deleteMeetMe(Integer id);
    MeetMe meetMeDTOIntoMeetMe(MeetMeDTO meetMeDTO);
    void addContactInfo(Person person, MeetMe meetMe);
    MeetMeDTO goAddContactInfo(MeetMeDTO meetMeDTO, IServiceProvider serviceProvider);
    Integer goDeleteContactInfo(Integer id,IServiceProvider serviceProvider);
    boolean checkMeetMeDTOIntegrity(MeetMeDTO meetMeDTO);
    List<MeetMe> cleanMyMeetMeData(Person person,IServiceProvider serviceProvider);
}
