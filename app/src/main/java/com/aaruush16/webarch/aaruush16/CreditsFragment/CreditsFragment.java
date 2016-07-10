package com.aaruush16.webarch.aaruush16.CreditsFragment;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import com.aaruush16.webarch.aaruush16.R;


/**
 * A simple {@link Fragment} subclass.
 */
public class CreditsFragment extends Fragment{

    ImageView img_wa_logo,img_wa2_logo;
    int count=0;
    Context context;
    public CreditsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.fragment_credits, container, false);
        return view;

    }
}
