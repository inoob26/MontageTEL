package com.tel.inoob.montagtel.Activities;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;
import com.tel.inoob.montagtel.Controller.RVTaskSeviceListAdapter;
import com.tel.inoob.montagtel.Controller.TicketController;
import com.tel.inoob.montagtel.Model.TaskService;
import com.tel.inoob.montagtel.R;
import com.tel.inoob.montagtel.Tools.Deserialize;

import java.util.List;

public class DetailTicketActivity extends AppCompatActivity {
    /**
     * Layout.
     */
    private static final int LAYOUT = R.layout.activity_detail_ticket;

    private TicketController controller;
    private TextView clientFio;
    private TextView clientPhone;
    private TextView taskAddress;
    private TextView serviceInfo;
    private TextView task_id;
    private TextView client_id;
    private RecyclerView recyclerView_task_service_list;

    private void onLoad() {
        task_id = (TextView) findViewById(R.id.task_detail_id);
        client_id = (TextView) findViewById(R.id.task_detail_client_id);
        clientFio = (TextView) findViewById(R.id.ticket_detail_client_fio);
        clientPhone = (TextView) findViewById(R.id.ticket_detail_client_phone);
        taskAddress = (TextView) findViewById(R.id.ticket_detail_task_address);
        serviceInfo = (TextView) findViewById(R.id.ticket_detail_service_info);

        Bundle extras = getIntent().getExtras();

        clientFio.setText((String)extras.get("clientFio"));
        clientPhone.setText((String)extras.get("clientPhone"));
        taskAddress.setText((String)extras.get("taskAddress"));
        serviceInfo.setText((String)extras.get("serviceInfo"));
        task_id.setText("№" + extras.get("task_id").toString());
        client_id.setText(extras.get("client_id").toString());

        getInformation((int) extras.get("task_id"));
        initializeRecycleView((int) extras.get("task_id"));
    }

    private void getInformation(final int id){
        Deserialize deserialize = new Deserialize();
        deserialize.deserializeTaskService(id);
    }

    private void initializeRecycleView(final int task_id){
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView_task_service_list = (RecyclerView) findViewById(R.id.recycle_view_task_service_list);
        recyclerView_task_service_list.setLayoutManager(linearLayoutManager);

        controller = new TicketController();
        List<TaskService> list = controller.getListOfTaskService(task_id);

        for (TaskService task: list) {
            System.out.println("Service NAME: " + task.getServiceName() + " PRICE: " + task.getPrice());
        }

        RVTaskSeviceListAdapter adapter = new RVTaskSeviceListAdapter(list);
        recyclerView_task_service_list.setAdapter(adapter);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(LAYOUT);
        onLoad();
        initListeners();
    }

    private void initListeners() {
        /**
         * dial when click on phone number.
         */
        clientPhone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent dialNumber = new Intent("android.intent.action.DIAL", Uri.parse("tel:8"+clientPhone.getText()));
                startActivity(dialNumber);
            }
        });
    }
}
