package com.tel.inoob.montagtel.Activities;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;
import com.tel.inoob.montagtel.Model.Task;
import com.tel.inoob.montagtel.R;
import com.tel.inoob.montagtel.Tools.Deserialize;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

/**
 * This is a second screen for Ticket list.
 *
 * @author inoob
 */
public class TicketActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{
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
        Date today = new Date();


        listOfTask = new LinkedList<>();
        //listOfTask = deserialize.deserializeTask((Integer) extras.get("userId"),"2017.06.13");
        listOfTask = deserialize.deserializeTask((Integer) extras.get("userId"),dateFormat.format(today));

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
        setContentView(LAYOUT);
        recyclerView = (RecyclerView) findViewById(R.id.rv);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);


        onLoad();
        initializeAdapter();
        initToolbar();
        initNavigationView();
    }


    /**
     * Initialize menu listener.
     */
    private void initNavigationView() {
        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);

        //initialize listener
        NavigationView navigationView = (NavigationView) findViewById(R.id.navigation);
        navigationView.setNavigationItemSelectedListener(this);
    }

    private void initToolbar() {
        //initialize toolbar as ActionBar for back dependency with android less 21 API
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                return false;
            }
        });
    }



    /**
     * Initialize menu for NavigationView.
     *
     * @param menu get menu.
     * @return true.
     */
    @Override
    public boolean onCreateOptionsMenu(final Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    /**
     * Navigation menu handler.
     *
     * @param item get item.
     * @return .
     */
    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        final int id = item.getItemId();
        if(id == R.id.graphic){
            //Handle main
            Toast.makeText(this,"GRAPHIC",Toast.LENGTH_SHORT).show();

        } else if(id == R.id.calendar){
            Toast.makeText(this,"CALENDAR",Toast.LENGTH_SHORT).show();
        } else if(id == R.id.contacts){
            //Handle contact
            //Toast.makeText(this,"CONTACTS",Toast.LENGTH_SHORT).show();
            Intent contact = new Intent(TicketActivity.this,ContactActivity.class);
            startActivity(contact);
        } else if(id == R.id.new_account) {
            Toast.makeText(this,"NEW_ACCOUNT",Toast.LENGTH_SHORT).show();
        } else if(id == R.id.logout){
            //Handle logout
            Toast.makeText(this,"LOGOUT",Toast.LENGTH_SHORT).show();
        }

        DrawerLayout drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawerLayout.closeDrawer(GravityCompat.START);

        return true;
    }
}
