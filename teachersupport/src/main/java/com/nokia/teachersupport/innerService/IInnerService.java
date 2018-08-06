package com.nokia.teachersupport.innerService;

import com.nokia.teachersupport.studGroup.StudGroup;

public interface IInnerService {
    void setServiceStudGroupInstance(StudGroup studGroup);
    StudGroup getServiceStudGroupInstance();

}
