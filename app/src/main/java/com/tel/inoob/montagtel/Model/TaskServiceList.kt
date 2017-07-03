package com.tel.inoob.montagtel.Model

import java.util.ArrayList
import java.util.concurrent.Callable

/**
 * @{code TaskServiceList class} keep ArrayList of TaskService.
 *
 * @author inoob
 * @since 0.1
 */
class TaskServiceList {
    private var list: MutableList<TaskService> = ArrayList()

    fun addTaskService(taskService: TaskService) {
        list.add(taskService)
    }

    fun getList(): List<TaskService> {
        return list
    }

    fun setList(list: MutableList<TaskService>) {
        this.list = list
    }
}
