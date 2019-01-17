package com.shiro.sjwtm.dao;

import com.shiro.sjwtm.dao.entity.Permission;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PermissionDao extends CrudRepository<Permission, Long> {

}
