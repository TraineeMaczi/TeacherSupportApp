package com.nokia.teachersupport.studGroup;

import java.util.List;

public interface IStudGroupService {
void deleteStudGroupById(Integer studGroupId);
StudGroup saveStudGroup(StudGroup studGroup);
StudGroup getStudGroupByName(String studGroupName);
List<StudGroup> listOfAllGroups();
void studGroupDTOIntoStudGroup(StudGroupDTO studGroupDTO,StudGroup studGroup);

}
