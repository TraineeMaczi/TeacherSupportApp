//package com.nokia.teachersupport.studGroup;
//
//import org.junit.Before;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.junit.MockitoJUnitRunner;
//
//import javax.servlet.http.HttpSession;
//
//import static org.junit.Assert.*;
//import static org.mockito.Mockito.when;
//
//@RunWith(MockitoJUnitRunner.class)
//public class StudGroupServiceImplTest {
//
//    @InjectMocks
//    StudGroupServiceImpl studGroupService;
//
//    @Mock
//    StudGroupRepo studGroupRepo;
//
//    @Mock
//    HttpSession session;
//
//    private StudGroup studGroup = new StudGroup();
//    private StudGroupDTO dto = new StudGroupDTO();
//    private StudGroup studGroupEmpty=new StudGroup();
//    private StudGroup testGroup=new StudGroup();
//    private StudGroupDTO dtoForTestGroup=new StudGroupDTO();
//
//    @Before
//    public void SetUp() {
//
//        testGroup.setGroupNameField("Test Group");
//
//        dtoForTestGroup.setGroupNameField("testo");
//        dtoForTestGroup.setFacultyField("Faculty");
//        dtoForTestGroup.setGroupNrFiled("8");
//        dtoForTestGroup.setClassNameField("Class name");
//        dtoForTestGroup.setClassDayFiled("Day");
//        dtoForTestGroup.setTimeFromFieldH("10");
//        dtoForTestGroup.setTimeToFieldH("15");
//        dtoForTestGroup.setTimeFromFieldM("12");
//        dtoForTestGroup.setTimeToFieldM("15");
//
//        dto.setGroupNameField("Name");
//        dto.setFacultyField("Faculty");
//        dto.setGroupNrFiled("8");
//        dto.setClassNameField("Class name");
//        dto.setClassDayFiled("Day");
//        dto.setTimeFromFieldH("10");
//        dto.setTimeToFieldH("15");
//        dto.setTimeFromFieldM("12");
//        dto.setTimeToFieldM("15");
//
//
//        studGroup.setGroupNameField("Name");
//        studGroup.setFacultyField("Faculty");
//        studGroup.setGroupNrFiled("8");
//        studGroup.setClassNameField("Class name");
//        studGroup.setClassDayFiled("Day");
//        studGroup.setTimeFromFieldH("10");
//        studGroup.setTimeToFieldH("15");
//        studGroup.setTimeFromFieldM("12");
//        studGroup.setTimeToFieldM("15");
//
//
//        when(session.getAttribute("currentStudGroupName")).thenReturn("Test Group");
//        when(studGroupRepo.findByGroupNameField("Test Group")).thenReturn(testGroup);
//    }
//
//    @Test
//    public void goStudGroupUpdate()
//    {
//    studGroupService.goStudGroupUpdate(dtoForTestGroup,session,); //UWAGA BO ZMIENIALS
//    assertEquals("testo",testGroup.getGroupNameField());
//        assertEquals("Faculty",testGroup.getFacultyField());
//        assertEquals("8",testGroup.getGroupNrFiled());
//        assertEquals("Class name",testGroup.getClassNameField());
//        assertEquals("Day",testGroup.getClassDayFiled());
//        assertEquals("10",testGroup.getTimeFromFieldH());
//        assertEquals("15",testGroup.getTimeToFieldH());
//        assertEquals("12",testGroup.getTimeFromFieldM());
//        assertEquals("15",testGroup.getTimeToFieldM());
//
//    }
//
//    @Test
//    public void studGroupDTOIntoStudGroup() {
//
//        studGroupService.studGroupDTOIntoStudGroup(dto,studGroupEmpty);
//      assertEquals(studGroup.getGroupNameField(),studGroupEmpty.getGroupNameField());
//       assertEquals(studGroup.getFacultyField(),studGroupEmpty.getFacultyField());
//        assertEquals(studGroup.getGroupNrFiled(),studGroupEmpty.getGroupNrFiled());
//        assertEquals(studGroup.getClassNameField(),studGroupEmpty.getClassNameField());
//        assertEquals(studGroup.getClassDayFiled(),studGroupEmpty.getClassDayFiled());
//        assertEquals(studGroup.getTimeFromFieldH(),studGroupEmpty.getTimeFromFieldH());
//        assertEquals(studGroup.getTimeToFieldH(),studGroupEmpty.getTimeToFieldH());
//        assertEquals(studGroup.getTimeFromFieldM(),studGroupEmpty.getTimeFromFieldM());
//        assertEquals(studGroup.getTimeToFieldM(),studGroupEmpty.getTimeToFieldM());
//    }
//}
//
