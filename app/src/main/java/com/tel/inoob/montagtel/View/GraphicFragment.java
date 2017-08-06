package com.tel.inoob.montagtel.View;


import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import com.tel.inoob.montagtel.Controller.TicketController;
import com.tel.inoob.montagtel.Model.Task;
import com.tel.inoob.montagtel.R;

import java.util.List;

/**
 * @author inoob
 * @since 0.1
 */
public class GraphicFragment extends Fragment {

    private TextView date_lbl;
    private TicketController controller;
    private List<Task> listOfTask;
    private RecyclerView recyclerView;
    private ImageButton calendar_btn;

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_DATA_DD_MM = "dateDDMM";

    private int user_id;
    private String date1;

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

        date_lbl = (TextView) view.findViewById(R.id.date_lbl);

        final Bundle args = getArguments();
        user_id = args.getInt(ARG_PARAM1);
        date1 = args.getString(ARG_DATA_DD_MM);

        String placeHolderDateLbl = "Заявки на " + date1;
        date_lbl.setText(placeHolderDateLbl);

        controller = new TicketController();
        listOfTask = controller.getListOfTask(args.getString("dateMMDD"),user_id,getContext());

        recyclerView = (RecyclerView) view.findViewById(R.id.rv);

        calendar_btn = (ImageButton) view.findViewById(R.id.graphic_calendar_btn);
        calendar_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TaskListCalendarDialog taskListCalendarDialog = new TaskListCalendarDialog();

                taskListCalendarDialog.setBundle(args);

                taskListCalendarDialog.show(getActivity().getFragmentManager(),"Выбор даты");
            }
        });

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(view.getContext());
        recyclerView.setLayoutManager(linearLayoutManager);

        RVTicketAdapter adapter = new RVTicketAdapter(listOfTask,user_id);
        recyclerView.setAdapter(adapter);

        return view;
    }
}
