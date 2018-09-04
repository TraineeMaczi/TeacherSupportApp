package com.nokia.teachersupport.roles;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoleServiceImpl implements IRoleService {
    private RoleRepo roleRepo;
    @Autowired
    public RoleServiceImpl(RoleRepo roleRepo) {
        this.roleRepo = roleRepo;
    }

    @Override
    public void save(SecurityRole securityRole) {
        roleRepo.save(securityRole);
    }

    @Override
    public SecurityRole findByRoleName(String roleName) {
        return roleRepo.findByRoleName(roleName);
    }
}
