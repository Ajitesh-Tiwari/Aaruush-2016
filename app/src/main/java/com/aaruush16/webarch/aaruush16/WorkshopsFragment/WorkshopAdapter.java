package com.aaruush16.webarch.aaruush16.WorkshopsFragment;

import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.content.Context;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.ramotion.foldingcell.FoldingCell;
import com.aaruush16.webarch.aaruush16.R;
import com.aaruush16.webarch.aaruush16.RealmClasses.Workshop;
import com.ramotion.foldingcell.FoldingCell;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

/**
 * Created by Ravi on 03-09-2016.
 */
class WorkshopAdapter extends ArrayAdapter<Workshop> {


    private HashSet<Integer> unfoldedIndexes = new HashSet<>();
    Context context;

    public WorkshopAdapter(Context context, List<Workshop> objects) {
        super(context, 0, objects);
        this.context=context;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // get item for selected view
        Workshop item = getItem(position);
        // if cell is exists - reuse it, if not - create the new one from resource
        FoldingCell cell = (FoldingCell) convertView;
        ViewHolder viewHolder;
        if (cell == null) {
            viewHolder = new ViewHolder();
            LayoutInflater vi = LayoutInflater.from(getContext());
            cell = (FoldingCell) vi.inflate(R.layout.workshop_list_item, parent, false);
            // binding view parts to view holder

            viewHolder.title = (TextView) cell.findViewById(R.id.title);
            viewHolder.desc = (TextView) cell.findViewById(R.id.desc);
            viewHolder.cost = (TextView) cell.findViewById(R.id.cost);
            viewHolder.date = (TextView) cell.findViewById(R.id.date_time);
            viewHolder.company = (TextView) cell.findViewById(R.id.company);
            viewHolder.team = (TextView) cell.findViewById(R.id.team);
            viewHolder.frontDate=(TextView)cell.findViewById(R.id.frontDate);
            viewHolder.frontCost=(TextView)cell.findViewById(R.id.frontCost);
            viewHolder.image=(ImageView)cell.findViewById(R.id.image);
            viewHolder.backImage=(ImageView)cell.findViewById(R.id.backImage);
            cell.setTag(viewHolder);
        } else {
            // for existing cell set valid valid state(without animation)
            if (unfoldedIndexes.contains(position)) {
                cell.unfold(true);
            } else {
                cell.fold(true);
            }
            viewHolder = (ViewHolder) cell.getTag();
        }

        // bind data from selected element to view through view holder
        viewHolder.title.setText(trim(Html.fromHtml(item.getName())));
        viewHolder.desc.setText(trim(Html.fromHtml(item.getDesc())));
        viewHolder.cost.setText(trim(Html.fromHtml(item.getCost())));
        viewHolder.frontCost.setText(trim(Html.fromHtml(item.getCost())));
        viewHolder.date.setText(trim(Html.fromHtml(item.getDate())));
        viewHolder.frontDate.setText(trim(Html.fromHtml(item.getDate())));
        viewHolder.company.setText(trim(Html.fromHtml(item.getCompany_name())));
        viewHolder.team.setText(trim(Html.fromHtml(item.getTeam())));
        Glide.with(context).load("http://aaruush.net/images/workshop/"+item.getImage()).into(viewHolder.image);
        Glide.with(context).load("http://aaruush.net/images/workshop/"+item.getImage()).into(viewHolder.backImage);

        return cell;
    }

    // simple methods for register cell state changes
    public void registerToggle(int position) {
        if (unfoldedIndexes.contains(position))
            registerFold(position);
        else
            registerUnfold(position);
    }

    public void registerFold(int position) {
        unfoldedIndexes.remove(position);
    }

    public void registerUnfold(int position) {
        unfoldedIndexes.add(position);
    }


    // View lookup cache
    private static class ViewHolder {
        TextView title;
        TextView frontDate;
        TextView frontCost;
        TextView desc;
        TextView cost;
        TextView date;
        TextView company;
        TextView team;
        ImageView image;
        ImageView backImage;
    }

    public static CharSequence trim(CharSequence s) {
        int start=0;
        int end=s.length();
        while (start < end && Character.isWhitespace(s.charAt(start))) {
            start++;
        }

        while (end > start && Character.isWhitespace(s.charAt(end - 1))) {
            end--;
        }

        return s.subSequence(start, end);
    }
}
