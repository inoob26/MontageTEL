package com.tel.inoob.montagtel.Controller;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import com.tel.inoob.montagtel.Model.Error;
import com.tel.inoob.montagtel.Model.Task;
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

            listOfTask = new LinkedList<>();
            //listOfTask = deserialize.deserializeTask(user_id, date);
            listOfTask = deserialize.deserializeTask(user_id,dateFormat.format(today));

            if (listOfTask.isEmpty()) {
                listOfTask.add(new Task("Нет заявок на этот день"));
            }

        return listOfTask;
    }
}
