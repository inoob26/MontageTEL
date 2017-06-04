package com.tel.inoob.montagtel.Model

/**
 * `Error ` class keep information about error

 * @author inoob
 * *
 * @since 0.1
 */
class Error {

    var errorCode: Int = 0
    var errorMsg: String? = null

    init {
        this.errorCode = -1
        this.errorMsg = "It is OK"
    }
}
