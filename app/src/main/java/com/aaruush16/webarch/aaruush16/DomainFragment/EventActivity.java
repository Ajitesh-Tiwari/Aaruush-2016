package com.aaruush16.webarch.aaruush16.DomainFragment;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
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

        ActionBar actionBar=getSupportActionBar();
        actionBar.setTitle(event.getName());


        event_img= (ImageView) findViewById(R.id.event_img);
        txt_description= (TextView) findViewById(R.id.txt_description);
        txt_date= (TextView) findViewById(R.id.txt_date);
        txt_contact= (TextView) findViewById(R.id.txt_contact);
        txt_cost= (TextView) findViewById(R.id.txt_cost);

        ImageLoader imageLoader= AppController.getInstance().getImageLoader();
        imageLoader.get(event.getImageURL(),ImageLoader.getImageListener(event_img,R.drawable.aaruushlogo,R.drawable.wa));
        txt_description.setText(event.getDescription());
        txt_date.setText(event.getDate());
        txt_contact.setText(event.getContact());
        txt_cost.setText(event.getCost());
    }
}
