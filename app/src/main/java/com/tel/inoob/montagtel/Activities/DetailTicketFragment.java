package com.tel.inoob.montagtel.Activities;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.tel.inoob.montagtel.Controller.TicketController;
import com.tel.inoob.montagtel.Model.Task;
import com.tel.inoob.montagtel.R;
/**
 * @author inoob
 * @since 0.1
 */
public class DetailTicketFragment extends Fragment {

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
        Bundle args = getArguments();

        return view;
    }
}
