package com.nokia.teachersupport.studGroup;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.Assert.*;
@RunWith(MockitoJUnitRunner.class)
public class GroupRemoteResourceServiceImplTest {

    @InjectMocks
    GroupRemoteResourceServiceImpl groupRemoteResourceService;
    private RemoteStudGroupResourceDTO dto=new RemoteStudGroupResourceDTO();
    private GroupRemoteResource groupRemoteResource=new GroupRemoteResource();
    @Before
    public void SetUp()
    {
        dto.setLink("Link");
        dto.setName("Name");
        groupRemoteResource.setResourceLink("Link");
        groupRemoteResource.setStudGroupResourceName("Name");
    }
    @Test
    public void resourceDTOIntoResource() {

        assertEquals(groupRemoteResource.getResourceLink(),groupRemoteResourceService.resourceDTOIntoResource(dto).getResourceLink());
        assertEquals(groupRemoteResource.getStudGroupResourceName(),groupRemoteResourceService.resourceDTOIntoResource(dto).getStudGroupResourceName());
    }
}