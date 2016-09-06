package com.aaruush16.webarch.aaruush16.TeamsFragment;


import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.aaruush16.webarch.aaruush16.R;
import com.aaruush16.webarch.aaruush16.RealmClasses.PersonTeam;
import com.aaruush16.webarch.aaruush16.ViewFlipper.ViewFlipperAdapter;
import com.aaruush16.webarch.aaruush16.ViewFlipper.ViewFlipperItem;
import com.yalantis.flipviewpager.utils.FlipSettings;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import io.realm.Realm;
import io.realm.RealmQuery;
import io.realm.RealmResults;


/**
 * A simple {@link Fragment} subclass.
 */
public class TeamsFragment extends Fragment {

    List<ViewFlipperItem> teamList;
    ListView list_team;
    Realm realm;

    public TeamsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        ((AppCompatActivity)getActivity()).getSupportActionBar().setTitle("Team");
        View view=inflater.inflate(R.layout.fragment_teams, container, false);
        list_team= (ListView) view.findViewById(R.id.list_team);

        realm= Realm.getDefaultInstance();
        RealmQuery<PersonTeam> query=realm.where(PersonTeam.class);
        RealmResults<PersonTeam> results=query.findAll();
        teamList= new ArrayList<>();
        Iterator<PersonTeam> teamIterator=results.iterator();
        while (teamIterator.hasNext()){
            PersonTeam personTeam=teamIterator.next();
            ViewFlipperItem item=new ViewFlipperItem(0,personTeam.getName(),R.color.sienna,personTeam.getPosition(),personTeam.getEmail(),personTeam.getContact());
            item.img_id=personTeam.getImg_id();
            teamList.add(item);
        }

        FlipSettings settings = new FlipSettings.Builder().defaultPage(1).build();
        list_team.setAdapter(new ViewFlipperAdapter(getActivity(), teamList, settings));
        list_team.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String contact = (teamList.get(i).getInterests().get(2));
                if (contact.contains("@")) {
                    Intent intent = new Intent(Intent.ACTION_SEND);
                    intent.setType("plain/text");
                    intent.putExtra(Intent.EXTRA_EMAIL, new String[]{contact});
                    intent.putExtra(Intent.EXTRA_SUBJECT, "subject");
                    intent.putExtra(Intent.EXTRA_TEXT, "mail body");
                    startActivity(Intent.createChooser(intent, ""));
                } else {
                    String x = "tel:" + contact;
                    Intent callIntent = new Intent(Intent.ACTION_DIAL);
                    callIntent.setData(Uri.parse(x));
                    startActivity(callIntent);
                }
            }
        });

        return view;
    }

}
