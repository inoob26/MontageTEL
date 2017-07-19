package com.tel.inoob.montagtel.Model

/**
 * Keep information about additional service for user.
 *
 * @author inoob
 * @since 0.1
 */
class ServiceAdvans {
    var id: Int = -1
    var serviceName: String = ""
    var isCompleted: Boolean = false
    var quantity: Int = -1
    var tarifId: Int = 0
    var price: Double = 0.0

    constructor(){}

    constructor(id: Int?, serviceName: String?, isCompleted: Boolean?, quantity: Int?,tarifId: Int?, price: Double?){
        this.id = id ?: -1
        this.serviceName = serviceName ?: ""
        this.isCompleted = isCompleted ?: false
        this.quantity = quantity ?: 0
        this.tarifId = tarifId ?: 0
        this.price = price ?: 0.0
    }
}