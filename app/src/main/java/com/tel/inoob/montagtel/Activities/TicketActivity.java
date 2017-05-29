package com.tel.inoob.montagtel.Activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import com.tel.inoob.montagtel.R;
import com.tel.inoob.montagtel.Tools.Deserialize;

/**
 * This is a second screen for Ticket list.
 *
 * @author inoob
 */
public class TicketActivity extends AppCompatActivity {
    private TextView text;

    private void onLoad(){
        Bundle extras = getIntent().getExtras();
        text = (TextView)findViewById(R.id.text);
        text.setText(String.valueOf(extras.get("userId")));

        Deserialize deserialize = new Deserialize();
        deserialize.deserializeTask((Integer) extras.get("userId"),"2017.05.25");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ticket);
        onLoad();
    }
}
