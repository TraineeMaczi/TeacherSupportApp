package com.nokia.teachersupport.roles;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;

@Component
public class LoadMyRoles implements ApplicationListener<ApplicationReadyEvent> {

    private RoleRepo MyRoleRepoInstance;

    @Autowired
    public LoadMyRoles(RoleRepo rRepo) {
        this.MyRoleRepoInstance = rRepo;
    }

    @Override
    public void onApplicationEvent(ApplicationReadyEvent applicationReadyEvent) {
 if (MyRoleRepoInstance.findByRoleName("ROLE_ADMIN") == null) {
            SecutityRole admin=new SecutityRole();
            admin.setRoleName("ROLE_ADMIN");
            MyRoleRepoInstance.save(admin);
  }

 if (MyRoleRepoInstance.findByRoleName("ROLE_USER") == null) {
            SecutityRole user=new SecutityRole();
            user.setRoleName("ROLE_USER");
            MyRoleRepoInstance.save(user);
 }

    }
}
