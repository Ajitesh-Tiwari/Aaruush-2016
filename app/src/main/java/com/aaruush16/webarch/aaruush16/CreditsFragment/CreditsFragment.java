package com.aaruush16.webarch.aaruush16.CreditsFragment;


import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.aaruush16.webarch.aaruush16.R;

import org.w3c.dom.Text;


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

    private int getNavBarWidth() {
        Resources r = getResources();
        int id = r.getIdentifier("navigation_bar_width", "dimen", "android");
        return r.getDimensionPixelSize(id);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.fragment_credits, container, false);
        ((AppCompatActivity)getActivity()).getSupportActionBar().setTitle("About Us");
//        int width = getNavBarWidth();
        LinearLayout L = (LinearLayout)view.findViewById(R.id.layout);
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);

        Context c = getActivity();
//        layoutParams.setMargins(0,0,0,((int)(1.5*width)));
        TextView t = new TextView(c);
        t.setTextSize(TypedValue.COMPLEX_UNIT_SP,16);
        t.setGravity(Gravity.CENTER_VERTICAL | Gravity.CENTER_HORIZONTAL);
        t.setTextColor(0xFFFFFFFF);
        t.setText("Our Team Consists Of An Amalgam Of Talented Designers, Developers, Video Makers And Writers. Tap here to view our website.");
        t.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.webarchsrm.com/index.php"));
                startActivity(browserIntent);
            }
        });
        context=c;
        img_wa_logo= (ImageView) view.findViewById(R.id.img_wa_logo);
        img_wa_logo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(count<5) count++;
                else    {
                    count=0;
                    Animation animation= AnimationUtils.loadAnimation(context,R.anim.rotate_wa);
                    animation.setInterpolator(context,android.R.anim.accelerate_decelerate_interpolator);
                    //animation.setDuration(1000);
                    img_wa_logo.startAnimation(animation);
                }
            }
        });

        L.addView(t,layoutParams);
        return view;

    }
}
