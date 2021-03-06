package com.aaruush16.webarch.aaruush16;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MotionEvent;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.aaruush16.webarch.aaruush16.CreditsFragment.CreditsFragment;
import com.aaruush16.webarch.aaruush16.DataSync.DataFetcher;
import com.aaruush16.webarch.aaruush16.DomainFragment.DomainFragment;
import com.aaruush16.webarch.aaruush16.FavouritesFragment.FavouritesFragment;
import com.aaruush16.webarch.aaruush16.Firebase.LoginActivity;
import com.aaruush16.webarch.aaruush16.HighlightsFragment.HighlightsFragment;
import com.aaruush16.webarch.aaruush16.HomeFragment.HomeFragment;
import com.aaruush16.webarch.aaruush16.PatronsFragment.PatronsFragment;
import com.aaruush16.webarch.aaruush16.ResideMenu.ResideMenu;
import com.aaruush16.webarch.aaruush16.ResideMenu.ResideMenuItem;
import com.aaruush16.webarch.aaruush16.TeamsFragment.TeamsFragment;
import com.aaruush16.webarch.aaruush16.WorkshopsFragment.WorkshopsFragment;
import com.firebase.ui.auth.AuthUI;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.joanzapata.iconify.Iconify;
import com.joanzapata.iconify.fonts.SimpleLineIconsModule;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    FirebaseUser firebaseUser;
    Context context;
    ResideMenu resideMenu;
    Toolbar toolbar;
    FrameLayout frameLayout;
    boolean doubleBackToExitPressedOnce = false;

    int id=0;


    private void share()
    {
        Intent sharingIntent = new Intent(android.content.Intent.ACTION_SEND);
        sharingIntent.setType("text/plain");
        String about=getString(R.string.desc);
        String appUrl = "https://play.google.com/store/apps/details?id=com.aaruush16.webarch.aaruush16";
        sharingIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, "Aaruush 16");
        sharingIntent.putExtra(android.content.Intent.EXTRA_TEXT, about+"\n"+appUrl);
        startActivity(Intent.createChooser(sharingIntent, "Share via"));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        context=this;
        firebaseUser= FirebaseAuth.getInstance().getCurrentUser();
        if(firebaseUser==null){
            AuthUI.getInstance().signOut(this).addOnCompleteListener(new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull Task<Void> task) {
                    Intent intent=new Intent(context, LoginActivity.class);
                    startActivity(intent);
                }
            });
        }

        DataFetcher dataFetcher=new DataFetcher();
        dataFetcher.fetchJSON(this);
        frameLayout= (FrameLayout) findViewById(R.id.frameLayout);

        Iconify.with(new SimpleLineIconsModule());
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(firebaseUser.getDisplayName());
        //getSupportActionBar().setSubtitle("Welcome to Aaruush 2016");
        // attach to current activity;
        resideMenu = new ResideMenu(this);
        resideMenu.setBackground(R.drawable.bg_signin);
        resideMenu.attachToActivity(this);
        resideMenu.setScaleValue(0.6f);
        resideMenu.setUse3D(true);

        if( savedInstanceState == null )
            changeFragment(new HomeFragment());
        // create menu items;
        String titlesLeft[] = { "Home", "Domains", "Workshops", "Highlights","Favourites" };
        int iconLeft[] = {R.drawable.home,R.drawable.domain,R.drawable.workshops,R.drawable.highlight,R.drawable.favourite};

        for (int i = 0; i < titlesLeft.length; i++){
            ResideMenuItem item = new ResideMenuItem(this, iconLeft[i], titlesLeft[i]);
            item.setOnClickListener(this);
            item.setId(id++);
            resideMenu.addMenuItem(item,  ResideMenu.DIRECTION_LEFT); // or  ResideMenu.DIRECTION_RIGHT
        }
        // create menu items;
        String titlesRight[] = { "Patrons","Team", "Invite","Logout", "Credits",};
        int iconRight[] = {R.drawable.patron,R.drawable.team,R.drawable.invite,R.drawable.logout,R.drawable.about_us};

        for (int i = 0; i < titlesRight.length; i++){
            ResideMenuItem item = new ResideMenuItem(this, iconRight[i], titlesRight[i]);
            item.setOnClickListener(this);
            item.setId(id++);
            resideMenu.addMenuItem(item,  ResideMenu.DIRECTION_RIGHT); // or  ResideMenu.DIRECTION_RIGHT
        }
        findViewById(R.id.leftMenu).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                resideMenu.openMenu(ResideMenu.DIRECTION_LEFT);
            }
        });
        findViewById(R.id.rightMenu).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                resideMenu.openMenu(ResideMenu.DIRECTION_RIGHT);
            }
        });
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        return resideMenu.dispatchTouchEvent(ev);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case 0:
                changeFragment(new HomeFragment());
                break;
            case 1:
                changeFragment(new DomainFragment(),"IGNORE");
                break;
            case 2:
                changeFragment(new WorkshopsFragment());
                break;
            case 3:
                changeFragment(new HighlightsFragment());
                break;
            case 4:
                changeFragment(new FavouritesFragment());
                break;
            case 5:
                changeFragment(new PatronsFragment(),"IGNORE");
                break;
            case 6:
                changeFragment(new TeamsFragment(),"IGNORE");
                break;
            case 7:
                share();
                break;
            case 8:
                AuthUI.getInstance().signOut(MainActivity.this).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        Intent intent=new Intent(context, LoginActivity.class);
                        startActivity(intent);
                    }
                });
                break;
            case 9:
                changeFragment(new CreditsFragment());
                break;

        }
        resideMenu.closeMenu();

    }
    private void changeFragment(Fragment targetFragment){
        resideMenu.removeIgnoredView(frameLayout);
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.frameLayout, targetFragment, "fragment")
                .setTransitionStyle(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
                .commit();
    }

    private void changeFragment(Fragment targetFragment,String ignore){
        int layout=R.id.frameLayout;
        resideMenu.addIgnoredView(frameLayout);
        getSupportFragmentManager()
                .beginTransaction()
                .replace(layout, targetFragment, "fragment")
                .setTransitionStyle(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
                .commit();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    public void onBackPressed() {
        if (doubleBackToExitPressedOnce) {
            super.onBackPressed();
            return;
        }

        this.doubleBackToExitPressedOnce = true;
        Toast.makeText(this, "Please click BACK again to exit", Toast.LENGTH_SHORT).show();

        new Handler().postDelayed(new Runnable() {

            @Override
            public void run() {
                doubleBackToExitPressedOnce=false;
            }
        }, 2000);
    }
}
