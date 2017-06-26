package com.tel.inoob.montagtel.Model

import java.util.LinkedList


/**
 * `User` class keep information about user.

 * @author inoob
 * *
 * @since 0.1
 */
class User {

    var id: Int = 0
    var name: String? = ""
    var email: String? = ""
    var roleId: Int = 0
    private var roles: MutableList<Role> = LinkedList()

    fun getRoles(): List<Role> {
        return roles
    }

    fun setRoles(roles: MutableList<Role>) {
        this.roles = roles
    }

    fun addRole(role: Role) {
        this.roles.add(role)
    }
}
