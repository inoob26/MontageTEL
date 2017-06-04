package com.tel.inoob.montagtel.Activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import com.tel.inoob.montagtel.Model.Task;
import com.tel.inoob.montagtel.R;
import com.tel.inoob.montagtel.Tools.Deserialize;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.LinkedList;
import java.util.List;

/**
 * This is a second screen for Ticket list.
 *
 * @author inoob
 */
public class TicketActivity extends AppCompatActivity {
    private List<Task> listOfTask;
    private RecyclerView recyclerView;

    private void onLoad(){
        Bundle extras = getIntent().getExtras();

        Deserialize deserialize = new Deserialize();

        DateFormat dateFormat = new SimpleDateFormat("yyyy.MM.dd");
        Calendar cal = Calendar.getInstance();

        listOfTask = new LinkedList<>();
        listOfTask = deserialize.deserializeTask((Integer) extras.get("userId"),dateFormat.format(cal.getTime()));
        //listOfTask = deserialize.deserializeTask((Integer) extras.get("userId"),"2017.05.25");
        if(listOfTask.isEmpty()){
            listOfTask.add(new Task("Нет заявок на этот день"));
        }
    }

    private void initializeAdapter(){
        RVTicketAdapter adapter = new RVTicketAdapter(listOfTask);
        recyclerView.setAdapter(adapter);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_ticket);
        setContentView(R.layout.activity_recycle_view);
        recyclerView = (RecyclerView) findViewById(R.id.rv);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);

        onLoad();
        initializeAdapter();
    }
}
