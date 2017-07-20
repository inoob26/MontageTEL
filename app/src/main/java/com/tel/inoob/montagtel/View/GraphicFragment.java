package com.tel.inoob.montagtel.View;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.tel.inoob.montagtel.Controller.TicketController;
import com.tel.inoob.montagtel.Model.Error;
import com.tel.inoob.montagtel.Model.Task;
import com.tel.inoob.montagtel.R;
import com.tel.inoob.montagtel.Tools.Deserialize;

import java.util.List;

/**
 * @author inoob
 * @since 0.1
 */
public class GraphicFragment extends Fragment {

    private TicketController controller;
    private List<Task> listOfTask;
    private RecyclerView recyclerView;

    private static final String ARG_PARAM1 = "param1";

    private int user_id;

    public GraphicFragment() {
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
        user_id = args.getInt(ARG_PARAM1);

        controller = new TicketController();
        listOfTask = controller.getListOfTask(user_id,getContext());

        recyclerView = (RecyclerView) view.findViewById(R.id.rv);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(view.getContext());
        recyclerView.setLayoutManager(linearLayoutManager);

        RVTicketAdapter adapter = new RVTicketAdapter(listOfTask,user_id);
        recyclerView.setAdapter(adapter);

        return view;
    }
}
