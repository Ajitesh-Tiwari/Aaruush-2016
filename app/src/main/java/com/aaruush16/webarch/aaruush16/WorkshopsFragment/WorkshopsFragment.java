package com.aaruush16.webarch.aaruush16.WorkshopsFragment;


import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.aaruush16.webarch.aaruush16.R;
import com.ramotion.foldingcell.FoldingCell;


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
        View rootView =  inflater.inflate(R.layout.fragment_workshops, container, false);
        final Context x = getActivity();

        // get our folding cell
        final FoldingCell fc = (FoldingCell) rootView.findViewById(R.id.folding_cell);

        // attach click listener to folding cell
        fc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fc.toggle(false);
            }
        });

        final FoldingCell fc1 = (FoldingCell) rootView.findViewById(R.id.folding_cell1);

        // attach click listener to folding cell
        fc1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fc1.toggle(false);
            }
        });

        final FoldingCell fc2 = (FoldingCell) rootView.findViewById(R.id.folding_cell2);

        // attach click listener to folding cell
        fc2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fc2.toggle(false);
            }
        });

        final FoldingCell fc3 = (FoldingCell) rootView.findViewById(R.id.folding_cell3);

        // attach click listener to folding cell
        fc3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fc3.toggle(false);
            }
        });

        // attach click listener to fold btn
//        final Button foldBtn = (Button) rootView.findViewById(R.id.fold_btn);
//        foldBtn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                fc.fold(false);
//            }
//        });
//
//        // attach click listener to toast btn
//        final Button toastBtn = (Button) rootView.findViewById(R.id.toast_btn);
//        toastBtn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Toast.makeText(x, "Victrix superbus cursus est.", Toast.LENGTH_SHORT).show();
//            }
//        });
        return rootView;
    }


}
