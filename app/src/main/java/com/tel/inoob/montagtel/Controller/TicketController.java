package com.tel.inoob.montagtel.Controller;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import com.tel.inoob.montagtel.Activities.TicketActivity;
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

        String json = deserialize.getJson(Deserialize.D_TASK_PATH + user_id +
                "&date=" + date);

        Error error = deserialize.deserializeError(json);

        //handle error
        if(error.getErrorCode() != -1) {
            listOfTask = new LinkedList<>();
            listOfTask.add(new Task("Ошибка " + error.getErrorMsg()));

            AlertDialog.Builder builder = new AlertDialog.Builder(context);

            builder.setMessage(error.getErrorMsg())
                    .setTitle("Ой, что то пошло не так");
            builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.dismiss();
                }
            });

            AlertDialog dialog = builder.create();
            dialog.show();


        } else {
            listOfTask = new LinkedList<>();
            listOfTask = deserialize.deserializeTask(user_id, date);
            //listOfTask = deserialize.deserializeTask((Integer) extras.get("userId"),dateFormat.format(today));

            if (listOfTask.isEmpty()) {
                listOfTask.add(new Task("Нет заявок на этот день"));
            }
        }

        return listOfTask;
    }
}
