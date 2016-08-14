package com.aaruush16.webarch.aaruush16.DomainFragment;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.aaruush16.webarch.aaruush16.R;
import com.aaruush16.webarch.aaruush16.RealmClasses.Event;
import com.aaruush16.webarch.aaruush16.VolleySingleton.AppController;
import com.android.volley.toolbox.ImageLoader;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by Rishi on 14-08-2016.
 */
public class EventListAdapter extends RecyclerView.Adapter<EventListAdapter.MyViewHolder>{

    Context context;
    List<Event> eventList;

    public EventListAdapter(Context context, List<Event> eventList){
        this.context=context;
        this.eventList=eventList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.event_list_item, parent, false);
        MyViewHolder holder = new MyViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {
        holder.setData(eventList.get(position));
        holder.event_card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(context,EventActivity.class);
                intent.putExtra("id",eventList.get(position).getId());
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return eventList.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {

        TextView description;
        CircleImageView event_img;
        CardView event_card;

        public MyViewHolder(View itemView) {
            super(itemView);


            description = (TextView) itemView.findViewById(R.id.event_txt_desc);
            event_img= (CircleImageView) itemView.findViewById(R.id.event_img);
            event_card= (CardView) itemView.findViewById(R.id.event_card);
        }

        public void setData(Event data) {

            description.setText(data.getDescription());
            ImageLoader imageLoader= AppController.getInstance().getImageLoader();
            imageLoader.get(data.getImageURL(),ImageLoader.getImageListener(event_img,R.drawable.aaruushlogo,R.drawable.wa));


        }
    }
}
