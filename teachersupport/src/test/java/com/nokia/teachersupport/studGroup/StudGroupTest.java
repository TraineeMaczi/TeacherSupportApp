package com.nokia.teachersupport.studGroup;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;

import static org.junit.Assert.*;


public class StudGroupTest {

    private String FromH,FromM,ToH,ToM;
    private String outputStringTime;

    @Before
    public void SetUp()
    {
        FromH="10";
        FromM="15";
        ToH="11";
        ToM="45";
        outputStringTime="10:15-11:45";
    }
    @Test
    public void dispTime() {
    assertEquals(outputStringTime,StudGroup.dispTime(FromH,FromM,ToH,ToM));
    }
}