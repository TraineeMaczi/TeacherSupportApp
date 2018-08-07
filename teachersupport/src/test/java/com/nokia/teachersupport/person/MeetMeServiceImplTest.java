package com.nokia.teachersupport.person;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.Assert.*;

@RunWith(MockitoJUnitRunner.class)
public class MeetMeServiceImplTest {
    @InjectMocks
    MeetMeServiceImpl meetMeService;
    @Mock
    MeetMeRepo meetMeRepo;

    @Test
    public void meetMeDTOIntoMeetMe() {
        MeetMe meetMe=new MeetMe();
        meetMe.setPlaceField("place");
        meetMe.setOfficeField("office");
        meetMe.setDayField("wtorek");
        meetMe.setTimeField("10:10-11:11");

        MeetMeDTO meetMeDTO=new MeetMeDTO();
        meetMeDTO.setPlaceField("place");
        meetMeDTO.setOfficeField("office");
        meetMeDTO.setDayField("wtorek");
        meetMeDTO.setTimeFromFieldH("10");
        meetMeDTO.setTimeFromFieldM("10");
        meetMeDTO.setTimeToFieldH("11");
        meetMeDTO.setTimeToFieldM("11");

        assertEquals(meetMe.getPlaceField(),meetMeService.meetMeDTOIntoMeetMe(meetMeDTO).getPlaceField());
        assertEquals(meetMe.getOfficeField(),meetMeService.meetMeDTOIntoMeetMe(meetMeDTO).getOfficeField());
        assertEquals(meetMe.getDayField(),meetMeService.meetMeDTOIntoMeetMe(meetMeDTO).getDayField());
        assertEquals(meetMe.getTimeField(),meetMeService.meetMeDTOIntoMeetMe(meetMeDTO).getTimeField());

    }
}