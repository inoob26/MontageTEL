package com.tel.inoob.montagtel.Model

import java.util.Date

/**
 * {@code Task} keep information about task.
 *
 * @author inoob
 * @since 0.1
 */
class Task {
    var id : Int = 0
    var clientId : Int = 0

    /**
     * не выполнена - 1 - red color
     * выполнена - 2 - green color
     * отказ - 101 - red color
     * резерв - 11 - orange
     * перенос  - 200
     */
    var status : Int = 0
    var firstName : String = ""
    var secondName : String = ""
    var middleName : String = ""
    var clientPhone : String = ""
    var flatNumber : String = ""
    var objectName : String = ""
    var startDateTime : String = ""

    constructor(id: Int?,
                clientId: Int?,
                status: Int?,
                firstName: String?,
                secondName: String?,
                middleName: String?,
                clientPhone: String?,
                flatNumber: String?,
                objectName: String?,
                startDateTime: String?
    ) {
        this.id = id ?: 0
        this.clientId = clientId ?: 0
        this.status = status ?: 0
        this.firstName = firstName ?: ""
        this.secondName = secondName ?: ""
        this.middleName = middleName ?: ""
        this.clientPhone = clientPhone ?: ""
        this.flatNumber = flatNumber ?: ""
        this.objectName = objectName ?: ""
        this.startDateTime = startDateTime ?: ""
    }

    constructor(){}

    constructor(firstName : String?){
        this.firstName = firstName ?: "firstName"
    }

    //for check error on TaskListDeserialize
    constructor(status: Int?, serviceInfo: String?) {
        this.status = status ?: 0
    }

}