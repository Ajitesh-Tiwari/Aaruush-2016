package com.aaruush16.webarch.aaruush16.DomainFragment;

import android.content.Intent;
import android.graphics.Color;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.aaruush16.webarch.aaruush16.R;
import com.aaruush16.webarch.aaruush16.RealmClasses.Event;
import com.aaruush16.webarch.aaruush16.VolleySingleton.AppController;
import com.android.volley.toolbox.ImageLoader;

import java.util.Iterator;

import io.realm.Realm;
import io.realm.RealmQuery;
import io.realm.RealmResults;

public class EventActivity extends AppCompatActivity {

    Realm realm;
    Event event;
    ImageView event_img;
    CollapsingToolbarLayout mCollapse;
    TextView txt_description,txt_date,txt_contact,txt_cost;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event);
        Intent i =getIntent();
        int id=i.getExtras().getInt("id");
        realm=Realm.getDefaultInstance();
        RealmQuery<Event> query=realm.where(Event.class).equalTo("id",id);
        event=new Event();
        event=query.findFirst();

        mCollapse = (CollapsingToolbarLayout) findViewById(R.id.toolbar_layout);
        mCollapse.setCollapsedTitleTextAppearance(R.style.CollapsedAppBar);
        mCollapse.setExpandedTitleTextAppearance(R.style.ExpandedAppBar);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitleTextColor(Color.BLUE);
        toolbar.setTitle(event.getName());

        setSupportActionBar(toolbar);

//        ActionBar actionBar=getSupportActionBar();
//        actionBar.setTitle(event.getName());


        event_img= (ImageView) findViewById(R.id.event_img);
        txt_description= (TextView) findViewById(R.id.txt_description);
        txt_date= (TextView) findViewById(R.id.txt_date);
        txt_contact= (TextView) findViewById(R.id.txt_contact);
        txt_cost= (TextView) findViewById(R.id.txt_cost);

//        ImageLoader imageLoader= AppController.getInstance().getImageLoader();
//        imageLoader.get(event.getImageURL(),ImageLoader.getImageListener(event_img,R.drawable.aaruushlogo,R.drawable.wa));
        txt_description.setText(event.getDescription());
        txt_contact.setText(event.getContact());
        final FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        if(event.getFav()){
            fab.setImageResource(R.drawable.ic_star_white_24dp);
        }else{
            fab.setImageResource(R.drawable.ic_star_border_white_24dp);
        }
        fab.setOnClickListener(new View.OnClickListener() {
            int i=0;
            @Override
            public void onClick(View view) {
                realm.beginTransaction();
                if(!event.getFav()) {
                    fab.setImageResource(R.drawable.ic_star_white_24dp);
                    Snackbar.make(view, "Added To Favourites", Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show();
                    event.setFav(true);
                }
                else
                {
                    fab.setImageResource(R.drawable.ic_star_border_white_24dp);
                    Snackbar.make(view, "Removed from Favourites", Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show();
                    event.setFav(false);
                }
                realm.commitTransaction();
            }
        });
    }
}
