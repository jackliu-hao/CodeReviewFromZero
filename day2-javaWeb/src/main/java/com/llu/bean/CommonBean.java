package com.llu.bean;



/**
 * @author jackliu  Email:
 * @description: 公共类
 * @Version
 * @create 2024-07-07 19:43
 */


public abstract class CommonBean {
    private String name;
    private String loginName;
    private String password;
    private String email;
    private String phone;
    // 权限字段  0 标识普通用户， 1 标识管理员
    private Integer role;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Integer getRole() {
        return role;
    }

    public void setRole(Integer role) {
        this.role = role;
    }
}
