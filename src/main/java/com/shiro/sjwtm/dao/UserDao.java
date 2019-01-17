package com.shiro.sjwtm.dao;

import com.shiro.sjwtm.dao.entity.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserDao extends CrudRepository<User, Long> {

    public User findUserByUserName(String userName);

    public User findUserByUserNameAndPassWord(String userName, String userPwd);

}
