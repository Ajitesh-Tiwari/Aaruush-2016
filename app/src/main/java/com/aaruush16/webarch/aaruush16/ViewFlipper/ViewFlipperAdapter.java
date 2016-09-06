package com.aaruush16.webarch.aaruush16.ViewFlipper;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.aaruush16.webarch.aaruush16.R;
import com.aaruush16.webarch.aaruush16.VolleySingleton.AppController;
import com.android.volley.toolbox.ImageLoader;
import com.bumptech.glide.Glide;
import com.yalantis.flipviewpager.adapter.BaseFlipAdapter;
import com.yalantis.flipviewpager.utils.FlipSettings;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by Rishi on 03-09-2016.
 */
public class ViewFlipperAdapter extends BaseFlipAdapter {

    Context context;
    List<ViewFlipperItem> viewFlipperItems;
        private final int PAGES = 3;
        private int[] IDS_INTEREST = {R.id.interest_1, R.id.interest_2, R.id.interest_3, R.id.interest_4, R.id.interest_5};



    public ViewFlipperAdapter(Context context, List<ViewFlipperItem> items, FlipSettings settings) {
        super(context, items, settings);
        this.context=context;
        this.viewFlipperItems=items;
    }

    @Override
    public View getPage(int position, View convertView, ViewGroup parent, Object item1, Object item2) {
        final Holder holder;

        if (convertView == null) {
            holder = new Holder();
            convertView = ((Activity)context).getLayoutInflater().inflate(R.layout.view_flipper_list_item, parent, false);
            holder.leftAvatar = (ImageView) convertView.findViewById(R.id.first);
            holder.rightAvatar = (ImageView) convertView.findViewById(R.id.second);
            holder.infoPage = ((Activity)context).getLayoutInflater().inflate(R.layout.view_flipper_info, parent, false);
            holder.nickName = (TextView) holder.infoPage.findViewById(R.id.nickname);

            for (int id : IDS_INTEREST)
                holder.interests.add((TextView) holder.infoPage.findViewById(id));

            convertView.setTag(holder);
        } else {
            holder = (Holder) convertView.getTag();
        }

        switch (position) {
            // Merged page with 2 friends
            case 1:
                if(((ViewFlipperItem) item1).getAvatar()==0){
                    ImageLoader imageLoader= AppController.getInstance().getImageLoader();
                    //imageLoader.get("http://aaruush.net/testing123/images/team/"+((ViewFlipperItem) item1).img_id,ImageLoader.getImageListener(holder.leftAvatar,R.drawable.com_facebook_profile_picture_blank_portrait,R.mipmap.ic_launcher));
                    Glide.with(context).load("http://aaruush.net/testing123/images/team/"+((ViewFlipperItem) item1).img_id).into(holder.leftAvatar);
                    if(item2!=null){
                        //imageLoader.get("http://aaruush.net/testing123/images/team/"+((ViewFlipperItem) item2).img_id,ImageLoader.getImageListener(holder.rightAvatar,R.drawable.com_facebook_profile_picture_blank_portrait,R.mipmap.ic_launcher));
                        Glide.with(context).load("http://aaruush.net/testing123/images/team/"+((ViewFlipperItem) item2).img_id).into(holder.rightAvatar);
                    }
                }else {
                    holder.leftAvatar.setImageResource(((ViewFlipperItem) item1).getAvatar());
                    if (item2 != null) {
                        holder.rightAvatar.setImageResource(((ViewFlipperItem) item2).getAvatar());
                    }
                }
                break;
            default:
                fillHolder(holder, position == 0 ? (ViewFlipperItem) item1 : (ViewFlipperItem) item2);
                holder.infoPage.setTag(holder);
                return holder.infoPage;
        }
        return convertView;
    }

    @Override
    public int getPagesCount() {
        return PAGES;
    }

    private void fillHolder(Holder holder, ViewFlipperItem friend) {
            if (friend == null)
                return;
            Iterator<TextView> iViews = holder.interests.iterator();
            Iterator<String> iInterests = friend.getInterests().iterator();
            while (iViews.hasNext() && iInterests.hasNext()) {
                TextView txt=iViews.next();
                txt.setText(iInterests.next());
                txt.setVisibility(View.VISIBLE);

            }
            holder.infoPage.setBackgroundColor(context.getResources().getColor(friend.getBackground()));
            holder.nickName.setText(friend.getNickname());
        }

    class Holder {
            ImageView leftAvatar;
            ImageView rightAvatar;
            View infoPage;

            List<TextView> interests = new ArrayList<>();
            TextView nickName;
    }
}
