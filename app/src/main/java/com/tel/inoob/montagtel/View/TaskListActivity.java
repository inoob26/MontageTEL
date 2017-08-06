package com.tel.inoob.montagtel.View;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentManager;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;
import com.tel.inoob.montagtel.MainScreen;
import com.tel.inoob.montagtel.R;

public class TaskListActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private static final int LAYOUT = R.layout.activity_task_list;

    private int user_id;
    private String dateDDMM;
    private String dateMMDD;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(LAYOUT);

        Bundle extras = getIntent().getExtras();
        user_id = (int) extras.get("userId");
        dateDDMM = extras.getString("dateDDMM");
        dateMMDD = extras.getString("dateMMDD");

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("MontageTEL");
        setSupportActionBar(toolbar);


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);

        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        reloadGF();
    }

    private void reloadGF(){
        GraphicFragment graphicFragment = new GraphicFragment();
        Bundle args = new Bundle();
        args.putInt("param1",user_id);
        args.putCharSequence("dateDDMM", dateDDMM);
        args.putCharSequence("dateMMDD", dateMMDD);
        graphicFragment.setArguments(args);
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.content_task_list,graphicFragment).commit();
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        //getMenuInflater().inflate(R.menu.task_list, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.graphic) {
            GraphicFragment graphicFragment = new GraphicFragment();
            Bundle args = new Bundle();
            args.putInt("param1",user_id);
            args.putCharSequence("dateDDMM", dateDDMM);
            args.putCharSequence("dateMMDD", dateMMDD);
            graphicFragment.setArguments(args);
            FragmentManager fragmentManager = getSupportFragmentManager();
            fragmentManager.beginTransaction().replace(R.id.content_task_list,graphicFragment).commit();

        } else if (id == R.id.calendar) {
            CalendarFragment calendarFragment = new CalendarFragment();
            FragmentManager fragmentManager = getSupportFragmentManager();
            fragmentManager.beginTransaction().replace(R.id.content_task_list,calendarFragment).commit();
        } else if (id == R.id.contacts) {
            ContactFragment contactFragment = new ContactFragment();
            FragmentManager fragmentManager = getSupportFragmentManager();
            fragmentManager.beginTransaction().replace(R.id.content_task_list,contactFragment).commit();

        } else if (id == R.id.new_account) {
            Toast.makeText(this,"NEW_ACCOUNT",Toast.LENGTH_SHORT).show();
        } else if (id == R.id.logout) {
            Intent main = new Intent(TaskListActivity.this, MainScreen.class);
            startActivity(main);
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}