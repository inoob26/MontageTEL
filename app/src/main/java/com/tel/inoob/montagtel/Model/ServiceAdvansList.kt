package com.tel.inoob.montagtel.Model

import java.util.*

/**
 * @author inoob
 * @since 0.1
 */
class ServiceAdvansList {
    private var list: MutableList<ServiceAdvans> = LinkedList<ServiceAdvans>()

    fun addServiceAdvans(serviceAdvans: ServiceAdvans){
        list.add(serviceAdvans)
    }

    fun getList() : MutableList<ServiceAdvans> {
        return this.list
    }

    fun setList(list: MutableList<ServiceAdvans>) {
        this.list = list
    }
}