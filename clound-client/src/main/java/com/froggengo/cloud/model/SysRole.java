package com.froggengo.cloud.model;

/**
 * @author fly
 * @create 2024-05-20-21:32
 **/
public class SysRole {
    private Integer id;
    private String roleName;
    private String roleDesc;

    public Integer getId() {return id;}

    public SysRole setId(Integer id) {
        this.id = id;
        return this;
    }

    public String getRoleName() {return roleName;}

    public SysRole setRoleName(String roleName) {
        this.roleName = roleName;
        return this;
    }

    public String getRoleDesc() {return roleDesc;}

    public SysRole setRoleDesc(String roleDesc) {
        this.roleDesc = roleDesc;
        return this;
    }
}
