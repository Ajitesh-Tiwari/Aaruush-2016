package com.aaruush16.webarch.aaruush16.WorkshopsFragment;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.aaruush16.webarch.aaruush16.R;
import com.aaruush16.webarch.aaruush16.RealmClasses.Workshop;
import com.ramotion.foldingcell.FoldingCell;

import java.util.List;

/**
 * Created by Rishi on 27-08-2016.
 */
public class WorkshopListAdapter extends RecyclerView.Adapter<WorkshopListAdapter.MyViewHolder> {

    Context context;
    List<Workshop> workshopList;

    public WorkshopListAdapter(Context context, List<Workshop> workshopList){
        this.context=context;
        this.workshopList=workshopList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.workshop_list_item, parent, false);
        MyViewHolder holder = new MyViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        holder.setData(workshopList.get(position));
    }

    @Override
    public int getItemCount() {
        return workshopList.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {


        TextView title;
        TextView desc;
        TextView cost;
        TextView date;
        TextView company;
        TextView team;
        FoldingCell foldingCell;


        public MyViewHolder(View itemView) {
            super(itemView);
            title= (TextView) itemView.findViewById(R.id.title);
            desc= (TextView) itemView.findViewById(R.id.desc);
            cost= (TextView) itemView.findViewById(R.id.cost);
            date= (TextView) itemView.findViewById(R.id.date_time);
            company= (TextView) itemView.findViewById(R.id.company);
            team= (TextView) itemView.findViewById(R.id.team);
            foldingCell = (FoldingCell) itemView.findViewById(R.id.folding_cell);
        }

        public void setData(Workshop data) {

            title.setText(data.getName());
            desc.setText(data.getDesc());
            cost.setText(data.getCost());
            date.setText("Date: "+Html.fromHtml(data.getDate()));
            company.setText("Company: "+data.getCompany_name());
            team.setText(data.getTeam());
            foldingCell.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    foldingCell.toggle(false);
                }
            });

/*
            if(data.getDescription()!=null) {

                Spanned result;

                if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
                    result = Html.fromHtml(data.getDescription(), Html.FROM_HTML_MODE_LEGACY);
                } else {
                    result = Html.fromHtml(data.getDescription());
                }

                description.setText(result);
            }
*/
        }
    }

}
