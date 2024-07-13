package com.llu.entity;

import java.io.Serializable;

/**
 * (User)实体类
 *
 * @author 11u
 * @since 2024-07-12 22:42:54
 */
public class User implements Serializable {
    private static final long serialVersionUID = 722299428864284780L;
    
    private Integer id;
    
    private String username;
    
    private String password;
    
    private String loginname;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getLoginname() {
        return loginname;
    }

    public void setLoginname(String loginname) {
        this.loginname = loginname;
    }

}

