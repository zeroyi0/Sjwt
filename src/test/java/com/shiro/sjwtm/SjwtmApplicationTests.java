package com.shiro.sjwtm;

import com.shiro.sjwtm.dao.PermissionDao;
import com.shiro.sjwtm.dao.RoleDao;
import com.shiro.sjwtm.dao.UserDao;
import com.shiro.sjwtm.dao.entity.Permission;
import com.shiro.sjwtm.dao.entity.Role;
import com.shiro.sjwtm.dao.entity.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SjwtmApplicationTests {

    @Autowired
    UserDao userDao;

    @Autowired
    RoleDao roleDao;

    @Autowired
    PermissionDao permissionDao;

    @Test
    public void contextLoads() {
        Permission guestRead = new Permission("guest:read");
        Permission guestUpdate = new Permission("guest:update");
        Permission adminRead = new Permission("admin:read");
        Permission adminUpdate = new Permission("admin:update");

        permissionDao.save(guestRead);
        permissionDao.save(guestUpdate);
        permissionDao.save(adminRead);
        permissionDao.save(adminUpdate);

        Role admin = new Role("admin", Arrays.asList(adminRead, adminUpdate));
        Role guest = new Role("guest", Arrays.asList(guestRead, guestUpdate));

        roleDao.save(admin);
        roleDao.save(guest);

        User adminUser = new User();
        adminUser.setUserName("admin");
        adminUser.setPassWord("admin");
        adminUser.setRoles(Arrays.asList(admin, guest));

        User guestUser = new User();
        guestUser.setUserName("guest");
        guestUser.setPassWord("guest");
        guestUser.setRoles(Arrays.asList(guest));

        userDao.save(guestUser);
        userDao.save(adminUser);

    }

}

