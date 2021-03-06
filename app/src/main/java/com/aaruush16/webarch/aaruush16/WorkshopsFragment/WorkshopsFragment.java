package com.aaruush16.webarch.aaruush16.WorkshopsFragment;


import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.ForwardingListener;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.aaruush16.webarch.aaruush16.DomainFragment.EventListAdapter;
import com.aaruush16.webarch.aaruush16.R;
import com.aaruush16.webarch.aaruush16.RealmClasses.Event;
import com.aaruush16.webarch.aaruush16.RealmClasses.Workshop;
import com.github.florent37.materialviewpager.header.MaterialViewPagerHeaderDecorator;
import com.ramotion.foldingcell.FoldingCell;

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
public class WorkshopsFragment extends Fragment {

    Context context;
    ListView list_event;
    WorkshopAdapter workshopAdapter;
    ArrayList<Workshop> eventList;
    Realm realm;

    public WorkshopsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle("Workshops");
        View rootView = inflater.inflate(R.layout.fragment_workshops, container, false);
        final Context x = getActivity();

        context = getContext();
        View view = rootView;
        list_event = (ListView) view.findViewById(R.id.list_workshop);

        realm = Realm.getDefaultInstance();
        RealmQuery<Workshop> query = realm.where(Workshop.class);
        RealmResults<Workshop> results = query.findAll();
        eventList = new ArrayList<Workshop>();
        Iterator<Workshop> eventIterator = results.iterator();
        while (eventIterator.hasNext()) {
            Workshop event = eventIterator.next();
            eventList.add(event);
        }
        workshopAdapter = new WorkshopAdapter(context, eventList);

        list_event.setAdapter(workshopAdapter);

        list_event.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                // toggle clicked cell state
                ((FoldingCell) view).toggle(false);
                // register in adapter that state for selected cell is toggled
                workshopAdapter.registerToggle(i);

            }
        });
        results.addChangeListener(new RealmChangeListener<RealmResults<Workshop>>() {
            @Override
            public void onChange(RealmResults<Workshop> element) {
                Iterator<Workshop> eventIterator = element.iterator();
                eventList = new ArrayList<Workshop>();
                while (eventIterator.hasNext()) {
                    Workshop workshop = eventIterator.next();
                    eventList.add(workshop);
                }
                workshopAdapter.notifyDataSetChanged();
            }
        });


        return rootView;
    }


}
