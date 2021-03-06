
package com.aaruush16.webarch.aaruush16.DomainFragment;


import android.annotation.TargetApi;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.aaruush16.webarch.aaruush16.R;
import com.github.florent37.materialviewpager.MaterialViewPager;
import com.github.florent37.materialviewpager.header.HeaderDesign;

/**
 * A simple {@link Fragment} subclass.
 */
public class DomainFragment extends Fragment{

    MaterialViewPager materialViewPager;
    private Toolbar toolbar;

    public DomainFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.fragment_domain, container, false);
        ((AppCompatActivity)getActivity()).getSupportActionBar().setTitle("Domain");
        materialViewPager= (MaterialViewPager) view.findViewById(R.id.materialViewPager);
        materialViewPager.getViewPager().setAdapter(new FragmentStatePagerAdapter(getChildFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                DomainPagerFragment domainPagerFragment=new DomainPagerFragment();
                String txt="";
                switch (position){
                    case 0:txt="architecture";
                        break;
                    case 1:txt="bluebook";
                        break;
                    case 2:txt="digital_design";
                        break;
                    case 3:txt="electrizite";
                        break;
                    case 4:txt="fundaz";
                        break;
                    case 5:txt="konstruktion";
                        break;
                    case 6:txt="machination";
                        break;
                    case 7:txt="magefficie";
                        break;
                    case 8:txt="online";
                        break;
                    case 9:txt="praesentatio";
                        break;
                    case 10:txt="robogyan";
                        break;
                    case 11:txt="vimanaz";
                        break;
                    case 12:txt="xzone";
                        break;
                    case 13:txt="yuddhame";
                        break;
                    case 14:txt="special_event";
                }

                domainPagerFragment.setData(txt);
                return domainPagerFragment;
            }

            @Override
            public int getCount() {
                return 15;
            }

            @Override
            public CharSequence getPageTitle(int position) {
                String txt=null;
                switch (position){
                    case 0:txt="architecture";
                        break;
                    case 1:txt="bluebook";
                        break;
                    case 2:txt="digital design";
                        break;
                    case 3:txt="electrizite";
                        break;
                    case 4:txt="fundaz";
                        break;
                    case 5:txt="konstruktion";
                        break;
                    case 6:txt="machination";
                        break;
                    case 7:txt="magefficie";
                        break;
                    case 8:txt="online";
                        break;
                    case 9:txt="praesentatio";
                        break;
                    case 10:txt="robogyan";
                        break;
                    case 11:txt="vimanaz";
                        break;
                    case 12:txt="x-Zone";
                        break;
                    case 13:txt="yuddhame";
                        break;
                    case 14:txt="special event";

                }
                return txt;
            }
        });

        materialViewPager.setMaterialViewPagerListener(new MaterialViewPager.Listener() {
            @TargetApi(Build.VERSION_CODES.LOLLIPOP)
            @Override
            public HeaderDesign getHeaderDesign(int page) {
                int color=R.color.red;
                Drawable drawable=getResources().getDrawable(R.drawable.logo_white);
                switch (page) {
                    case 0:color=R.color.green;
                        drawable=getResources().getDrawable(R.drawable.architecture);
                        break;
                    case 1:color=R.color.blue;
                        drawable=getResources().getDrawable(R.drawable.bluebook);
                        break;
                    case 2:color=R.color.cyan;
                        drawable=getResources().getDrawable(R.drawable.digital_design);
                        break;
                    case 3:color=R.color.red;
                        drawable=getResources().getDrawable(R.drawable.electrizite);
                        break;
                    case 4:color=R.color.green;
                        drawable=getResources().getDrawable(R.drawable.fundaaz);
                        break;
                    case 5:color=R.color.blue;
                        drawable=getResources().getDrawable(R.drawable.konstruction);
                        break;
                    case 6:color=R.color.cyan;
                        drawable=getResources().getDrawable(R.drawable.machination);
                        break;
                    case 7:color=R.color.red;
                        drawable=getResources().getDrawable(R.drawable.mageficie);
                        break;
                    case 8:color=R.color.green;
                        drawable=getResources().getDrawable(R.drawable.online);
                        break;
                    case 9:color=R.color.blue;
                        drawable=getResources().getDrawable(R.drawable.presentatio);
                        break;
                    case 10:color=R.color.cyan;
                        drawable=getResources().getDrawable(R.drawable.robogyan);
                        break;
                    case 11:color=R.color.red;
                        drawable=getResources().getDrawable(R.drawable.vimanaz);
                        break;
                    case 12:color=R.color.green;
                        drawable=getResources().getDrawable(R.drawable.xzone);
                        break;
                    case 13:color=R.color.blue;
                        drawable=getResources().getDrawable(R.drawable.yudhamme);
                        break;
                    case 14:
                        color=R.color.cyan;
                        drawable=getResources().getDrawable(R.drawable.digital_design);
                        break;
                }
                return HeaderDesign.fromColorAndDrawable(getResources().getColor(color),drawable);
            }
        });

        materialViewPager.getViewPager().setOffscreenPageLimit(materialViewPager.getViewPager().getAdapter().getCount());
        materialViewPager.getPagerTitleStrip().setViewPager(materialViewPager.getViewPager());
        materialViewPager.getToolbar().removeAllViews();
        return view;
    }
//    public void onRefresh() {
//
//        DataFetcher dataFetcher=new DataFetcher();
//        try{
//            dataFetcher.fetchJSON(getActivity());
//        }catch (Exception e){
//        }finally {
//            swipeRefreshLayout.setRefreshing(false);
//        }
//    }

}