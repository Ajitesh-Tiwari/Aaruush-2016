package com.aaruush16.webarch.aaruush16.DomainFragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.aaruush16.webarch.aaruush16.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class DomainPagerFragment extends Fragment {

    String domain;
    TextView txt_domain;

    public DomainPagerFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_domain_pager, container, false);
        txt_domain= (TextView) view.findViewById(R.id.txt_domain);
        txt_domain.setText(domain);
        return view;
    }

    public void setData(String domain){
        this.domain=domain;
    }

}
