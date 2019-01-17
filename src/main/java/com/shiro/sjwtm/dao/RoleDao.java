package com.shiro.sjwtm.dao;

import com.shiro.sjwtm.dao.entity.Role;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface RoleDao extends CrudRepository<Role, Long> {

}
