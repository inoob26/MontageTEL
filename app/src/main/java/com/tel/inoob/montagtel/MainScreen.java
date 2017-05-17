package com.tel.inoob.montagtel;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import com.tel.inoob.montagtel.Tools.WebClient;

/**
 * This is Login Screen
 *
 * @author inoob
 * @since 0.1
 */
public class MainScreen extends AppCompatActivity {

    /**
     * Sign button
     */
    private Button sign_in;
    private EditText login;
    private EditText password;
    private TextView error;

    /**
     * This method initialize properties and events.
     */
    private void init(){
        login = (EditText)findViewById(R.id.login);
        password = (EditText)findViewById(R.id.password);
        error = (TextView)findViewById(R.id.wrongData);
        sign_in = (Button)findViewById(R.id.Sign_in);
        sign_in.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent ticket = new Intent(MainScreen.this,TicketActivity.class);
                WebClient webClient = new WebClient("http://10.192.25.4:9190/mobile/login?login="
                        + login.getText().toString()
                        + "&password=" + password.getText().toString() ,error);
                webClient.getJSON();
                //System.out.println(error.getText().toString().equals("пара логин пароль не верны"));
                String string = error.getText().toString();
                System.out.println("here" + string);
                if(string.equals("пара логин пароль не верны")) {
                    error.setVisibility(View.VISIBLE);
                } else {
                    //ticket.putExtra("data",string);
                    //startActivity(ticket);
                }
            }
        });
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_screen);
        init();
    }
}
