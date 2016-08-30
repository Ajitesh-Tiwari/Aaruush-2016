package com.aaruush16.webarch.aaruush16.DomainFragment;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.text.Spanned;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;

import com.aaruush16.webarch.aaruush16.MainActivity;
import com.aaruush16.webarch.aaruush16.R;
import com.aaruush16.webarch.aaruush16.RealmClasses.Event;

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
    public void onBindViewHolder(final MyViewHolder holder, final int position) {
        holder.setData(eventList.get(position));
        holder.event_card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Animation animation= AnimationUtils.loadAnimation(context,R.anim.card_expand);
                animation.setInterpolator(context,android.R.anim.decelerate_interpolator);
                animation.setAnimationListener(new Animation.AnimationListener() {
                    @Override
                    public void onAnimationStart(Animation animation) {


                    }

                    @Override
                    public void onAnimationEnd(Animation animation) {
                        Intent intent=new Intent(context,EventActivity.class);
                        intent.putExtra("id",eventList.get(position).getId());
                        context.startActivity(intent);
                        ((AppCompatActivity)context).overridePendingTransition(0,R.anim.fade_out);
                    }

                    @Override
                    public void onAnimationRepeat(Animation animation) {

                    }
                });

                holder.event_card.startAnimation(animation);


            }
        });
    }


    @Override
    public int getItemCount() {
        return eventList.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {

        TextView description;
        TextView title;
        CircleImageView event_img;
        CardView event_card;

        public MyViewHolder(View itemView) {
            super(itemView);
            description = (TextView) itemView.findViewById(R.id.event_txt_desc);
            event_img= (CircleImageView) itemView.findViewById(R.id.event_img);
            event_card= (CardView) itemView.findViewById(R.id.event_card);
            title= (TextView) itemView.findViewById(R.id.event_txt_title);
        }

        public void setData(Event data) {

            title.setText(data.getName());

            if(data.getContact().compareTo("Nil")!=0) {

                Spanned result;

                if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
                    result = Html.fromHtml(data.getDescription(), Html.FROM_HTML_MODE_LEGACY);
                } else {
                    result = Html.fromHtml(data.getDescription());
                }

                description.setText(result);
            }
            //ImageLoader imageLoader= AppController.getInstance().getImageLoader();
            //imageLoader.get(data.getImageURL(),ImageLoader.getImageListener(event_img,R.drawable.aaruushlogo,R.drawable.wa));


        }
    }
}
