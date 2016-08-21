package com.aaruush16.webarch.aaruush16.TeamsFragment;


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
public class TeamsFragment extends Fragment {


    public TeamsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        ((AppCompatActivity)getActivity()).getSupportActionBar().setTitle("Team");
        return inflater.inflate(R.layout.fragment_teams, container, false);
    }

}
