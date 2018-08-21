package com.nokia.teachersupport.roles;

public interface IRoleService {
    void save(SecutityRole secutityRole);
    SecutityRole findByRoleName(String roleName);
}
