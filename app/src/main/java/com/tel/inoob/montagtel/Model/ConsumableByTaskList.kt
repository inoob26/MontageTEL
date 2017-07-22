package com.tel.inoob.montagtel.Model

import java.util.*

/**
 * @author inoob
 * @since 0.1
 */
class ConsumableByTaskList {
    private var list: MutableList<ConsumableByTask> = LinkedList<ConsumableByTask>()

    fun addConsumableByTask(consumableByTask: ConsumableByTask){
        list.add(consumableByTask)
    }

    fun getList(): MutableList<ConsumableByTask> {
        return this.list
    }

    constructor(){}

}