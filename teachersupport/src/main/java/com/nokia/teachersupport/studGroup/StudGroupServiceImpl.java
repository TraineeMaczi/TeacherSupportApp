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
    public void studGroupDTOIntoStudGroup(StudGroupDTO studGroupDTO,StudGroup studGroup) {
        studGroup.setGroupNrFiled(studGroupDTO.getGroupNrFiled());
        studGroup.setFacultyField(studGroupDTO.getFacultyField()); //tu by bylo fajnie zeby faculty nie wpisywac tylko pobrac z listy
        studGroup.setClassNameField(studGroupDTO.getClassNameField());
        studGroup.setClassDayFiled(studGroupDTO.getClassDayFiled());
        studGroup.setTimeFromFieldH(studGroupDTO.getTimeFromFieldH());
        studGroup.setTimeFromFieldM(studGroupDTO.getTimeFromFieldM());
        studGroup.setTimeToFieldH(studGroupDTO.getTimeToFieldH());
        studGroup.setTimeToFieldM(studGroupDTO.getTimeToFieldM());

    }
}
