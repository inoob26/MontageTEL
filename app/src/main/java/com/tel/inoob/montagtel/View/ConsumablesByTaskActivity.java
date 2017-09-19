package com.tel.inoob.montagtel.View;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;
import com.tel.inoob.montagtel.Controller.RVConsumeAdapter;
import com.tel.inoob.montagtel.R;
import com.tel.inoob.montagtel.Tools.Deserialize;

/**
 * {@code ConsumablesByTaskActivity}class describe consumable activity.
 * keep list of consumable and fields with count of using consumables.
 *
 * @author inoob
 * @since 0.1
 */
public class ConsumablesByTaskActivity extends AppCompatActivity implements ConsumableOnClickUpdateListener{

    private static final int LAYOUT = R.layout.activity_consume_by_task;

    //Toolbar
    private ImageButton consume_toolbar_back_btn;
    private TextView consume_toolbar_task_id;
    private TextView consume_toolbar_client_id;
    private TextView consume_toolbar_time;

    //Content
    private RecyclerView recyclerView;

    private int task_id;
    private Deserialize deserialize;
    private RVConsumeAdapter adapter;

    private ConsumableOnClickUpdateListener listener;

    /**
     * addConsume_to_task_btn realize functional in the next version
     * get data from all fields of Consumable and send it to server
     */
    //private Button addConsume_to_task_btn;


    public void setListener(ConsumableOnClickUpdateListener listener) {
        this.listener = listener;
    }


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(LAYOUT);

        Bundle extras = getIntent().getExtras();
        //Toolbar
        consume_toolbar_back_btn = (ImageButton) findViewById(R.id.consume_toolbar_back_btn);
        consume_toolbar_task_id = (TextView) findViewById(R.id.consume_toolbar_task_id);
        consume_toolbar_client_id = (TextView) findViewById(R.id.consume_toolbar_client_id);
        consume_toolbar_time = (TextView) findViewById(R.id.consume_toolbar_time);

        consume_toolbar_back_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        String placeholder = "№"+extras.get("taskId").toString();

        consume_toolbar_task_id.setText(placeholder);
        consume_toolbar_client_id.setText(extras.get("client_id").toString());
        consume_toolbar_time.setText(extras.get("task_detail_time").toString());
        //Content
        //addConsume_to_task_btn = (Button) findViewById(R.id.addConsume_to_task_btn);
        task_id = (int) extras.get("taskId");
        initRecyclerView();
    }

    private void initRecyclerView(){
        recyclerView = (RecyclerView) findViewById(R.id.recycler_consume);

        LinearLayoutManager manager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(manager);

        deserialize = new Deserialize();

        adapter = new RVConsumeAdapter();
        adapter.setConsumableByTaskList(deserialize.deserializeConsumableByTask(task_id));
        adapter.setTaskId(task_id);
        adapter.setCloseListener(this);
        recyclerView.setAdapter(adapter);
    }

    /**
     * show info message and close Activity.
     */
    @Override
    public void sendDataUpdateAndCloseFrame() {
        finish();
        Toast toast = Toast.makeText(getApplicationContext(),"Расходник был добавлени",Toast.LENGTH_SHORT);
        toast.show();
    }
}
