package com.aaruush16.webarch.aaruush16.HomeFragment;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.aaruush16.webarch.aaruush16.R;
import com.bumptech.glide.Glide;

import java.util.List;

/**
 * Created by ajitesh on 4/9/16.
 */
public class CommentAdapter extends RecyclerView.Adapter<CommentAdapter.MyViewHolder> {

    private List<Comment> comments;
    Context context;

    public CommentAdapter(Context context,List<Comment> comments){
        this.comments=comments;
        this.context=context;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.comment_row, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        Comment comment = comments.get(position);
        holder.name.setText(comment.getName());
        holder.email.setText(comment.getEmail());
        holder.comment.setText(comment.getComment());
        if(comment.getPhoto().length()>0)
            Glide.with(context).load(comment.getPhoto()).placeholder(R.drawable.placeholder).into(holder.photo);
        else
            holder.photo.setImageDrawable(ContextCompat.getDrawable(context,R.drawable.placeholder));
    }

    @Override
    public int getItemCount() {
        return comments.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView name, email, comment;
        public ImageView photo;

        public MyViewHolder(View view) {
            super(view);
            name= (TextView) view.findViewById(R.id.name);
            email=(TextView) view.findViewById(R.id.email);
            comment= (TextView) view.findViewById(R.id.comment);
            photo= (ImageView) view.findViewById(R.id.photo);
        }
    }
}