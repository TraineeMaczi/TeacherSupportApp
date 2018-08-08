package com.nokia.teachersupport.studGroup;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudGroupServiceImpl implements IStudGroupService {

    private StudGroupRepo studGroupRepo;

    @Autowired
    public void setStudGroupRepo(StudGroupRepo studGroupRepo) {
        this.studGroupRepo=studGroupRepo;
    }


    @Override
    public void deleteStudGroupById(Integer studGroupId) {
        studGroupRepo.deleteById(studGroupId);
    }

    @Override
    public StudGroup saveStudGroup(StudGroup studGroup) {
        return studGroupRepo.save(studGroup);
    }

    @Override
    public StudGroup getStudGroupByName(String studGroupName) {
        return studGroupRepo.findByGroupNameField(studGroupName);
    }

    @Override
    public List<StudGroup> listOfAllGroups() {
        return studGroupRepo.findAll();
    }

    @Override
    public StudGroup studGroupDTOIntoStudGroup(StudGroupDTO studGroupDTO) {
        return null;
    }
}
