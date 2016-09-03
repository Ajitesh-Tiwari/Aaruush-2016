package com.aaruush16.webarch.aaruush16.WorkshopsFragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.content.Context;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.aaruush16.webarch.aaruush16.R;
import com.aaruush16.webarch.aaruush16.RealmClasses.Workshop;
import com.ramotion.foldingcell.FoldingCell;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Ravi on 03-09-2016.
 */
class WorkshopAdapter extends BaseAdapter {

    private Context context;
    private ArrayList<Workshop> workshops;

    TextView title;
    TextView desc;
    TextView cost;
    TextView date;
    TextView company;
    TextView team;
    FoldingCell foldingCell;

    public WorkshopAdapter(Context context, ArrayList<Workshop> workshops) {
        this.context = context;
        this.workshops = workshops;
    }


    @Override
    public Object getItem(int position) {
        return workshops.get(position);
    }
//
    @Override
    public int getCount() {
        return workshops.size();
    }


    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View itemView, ViewGroup parent) {



        if (itemView == null) {
            LayoutInflater inflater = (LayoutInflater) context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            itemView = (View) inflater.inflate(
                    R.layout.workshop_list_item, null);
        }


        title= (TextView) itemView.findViewById(R.id.title);
        desc= (TextView) itemView.findViewById(R.id.desc);
        cost= (TextView) itemView.findViewById(R.id.cost);
        date= (TextView) itemView.findViewById(R.id.date_time);
        company= (TextView) itemView.findViewById(R.id.company);
        team= (TextView) itemView.findViewById(R.id.team);
        foldingCell = (FoldingCell) itemView.findViewById(R.id.folding_cell);

        title.setText(workshops.get(position).getName());
        desc.setText(workshops.get(position).getDesc());
        cost.setText(workshops.get(position).getCost());
        date.setText("Date: "+workshops.get(position).getDate()+"\nTime: "+workshops.get(position).getTime());
        company.setText("Company: "+workshops.get(position).getCompany_name());
        team.setText(workshops.get(position).getTeam());

        foldingCell.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                foldingCell.toggle(false);
            }
        });

        return itemView;
    }
}