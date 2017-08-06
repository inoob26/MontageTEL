package com.tel.inoob.montagtel.View;

import android.os.Bundle;
import android.os.ConditionVariable;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CalendarView;
import android.widget.Toast;
import com.tel.inoob.montagtel.R;

/**
 * @author inoob
 * @since 0.1
 */
public class CalendarFragment extends Fragment {

    private CalendarView calendarView;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_calendar,container,false);

        calendarView = (CalendarView) view.findViewById(R.id.calendar_view);
        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(CalendarView view, int year, int month, int dayOfMonth) {
                int m = month+1;
                Toast.makeText(getContext(),"Date: " + year + "." + m + "." + dayOfMonth,0).show();
            }
        });

        return view;
    }
}
