package com.shiro.sjwtm.service;

import com.shiro.sjwtm.controller.vo.UserVo;
import com.shiro.sjwtm.dao.UserDao;
import com.shiro.sjwtm.dao.entity.Permission;
import com.shiro.sjwtm.dao.entity.Role;
import com.shiro.sjwtm.dao.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service("userService")
public class UserService {

    @Autowired
    private UserDao userDao;

    public User getUserByUserName(String userName) {
        return userDao.findUserByUserName(userName);
    }

    public User getUserByUserNameAndPassWord(UserVo user) {
        return userDao.findUserByUserNameAndPassWord(user.getUserName(), user.getUserPwd());
    }

    public Set<Permission> getPermissionsByUserName(String userName) {
        List<Role> roles = userDao.findUserByUserName(userName).getRoles();
        Set<Permission> permissions = new HashSet<>();
        for (Role role :
                roles) {
            permissions.addAll(role.getPermissions());
        }
        return permissions;
    }

}
