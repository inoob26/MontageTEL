package com.tel.inoob.montagtel.Model;

import java.util.LinkedList;
import java.util.List;

/**
 * {@code TicketList} keep list of Tasks.
 *
 * @author inoob
 * @since 0.1
 * @see com.tel.inoob.montagtel.Model.TicketList
 */
public class TicketList {

    private List<Task> taskList = new LinkedList<>();

    public TicketList(){

    }

    public void addTask(Task task){
        taskList.add(task);
    }

    public List<Task> getTaskList() {
        return taskList;
    }

    public void setTaskList(List<Task> taskList) {
        this.taskList = taskList;
    }
}
