package com.tel.inoob.montagtel.Model;

import java.util.LinkedList;
import java.util.List;

/**
 * {@code User} class keep information about user.
 *
 * @author inoob
 * @since 0.1
 */
public class User {

    private int id;
    private String name;
    private int roleId;
    private List<Role> roles = new LinkedList<>();


    /**
     * no arg constructor.
     *
     * for serialize/deserialize
     */
    public User(){

    }

    //getters & setters

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getRoleId() {
        return roleId;
    }

    public void setRoleId(int roleId) {
        this.roleId = roleId;
    }

    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }

    public void addRole(Role role){
        this.roles.add(role);
    }
}
