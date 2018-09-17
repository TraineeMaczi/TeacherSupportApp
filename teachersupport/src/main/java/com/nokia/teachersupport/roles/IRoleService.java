package com.nokia.teachersupport.roles;

public interface IRoleService {
    void saveSecurityRole(SecurityRole securityRole);
    SecurityRole findByRoleName(String roleName);
}
