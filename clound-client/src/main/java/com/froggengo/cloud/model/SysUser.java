package com.froggengo.cloud.model;

/**
 * @author fly
 * @create 2024-05-19-20:51
 **/
public class SysUser {
    private Integer id;
    private String username;
    private String password;

    public Integer getId() {return id;}

    public SysUser setId(Integer id) {
        this.id = id;
        return this;
    }

    public String getUsername() {return username;}

    public SysUser setUsername(String username) {
        this.username = username;
        return this;
    }

    public String getPassword() {return password;}

    public SysUser setPassword(String password) {
        this.password = password;
        return this;
    }

    @Override
    public String toString() {
        return "SysUser{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
