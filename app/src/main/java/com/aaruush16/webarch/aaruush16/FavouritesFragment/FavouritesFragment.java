package com.aaruush16.webarch.aaruush16.FavouritesFragment;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.aaruush16.webarch.aaruush16.DomainFragment.EventListAdapter;
import com.aaruush16.webarch.aaruush16.R;
import com.aaruush16.webarch.aaruush16.RealmClasses.Event;
import com.github.florent37.materialviewpager.header.MaterialViewPagerHeaderDecorator;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import io.realm.Realm;
import io.realm.RealmQuery;
import io.realm.RealmResults;


/**
 * A simple {@link Fragment} subclass.
 */
public class FavouritesFragment extends Fragment {

    Context context;
    String domain;
    TextView txt_domain;
    RecyclerView list_event;
    EventListAdapter eventListAdapter;
    List<Event> eventList;
    Realm realm;
    LinearLayoutManager linearLayoutManager;

    public FavouritesFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        ((AppCompatActivity)getActivity()).getSupportActionBar().setTitle("Favourites");
        context=getActivity();
        View view= inflater.inflate(R.layout.fragment_favourites, container, false);
        list_event= (RecyclerView) view.findViewById(R.id.list_event_fav);
        linearLayoutManager=new LinearLayoutManager(context);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        list_event.setLayoutManager(linearLayoutManager);
        fetchFav();
        return view;
    }

    void fetchFav(){
        realm= Realm.getDefaultInstance();
        RealmQuery<Event> query=realm.where(Event.class).equalTo("Fav",true);
        RealmResults<Event> results=query.findAll();
        eventList=new ArrayList<Event>();
        Iterator<Event> eventIterator=results.iterator();
        while (eventIterator.hasNext()){
            Event event=eventIterator.next();
            eventList.add(event);
        }
        if(eventList.size()==0)
            Toast.makeText(context,"No Favourites Found",Toast.LENGTH_LONG).show();
        eventListAdapter=new EventListAdapter(context,eventList);
        list_event.addItemDecoration(new MaterialViewPagerHeaderDecorator());
        list_event.setAdapter(eventListAdapter);
    }

    @Override
    public void onResume() {
        super.onResume();
        eventListAdapter.notifyDataSetChanged();
        fetchFav();
    }
}
