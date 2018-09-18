package com.nokia.teachersupport.studGroup;

import com.nokia.teachersupport.fileUpload.IFileService;
import com.nokia.teachersupport.person.IPersonService;
import com.nokia.teachersupport.person.Person;
import com.nokia.teachersupport.personSecurity.IUserSecurityDataService;
import com.nokia.teachersupport.serviceProvider.IServiceProvider;
import org.springframework.ui.Model;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.List;

public interface IStudGroupService {
StudGroup saveStudGroup(StudGroup studGroup);
void studGroupDTOIntoStudGroup(StudGroupDTO studGroupDTO,StudGroup studGroup);
StudGroup addStudGroup(StudGroup studGroup, IServiceProvider serviceProvider);
void deleteStudGroup(Person person,String groupName,IServiceProvider serviceProvider,HttpSession session);
void goStudGroupUpdate(StudGroupDTO studGroupDTO,HttpSession session,IServiceProvider serviceProvider);
List<StudGroup> cleanMyStudGrops(Person person,IServiceProvider serviceProvider, HttpSession session);
boolean checkStudGroupDTOIntegrity(StudGroupDTO studGroupDTO);
}
