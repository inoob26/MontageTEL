package com.tel.inoob.montagtel.Controller;

import android.content.Context;
import com.tel.inoob.montagtel.Model.Task;
import com.tel.inoob.montagtel.Model.TaskService;
import com.tel.inoob.montagtel.Tools.Deserialize;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

/**
 * @author inoob
 * @since 0.1
 */
public class TicketController {

    public List<Task> getListOfTask(final Context context,final int user_id, final String date){
        List<Task> listOfTask = new LinkedList<>();

        Deserialize deserialize = new Deserialize();
        DateFormat dateFormat = new SimpleDateFormat("yyyy.MM.dd");
        Date today = new Date();

            //listOfTask = deserialize.deserializeTask(user_id, date);
            listOfTask = deserialize.deserializeTask(user_id,dateFormat.format(today));

            if (listOfTask.isEmpty()) {
                listOfTask.add(new Task("Нет заявок на этот день"));
            }

        return listOfTask;
    }

    public List<TaskService> getListOfTaskService(final int task_id){
        List<TaskService> listOfTaskService = new LinkedList<>();

        Deserialize deserialize = new Deserialize();

        listOfTaskService = deserialize.deserializeTaskService(task_id);
        if(listOfTaskService.isEmpty()){
            listOfTaskService.add(new TaskService(0,"Нет задач для этого клиента"));
        }

        return listOfTaskService;
    }
}
