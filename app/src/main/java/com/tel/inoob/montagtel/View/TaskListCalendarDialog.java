package com.tel.inoob.montagtel.View;

import android.app.DialogFragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.CalendarView;
import android.widget.ImageButton;
import com.tel.inoob.montagtel.R;

public class TaskListCalendarDialog extends DialogFragment {

    private CalendarView calendarView;
    private ImageButton close_btn;
    private Bundle bundle;

    public TaskListCalendarDialog(){}

    public void setBundle(Bundle bundle) {
        this.bundle = bundle;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.dialog_calendar_task_list_service,container,false);

        //turn off dialog title
        getDialog().requestWindowFeature(Window.FEATURE_NO_TITLE);

        calendarView = (CalendarView) view.findViewById(R.id.calendar_for_graphic_fragment);
        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(CalendarView view, int year, int month, int dayOfMonth) {
                int m = month+1;

                Intent ticket = new Intent(getActivity().getApplicationContext(),TaskListActivity.class);

                ticket.putExtra("userId",bundle.getInt("param1"));
                ticket.putExtra("userName",bundle.getString("userName"));
                ticket.putExtra("dateDDMM", ""+ dayOfMonth + "." + m);
                ticket.putExtra("dateMMDD", ""+m+"."+dayOfMonth);
                getDialog().dismiss();
                startActivity(ticket);
            }
        });

        close_btn = (ImageButton) view.findViewById(R.id.dialog_calendar_close_btn);
        close_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getDialog().dismiss();
            }
        });

        return view;
    }
}
