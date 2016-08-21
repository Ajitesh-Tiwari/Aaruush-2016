package com.aaruush16.webarch.aaruush16.WorkshopsFragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.aaruush16.webarch.aaruush16.R;


/**
 * A simple {@link Fragment} subclass.
 */
public class WorkshopsFragment extends Fragment {


    public WorkshopsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        ((AppCompatActivity)getActivity()).getSupportActionBar().setTitle("Workshops");
        return inflater.inflate(R.layout.fragment_workshops, container, false);
    }

}
