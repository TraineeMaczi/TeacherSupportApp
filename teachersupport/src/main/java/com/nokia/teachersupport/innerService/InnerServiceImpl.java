package com.nokia.teachersupport.innerService;

import com.nokia.teachersupport.studGroup.StudGroup;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class InnerServiceImpl implements IInnerService {

    private StudGroup studGroup;

    @Autowired
    public InnerServiceImpl(StudGroup studGroup) {
        this.studGroup = studGroup;
    }


    @Override
    public void setServiceStudGroupInstance(StudGroup studGroup) {
        this.studGroup = studGroup;
    }

    @Override
    public StudGroup getServiceStudGroupInstance() {
        return this.studGroup;
    }
}
