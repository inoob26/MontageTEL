package com.tel.inoob.montagtel.Controller;

import android.content.Context;
import android.content.Intent;
import com.tel.inoob.montagtel.View.ConsumableOnClickUpdateListener;
import com.tel.inoob.montagtel.View.ConsumablesByTaskActivity;

public class ConsumableByTaskController {

    public static final ConsumableByTaskController INSTANCE = new ConsumableByTaskController();
    private Context context;
    private int task_id;
    private int client_id;
    private String task_detail_time;

    private ConsumableOnClickUpdateListener updateListener;

    public static ConsumableByTaskController getINSTANCE() {
        return INSTANCE;
    }

    public void showActivity(){
        Intent consumableActivity = new Intent(context, ConsumablesByTaskActivity.class);

        consumableActivity.putExtra("taskId",task_id);
        consumableActivity.putExtra("client_id",client_id);
        consumableActivity.putExtra("task_detail_time",task_detail_time);

        consumableActivity.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

        context.startActivity(consumableActivity);
    }

    public ConsumableOnClickUpdateListener getUpdateListener() {
        return updateListener;
    }

    public void setUpdateListener(ConsumableOnClickUpdateListener updateListener) {
        this.updateListener = updateListener;
    }

    public void setContext(Context context) {
        this.context = context;
    }

    public void setTask_id(int task_id) {
        this.task_id = task_id;
    }

    public void setClient_id(int client_id) {
        this.client_id = client_id;
    }

    public void setTask_detail_time(String task_detail_time) {
        this.task_detail_time = task_detail_time;
    }
}
