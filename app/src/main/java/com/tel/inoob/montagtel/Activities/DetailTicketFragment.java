package com.tel.inoob.montagtel.Activities;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.tel.inoob.montagtel.Controller.RVTaskSeviceListAdapter;
import com.tel.inoob.montagtel.Controller.TicketController;
import com.tel.inoob.montagtel.Model.Task;
import com.tel.inoob.montagtel.Model.TaskService;
import com.tel.inoob.montagtel.R;

import java.util.LinkedList;
import java.util.List;

/**
 * @author inoob
 * @since 0.1
 */
public class DetailTicketFragment extends Fragment {

    //private static final int LAYOUT = R.layout.activity_detail_ticket;

    private TicketController controller;
    private TextView client_fio;
    private TextView client_phone;
    private TextView task_address;
    private TextView task_id;
    private TextView client_id;
    private TextView start_time;
    private TextView task_detail_lbl_for_device_sum;
    private TextView task_detail_lbl_pay_total_sum;

    private RecyclerView recycler_view_task_service_list;

    private ProgressDialog progressDialog;

    public DetailTicketFragment(){

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_graphic, container, false);
        client_fio = (TextView) view.findViewById(R.id.ticket_detail_client_phone);
        client_phone = (TextView) view.findViewById(R.id.ticket_detail_client_phone);
        task_address = (TextView) view.findViewById(R.id.ticket_detail_task_address);
        task_id = (TextView) view.findViewById(R.id.task_detail_id);
        client_id = (TextView) view.findViewById(R.id.task_detail_client_id);
        start_time = (TextView) view.findViewById(R.id.task_detail_time);
        task_detail_lbl_for_device_sum = (TextView) view.findViewById(R.id.task_detail_lbl_for_device_sum);
        task_detail_lbl_pay_total_sum = (TextView) view.findViewById(R.id.task_detail_lbl_pay_total_sum);

        Bundle args = getArguments();

        client_fio.setText(args.getString("clientFio"));
        client_phone.setText(args.getString("clientPhone"));
        task_address.setText(args.getString("taskAddress"));
        task_id.setText("№" + args.getInt("task_id"));
        client_id.setText(args.getString("client_id"));
        start_time.setText(args.getString("task_detail_time"));

        recycler_view_task_service_list = (RecyclerView) view.findViewById(R.id.recycle_view_task_service_list);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(view.getContext());


        RVTaskSeviceListAdapter adapter = new RVTaskSeviceListAdapter();
        initAdapter(args.getInt("task_id"),adapter);

        recycler_view_task_service_list.setLayoutManager(linearLayoutManager);
        recycler_view_task_service_list.setAdapter(adapter);


        return view;
    }

    private void initAdapter(final int task_id, final RVTaskSeviceListAdapter adapter){
        Runnable newThread = new Runnable() {
            @Override
            public void run() {
                try {
                    progressDialog.setMessage("Загрузка данных");
                    progressDialog.show();

                    controller = new TicketController();
                    List<TaskService> list = controller.getListOfTaskService(task_id);

                    double device_sum = 0.0;
                    double total_sum = 0.0;

                    for (TaskService task : list) {
                        if (0 != task.getScladId()) {
                            device_sum += task.getPrice();
                            total_sum += task.getPrice();
                        } else {
                            total_sum += task.getPrice();
                        }
                    }

                    task_detail_lbl_for_device_sum.setText("" + device_sum + " руб");
                    task_detail_lbl_pay_total_sum.setText("" + total_sum + " руб");

                    adapter.setTaskServiceList(list);

                } finally {
                    progressDialog.dismiss();
                    controller = null;
                }
            }
        };

        newThread.run();
    }
}
