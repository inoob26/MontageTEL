package com.tel.inoob.montagtel.View;


import android.content.Context;
import android.provider.ContactsContract;
import android.support.v4.app.DialogFragment;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import com.tel.inoob.montagtel.R;
import com.tel.inoob.montagtel.Tools.Deserialize;

/**
 * {@code ServiceAdvansDialog} describe additional service object.
 * it adding dynamically into DetailTicketActivity.
 * it is service that can't be schedule before hand.
 *
 * @author inoob
 * @since  0.1
 */
public class ServiceAdvansDialog extends DialogFragment {

    /**
     * Fields
     */
    private RecyclerView recycle_view_service_advans;
    private ImageButton fragment_service_advans_btn_close;

    private DetailTicketActivity detailTicketActivity;

    public ServiceAdvansDialog(){}

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    public void setDetailTicketActivity(DetailTicketActivity detailTicketActivity) {
        this.detailTicketActivity = detailTicketActivity;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_service_adavans,container,false);

        fragment_service_advans_btn_close = (ImageButton) view.findViewById(R.id.fragment_service_advans_btn_close);
        //close Dialog
        fragment_service_advans_btn_close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getDialog().dismiss();
            }
        });

        Bundle args = getArguments();
        recycle_view_service_advans = (RecyclerView) view.findViewById(R.id.recycle_view_service_advans);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(view.getContext());
        recycle_view_service_advans.setLayoutManager(linearLayoutManager);

        Deserialize deserialize = new Deserialize();

        RVServiceAdvansAdapter advansAdapter =
                new RVServiceAdvansAdapter(deserialize.deserializeServiceAdvans(args.getInt("user_id")),
                        getDialog(),this.detailTicketActivity);
        advansAdapter.setTaskId(args.getInt("task_id"));

        recycle_view_service_advans.setAdapter(advansAdapter);
        return view;
    }
}
