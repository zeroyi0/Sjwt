package com.shiro.sjwtm.dao.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "s_user")
@Getter @Setter
public class User {

    @Id
    @GeneratedValue
    private Long uid;

    @Column(name = "s_user_name", unique = true)
    private String userName;

    @Column(name = "s_user_pwd")
    private String passWord;

    @ManyToMany(fetch = FetchType.EAGER)
    @JsonIgnoreProperties(value = { "users" })
    @JoinTable(name = "s_user_role", joinColumns = { @JoinColumn(name = "uid") }, inverseJoinColumns = {
            @JoinColumn(name = "rid") })
    private List<Role> roles;

    public User() {}

    public User(String userName, String passWord) {
        this.userName = userName;
        this.passWord = passWord;
    }
}
