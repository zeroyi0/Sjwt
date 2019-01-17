package com.shiro.sjwtm.dao.entity;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter @Setter
@Table(name = "s_permission")
public class Permission {

    @Id
    @GeneratedValue
    private Long pid;

    @Column(name = "s_permission_discrible")
    private String permission;

    public Permission() {}

    public Permission(String permission) {
        this.permission = permission;
    }
}
