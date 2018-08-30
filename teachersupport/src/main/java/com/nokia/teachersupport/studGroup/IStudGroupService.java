package com.nokia.teachersupport.studGroup;

import com.nokia.teachersupport.fileUpload.IFileService;
import com.nokia.teachersupport.person.IPersonService;
import com.nokia.teachersupport.personSecurity.IUserSecurityDataService;
import org.springframework.ui.Model;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.List;

public interface IStudGroupService {
void deleteStudGroupById(Integer studGroupId);
StudGroup saveStudGroup(StudGroup studGroup);
StudGroup getStudGroupByName(String studGroupName);
List<StudGroup> listOfAllGroups();
void studGroupDTOIntoStudGroup(StudGroupDTO studGroupDTO,StudGroup studGroup);
StudGroup addStudGroup( StudGroup studGroup, IPersonService personService, IUserSecurityDataService userSecurityDataService);
void deleteStudGroup(String groupName, IPersonService personService, IFileService fileService,
                     IGroupRemoteResourceService remoteResourceService, IUserSecurityDataService userSecurityDataService, HttpSession session);
void goStudGroupUpdate(StudGroupDTO studGroupDTO,HttpSession session,IUserSecurityDataService userSecurityDataService,IPersonService personService);

}
