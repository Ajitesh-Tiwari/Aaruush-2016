package com.aaruush16.webarch.aaruush16.PatronsFragment;


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
import com.aaruush16.webarch.aaruush16.ViewFlipper.ViewFlipperAdapter;
import com.aaruush16.webarch.aaruush16.ViewFlipper.ViewFlipperItem;
import com.yalantis.flipviewpager.utils.FlipSettings;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class PatronsFragment extends Fragment {

    List<ViewFlipperItem> patrons;

    public PatronsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        ((AppCompatActivity)getActivity()).getSupportActionBar().setTitle("Patrons");
        View view= inflater.inflate(R.layout.fragment_patrons, container, false);
        final ListView list_patrons = (ListView) view.findViewById(R.id.list_patrons);

        patrons=new ArrayList<>();

        patrons.add(new ViewFlipperItem(R.drawable.director, "Dr C. Muthamizhchelvan", R.color.sienna, "Director E & T","Patron","director.et@srmuniv.ac.in"));
        patrons.add(new ViewFlipperItem(R.drawable.convenor, "Prof. Rathinam. A", R.color.saffron, "Convenor - Aaruush","Patron","convenor@aaruush.net"));
        patrons.add(new ViewFlipperItem(R.drawable.fa, "Prof. V. Ponniah", R.color.green, "Finance Advisor - Aaruush","Patron","advisor.finance@aaruush.net"));
        patrons.add(new ViewFlipperItem(R.drawable.eo, "Mr. V. Thirumurugan", R.color.pink, "Estate Officer","Patron","estate.officer@srmuniv.ac.in"));

        FlipSettings settings = new FlipSettings.Builder().defaultPage(1).build();
        list_patrons.setAdapter(new ViewFlipperAdapter(getActivity(), patrons, settings));
        list_patrons.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String contact = (patrons.get(i).getInterests().get(2));
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
