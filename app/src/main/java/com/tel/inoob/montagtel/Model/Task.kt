package com.tel.inoob.montagtel.Model

/**
 * {@code Task} keep information about task.
 *
 * @author inoob
 * @since 0.1
 */
class Task {
    var id : Int = 0;
    var clientId : Int = 0;
    var firstName : String = "";
    var secondName : String = "";
    var middleName : String = "";
    var serviceInfo : String = "";
    var clientPhone : String = "";
    var flatNumber : String = "";
    var objectName : String = "";

    constructor(id: Int,
                clientId: Int,
                firstName: String,
                secondName: String,
                middleName: String,
                serviceInfo: String,
                clientPhone: String,
                flatNumber: String,
                objectName: String
    ) {
        this.id = id
        this.clientId = clientId
        this.firstName = firstName
        this.secondName = secondName
        this.middleName = middleName
        this.serviceInfo = serviceInfo
        this.clientPhone = clientPhone
        this.flatNumber = flatNumber
        this.objectName = objectName
    }

    constructor(){}

}