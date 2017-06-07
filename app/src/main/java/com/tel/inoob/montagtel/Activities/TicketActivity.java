package com.tel.inoob.montagtel.Activities;

import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
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
    /**
     * Layout.
     */
    private static final int LAYOUT = R.layout.activity_recycle_view;
    private List<Task> listOfTask;

    private Toolbar toolbar;
    private DrawerLayout drawerLayout;

    private RecyclerView recyclerView;

    private void onLoad(){
        Bundle extras = getIntent().getExtras();

        Deserialize deserialize = new Deserialize();

        DateFormat dateFormat = new SimpleDateFormat("yyyy.MM.dd");
        Calendar cal = Calendar.getInstance();

        listOfTask = new LinkedList<>();
        //listOfTask = deserialize.deserializeTask((Integer) extras.get("userId"),"2017.05.25");
        listOfTask = deserialize.deserializeTask((Integer) extras.get("userId"),dateFormat.format(cal.getTime()));

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
        setTheme(R.style.AppDefaultTheme);
        //setContentView(R.layout.activity_ticket);
        setContentView(LAYOUT);
        recyclerView = (RecyclerView) findViewById(R.id.rv);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);


        onLoad();
        initializeAdapter();
        initToolbar();
        initNavigationView();
    }

    private void initNavigationView() {
        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
    }

    private void initToolbar() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);

        setSupportActionBar(toolbar);
        toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                return false;
            }
        });

        toolbar.inflateMenu(R.menu.menu);
    }
}
