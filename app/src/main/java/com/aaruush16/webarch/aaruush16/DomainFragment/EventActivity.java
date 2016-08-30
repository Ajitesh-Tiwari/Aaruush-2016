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
import android.text.Html;
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
    TextView txt_description,txt_date,txt_contact,txt_round,txt_desc_title;
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
        txt_round= (TextView) findViewById(R.id.txt_rounds);
        txt_desc_title= (TextView) findViewById(R.id.txt_desc_title);

//        ImageLoader imageLoader= AppController.getInstance().getImageLoader();
//        imageLoader.get(event.getImageURL(),ImageLoader.getImageListener(event_img,R.drawable.aaruushlogo,R.drawable.wa));
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
            if(event.getDescription().compareTo("Nil")!=0){
                txt_description.setText(Html.fromHtml(event.getDescription(),Html.FROM_HTML_MODE_LEGACY));
            }else{
                txt_description.setText("No Description is available.");
            }
            txt_round.setText(Html.fromHtml(event.getRounds(),Html.FROM_HTML_MODE_LEGACY));
            if(event.getContact().compareTo("Nil")!=0){
                txt_contact.setText(Html.fromHtml(event.getContact(),Html.FROM_HTML_MODE_LEGACY));
            }else{
                txt_contact.setText("No Contacts available.");
            }

        } else {

            if(event.getDescription().compareTo("Nil")!=0){
                txt_description.setText(Html.fromHtml(event.getDescription()));
            }else{
                txt_description.setText("No Description is available.");
            }

            if(event.getContact().compareTo("Nil")!=0){
                txt_contact.setText(Html.fromHtml(event.getContact()));
            }else {
                txt_contact.setText("No Contacts available.");
            }
            txt_round.setText(Html.fromHtml(event.getRounds()));
        }

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
