package com.tel.inoob.montagtel.Model

/**
 * @{code TaskService class} keep information about each task .
 * it means we store information how many service client want and how much he will pay.
 */
class TaskService {
    var id: Int? = 0
    var taskId: Int? = 0
    var isBreak : Boolean? = false
    var isCompleted: Boolean? = false
    var isPlan: Boolean? = false
    var quantity: Byte? = 0
    var serviceTemplateId: Int? = 0
    var serviceName: String? = null
    var scladId: Int? = 0
    var tarifName: String? = null
    var price: Double? = 0.0
    var tarifType: Byte? = 0
    var cost: Double? = 0.0

    constructor() {}

    constructor(id: Int?, taskId: Int?, isBreak: Boolean? ,
                isCompleted: Boolean?, isPlan: Boolean?,
                quantity: Byte?, serviceTemplateId: Int?,
                //comment: String?,
                serviceName: String?,
                scladId: Int?, tarifName: String?, price: Double?,
                tarifType: Byte?, cost: Double?) {
        this.id = id
        this.taskId = taskId
        this.isBreak = isBreak
        this.isCompleted = isCompleted
        this.isPlan = isPlan
        this.quantity = quantity
        this.serviceTemplateId = serviceTemplateId
        this.serviceName = serviceName
        this.scladId = scladId
        this.tarifName = tarifName
        this.price = price
        this.tarifType = tarifType
        this.cost = cost
    }

    constructor(id: Int, serviceName: String){
        this.id = id
        this.serviceName = serviceName
    }
}
