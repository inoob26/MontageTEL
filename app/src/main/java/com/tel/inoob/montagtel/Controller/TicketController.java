package com.tel.inoob.montagtel.Controller;


import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.ContextThemeWrapper;
import com.tel.inoob.montagtel.MainScreen;
import com.tel.inoob.montagtel.Model.Error;
import com.tel.inoob.montagtel.Model.Task;
import com.tel.inoob.montagtel.Model.TaskService;
import com.tel.inoob.montagtel.Tools.Deserialize;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import com.tel.inoob.montagtel.R;

/**
 * @author inoob
 * @since 0.1
 */
public class TicketController {

    /**
     *
     *
     * @param user_id user_id
     * @param context context for Alarm dialog or
     * @return list of Task
     */
    public List<Task> getListOfTask(final int user_id, Context context) {
        List<Task> listOfTask = new LinkedList<>();

        Deserialize deserialize = new Deserialize();
        DateFormat dateFormat = new SimpleDateFormat("yyyy.MM.dd");
        Date today = new Date();

        try {
            today = dateFormat.parse("2017.07.01");
        } catch (ParseException e) {
            e.printStackTrace();
        }

        Error error = deserialize.deserializeErrorForTask(user_id, dateFormat.format(today));

        checkErrorLoginCode(error,context);

        listOfTask = deserialize.deserializeTask(user_id, dateFormat.format(today));

        if (listOfTask.isEmpty()) {
            listOfTask.add(new Task("Нет заявок на этот день"));
        }

        return listOfTask;
    }

    public List<TaskService> getListOfTaskService(final int task_id,Context context){
        List<TaskService> listOfTaskService = new LinkedList<>();

        Deserialize deserialize = new Deserialize();

        Error error = deserialize.deserializeErrorTaskService(task_id);

        checkErrorLoginCode(error,context);

        listOfTaskService = deserialize.deserializeTaskService(task_id);
        if(listOfTaskService.isEmpty()){
            listOfTaskService.add(new TaskService(0,"Нет задач для этого клиента"));
        }

        return listOfTaskService;
    }

    private void checkErrorLoginCode(Error error, Context context){
        if(error.getToLogin() == 0){
            showErrorMessage(context,error.getErrorMsg());
        } else if (error.getToLogin() == 1){
            showLoginForm(context);
        }
    }

    /**
     * Show alert dialog.
     *
     * @param context context.
     * @param msg error message.
     */
    private void showErrorMessage(Context context, final String msg){
        AlertDialog.Builder builder = new AlertDialog.Builder(new ContextThemeWrapper(context, R.style.myDialog));
        builder.setMessage(msg)
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        // FIRE ZE MISSILES!
                        dialog.dismiss();
                    }
                });
        // Create the AlertDialog object and return it
        builder.create();
        builder.show();
    }

    /**
     * Show Login Form.
     *
     * @param context context.
     */
    private void showLoginForm(Context context){
        Intent loginForm = new Intent(context, MainScreen.class);
        context.startActivity(loginForm);
    }

}
