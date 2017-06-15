package com.tel.inoob.montagtel.Activities;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import com.tel.inoob.montagtel.R;

public class ContactActivity extends AppCompatActivity {

    /**
     * Phone numbers.
     */
    private final String stp = "84957874207";
    private final String secretary = "84957874200";
    private final String accountManagers = "84956492000";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        initButtonsListener();
    }

    /**
     * Initialize button event listener.
     * when user click buttons phone will dial.
     */
    private void initButtonsListener(){
        Button stp_btn = (Button) findViewById(R.id.stp_btn);
        Button secretary_btn = (Button) findViewById(R.id.secretary_btn);
        Button account_btn = (Button) findViewById(R.id.account_btn);

        stp_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dial(stp);
            }
        });

        secretary_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dial(secretary);
            }
        });

        account_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dial(accountManagers);
            }
        });
    }

    /**
     * Open dial app.
     * @param number phone number.
     */
    private void dial(final String number){
        Intent dialNumber = new Intent("android.intent.action.DIAL", Uri.parse("tel:"+number));
        startActivity(dialNumber);
    }

}
