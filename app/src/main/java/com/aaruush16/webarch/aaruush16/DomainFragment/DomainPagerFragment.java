package com.aaruush16.webarch.aaruush16.DomainFragment;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.aaruush16.webarch.aaruush16.R;
import com.aaruush16.webarch.aaruush16.RealmClasses.Event;
import com.github.florent37.materialviewpager.header.MaterialViewPagerHeaderDecorator;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import io.realm.Realm;
import io.realm.RealmChangeListener;
import io.realm.RealmQuery;
import io.realm.RealmResults;

/**
 * A simple {@link Fragment} subclass.
 */
public class DomainPagerFragment extends Fragment {

    Context context;
    String domain;
    TextView txt_domain;
    RecyclerView list_event;
    EventListAdapter eventListAdapter;
    List<Event> eventList;
    Realm realm;
    LinearLayoutManager linearLayoutManager;

    public DomainPagerFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        context=getContext();
        View view= inflater.inflate(R.layout.fragment_domain_pager, container, false);
       // txt_domain= (TextView) view.findViewById(R.id.txt_domain);
        list_event= (RecyclerView) view.findViewById(R.id.list_event);
       // txt_domain.setText(domain);

        linearLayoutManager=new LinearLayoutManager(context);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        list_event.setLayoutManager(linearLayoutManager);

        realm= Realm.getDefaultInstance();
        RealmQuery<Event> query=realm.where(Event.class).contains("Type",domain);
        RealmResults<Event> results=query.findAll();
        eventList=new ArrayList<Event>();
        Iterator<Event> eventIterator=results.iterator();
        while (eventIterator.hasNext()){
            Event event=eventIterator.next();
            eventList.add(event);
        }
        eventListAdapter=new EventListAdapter(context,eventList);
        list_event.addItemDecoration(new MaterialViewPagerHeaderDecorator());
        list_event.setAdapter(eventListAdapter);
        results.addChangeListener(new RealmChangeListener<RealmResults<Event>>() {
            @Override
            public void onChange(RealmResults<Event> element) {
                Iterator<Event> eventIterator=element.iterator();
                eventList=new ArrayList<Event>();
                while (eventIterator.hasNext()){
                    Event event=eventIterator.next();
                    eventList.add(event);
                }
                eventListAdapter.notifyDataSetChanged();
            }
        });

        return view;
    }

    public void setData(String domain){
        this.domain=domain;
    }

}
