package com.tel.inoob.montagtel;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import com.tel.inoob.montagtel.Tools.WebClient;

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
        text.setText((CharSequence) extras.get("data"));

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ticket);
        onLoad();
    }
}
