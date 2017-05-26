package com.tel.inoob.montagtel.Model;

/**
 * {@code Role} class keep information about Role.
 *
 * @author inoob
 * @since 0.1
 */
public class Role {
    private int id;
    private String name;

    /**
     * no arg constructor.
     *
     * for serialize/deserialize
     */
    public Role() {

    }

    public Role(int id, String name) {
        this.id = id;
        this.name = name;
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
}
