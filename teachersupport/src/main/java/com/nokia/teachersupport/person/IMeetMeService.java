package com.nokia.teachersupport.person;

import java.util.List;

public interface IMeetMeService {
    List<MeetMe> listOfAllMeetMe();
    MeetMe getMeetMe(Integer id);
    MeetMe saveMeetMe(MeetMe meetMe);
    void deleteMeetMe(Integer id);
    MeetMe meetMeDTOIntoMeetMe(MeetMeDTO meetMeDTO);
    public void addContactInfo(Person person, MeetMe meetMe);

}
