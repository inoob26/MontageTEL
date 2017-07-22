package com.tel.inoob.montagtel.View;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Button;
import android.widget.TextView;
import com.tel.inoob.montagtel.Controller.RVConsumeAdapter;
import com.tel.inoob.montagtel.R;
import com.tel.inoob.montagtel.Tools.Deserialize;

/**
 * @author inoob
 * @since 0.1
 */
public class ConsumablesByTaskActivity extends AppCompatActivity{

    private static final int LAYOUT = R.layout.activity_consume_by_task;

    //Toolbar
    private TextView consume_toolbar_task_id;
    private TextView consume_toolbar_client_id;
    private TextView consume_toolbar_time;

    //Content
    private RecyclerView recyclerView;
    private Button addConsume_to_task_btn;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(LAYOUT);

        Bundle extras = getIntent().getExtras();
        //Toolbar
        consume_toolbar_task_id = (TextView) findViewById(R.id.consume_toolbar_task_id);
        consume_toolbar_client_id = (TextView) findViewById(R.id.consume_toolbar_client_id);
        consume_toolbar_time = (TextView) findViewById(R.id.consume_toolbar_time);

        String placeholder = "#"+extras.get("taskId").toString();

        consume_toolbar_task_id.setText(placeholder);
        consume_toolbar_client_id.setText(extras.get("client_id").toString());
        consume_toolbar_time.setText(extras.get("task_detail_time").toString());
        //Content
        addConsume_to_task_btn = (Button) findViewById(R.id.addConsume_to_task_btn);
        initRecyclerView((int) extras.get("userId"),(int) extras.get("taskId"));
    }

    private void initRecyclerView(int user_id, int task_id){
        recyclerView = (RecyclerView) findViewById(R.id.recycler_consume);

        LinearLayoutManager manager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(manager);

        Deserialize deserialize = new Deserialize();

        RVConsumeAdapter adapter = new RVConsumeAdapter(deserialize.deserializeConsumableByTask(user_id));
        adapter.setTaskId(task_id);
        recyclerView.setAdapter(adapter);
    }

}
