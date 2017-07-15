package com.tel.inoob.montagtel.View;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import com.tel.inoob.montagtel.R;

/**
 *
 * @author inoob
 * @since 0.1
 */
public class ContactFragment extends Fragment {

    /**
     * Phone numbers.
     */
    private final String stp = "84957874207";
    private final String secretary = "84957874200";
    private final String accountManagers = "84956492000";

    public ContactFragment() {
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_contact, container, false);

        Button stp_btn = (Button) view.findViewById(R.id.stp_btn);
        Button secretary_btn = (Button) view.findViewById(R.id.secretary_btn);
        Button account_btn = (Button) view.findViewById(R.id.account_btn);

        stp_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dial(stp);
            }
        });

        secretary_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dial(secretary);
            }
        });

        account_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dial(accountManagers);
            }
        });

        return view;
    }

    /**
     * Open dial app.
     * @param number phone number.
     */
    private void dial(final String number){
        Intent dialNumber = new Intent("android.intent.action.DIAL", Uri.parse("tel:"+number));
        startActivity(dialNumber);
    }
}
