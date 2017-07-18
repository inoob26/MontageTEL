package com.tel.inoob.montagtel.View;


import android.content.Context;
import android.support.v4.app.DialogFragment;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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
    RecyclerOnItemClickListener communicator;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        communicator = (RecyclerOnItemClickListener) context;
    }

    public ServiceAdvansDialog(){

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_service_adavans,container,false);

        getDialog().setCancelable(true);
        getDialog().setTitle("Выбор доп услуги");

        Bundle args = getArguments();
        recycle_view_service_advans = (RecyclerView) view.findViewById(R.id.recycle_view_service_advans);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(view.getContext());
        recycle_view_service_advans.setLayoutManager(linearLayoutManager);

        Deserialize deserialize = new Deserialize();

        RVServiceAdvansAdapter advansAdapter = new RVServiceAdvansAdapter(deserialize.deserializeServiceAdvans(args.getInt("user_id")));
        recycle_view_service_advans.setAdapter(advansAdapter);

        return view;
    }
}
