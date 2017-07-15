package com.tel.inoob.montagtel;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import com.tel.inoob.montagtel.View.TaskListActivity;
import com.tel.inoob.montagtel.Tools.Deserialize;


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
     * Layout.
     */
    private static final int LAYOUT = R.layout.activity_main_screen;


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
                /*
                 * show progress dialog when login.
                 */
                ProgressDialog pd = new ProgressDialog(MainScreen.this);
                pd.setTitle("Connect to Server");
                pd.setMessage("Loading...");
                pd.show();


                final Intent ticket = new Intent(MainScreen.this, TaskListActivity.class);
                Deserialize deserialize = new Deserialize();
                int response = deserialize.deserializeLoginPasswod(login.getText().toString(), password.getText().toString());

                pd.cancel();


                if(response > 100){
                    error.setVisibility(View.INVISIBLE);
                    ticket.putExtra("userId",response);
                    startActivity(ticket);

                } else {
                    error.setVisibility(View.VISIBLE);
                }
            }
        });
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(LAYOUT);
        init();
    }
}
