package com.nokia.teachersupport.roles;

public interface IRoleService {
    void save(SecurityRole securityRole);
    SecurityRole findByRoleName(String roleName);
}
