package com.aaruush16.webarch.aaruush16.HomeFragment;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.aaruush16.webarch.aaruush16.R;
import com.bumptech.glide.Glide;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.mikhaellopez.circularimageview.CircularImageView;


/**
 * Ravi/28/06/2016
 */
public class HomeFragment extends Fragment {

    public Context context;
    private View rootView;
    FirebaseUser firebaseUser;

    public HomeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        rootView =  inflater.inflate(R.layout.fragment_home, container, false);
        CircularImageView circleImageView= (CircularImageView) rootView.findViewById(R.id.profileImage);
        context=getActivity();
        firebaseUser= FirebaseAuth.getInstance().getCurrentUser();
        if(firebaseUser.getPhotoUrl()!=null)
            Glide.with(this).load(firebaseUser.getPhotoUrl()).into(circleImageView);
        ((AppCompatActivity)getActivity()).getSupportActionBar().setTitle("Home");

        return rootView;
    }

    public static Intent getOpenFacebookIntent(Context context)
    {

        try {
            context.getPackageManager()
                    .getPackageInfo("com.facebook.katana", 0);
            return new Intent(Intent.ACTION_VIEW,
                    Uri.parse("fb://page/172731949541472"));
        } catch (Exception e)
        {
            return new Intent(Intent.ACTION_VIEW,
                    Uri.parse("https://www.facebook.com/aaruush.srm"));
        }
    }
    public static Intent getOpentwitterIntent(Context context)
    {

        try {
            context.getPackageManager()
                    .getPackageInfo("com.twitter.android", 0);
            return new Intent(Intent.ACTION_VIEW,
                    Uri.parse("twitter://user?user_id=1357708807"));
        } catch (Exception e)
        {
            return new Intent(Intent.ACTION_VIEW,
                    Uri.parse("https://twitter.com/Aaruush_Srmuniv"));
        }
    }

    public static Intent launchWhatsApp(Context context)
    {

        try {
            context.getPackageManager()
                    .getPackageInfo("com.whatsapp", 0);
            return new Intent(Intent.ACTION_VIEW,
                    Uri.parse("whatsapp://send?text=https://play.google.com/store/apps/details?id=webarch.aaruush15&hl=en"));

        } catch (Exception e)
        {
            return new Intent(Intent.ACTION_VIEW,
                    Uri.parse("https://play.google.com/store/apps/details?id=com.whatsapp&hl=en"));
        }
    }
}
