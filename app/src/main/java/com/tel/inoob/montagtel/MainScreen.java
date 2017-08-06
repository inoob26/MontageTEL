package com.tel.inoob.montagtel;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Resources;
import android.app.AlertDialog;
//import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.ContextThemeWrapper;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import com.tel.inoob.montagtel.Model.User;
import com.tel.inoob.montagtel.View.TaskListActivity;
import com.tel.inoob.montagtel.Tools.Deserialize;
import com.tel.inoob.montagtel.Model.Error;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;


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
    private TextView errorMsg;

    /**
     * Layout.
     */
    private static final int LAYOUT = R.layout.activity_main_screen;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(LAYOUT);
        login = (EditText)findViewById(R.id.login);
        password = (EditText)findViewById(R.id.password);
        errorMsg = (TextView)findViewById(R.id.wrongData);

        sign_in = (Button)findViewById(R.id.Sign_in);
        sign_in.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final Intent ticket = new Intent(MainScreen.this, TaskListActivity.class);
                Deserialize deserialize = new Deserialize();

                //int response = deserialize.deserializeLoginPassword(login.getText().toString(), password.getText().toString());
                Error error = deserialize.deserializeLoginPassword(login.getText().toString(), password.getText().toString());


                if (error.getErrorCode() < 11 && error.getErrorCode() > 0){
                    errorMsg.setText(error.getErrorMsg());
                    errorMsg.setVisibility(View.VISIBLE);
                } else {

                    DateFormat dateFormat1 = new SimpleDateFormat("dd.MM");
                    DateFormat dateFormat2 = new SimpleDateFormat("MM.dd");
                    final Date today = new Date();

                    User user = deserialize.deserializeUser(login.getText().toString(), password.getText().toString());

                    //int result = deserialize.deserializeUser(login.getText().toString(), password.getText().toString());
                    ticket.putExtra("userId",user.getId());
                    ticket.putExtra("userName",user.getName());
                    ticket.putExtra("dateDDMM", dateFormat1.format(today));
                    ticket.putExtra("dateMMDD", dateFormat2.format(today));
                    startActivity(ticket);
                }
            }
        });
    }

    @Override
    protected void onStop() {
        super.onStop();
        login.setText("");
        password.setText("");
    }
}
