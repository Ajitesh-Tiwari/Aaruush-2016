package com.aaruush16.webarch.aaruush16.DomainFragment;


import android.annotation.TargetApi;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.content.ContextCompat;
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
public class DomainFragment extends Fragment {

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
        materialViewPager= (MaterialViewPager) view.findViewById(R.id.materialViewPager);
        ((AppCompatActivity)getActivity()).getSupportActionBar().setTitle("Domains");
        materialViewPager.getViewPager().setAdapter(new FragmentStatePagerAdapter(getChildFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                DomainPagerFragment domainPagerFragment=new DomainPagerFragment();
                String txt="";
                switch (position){
                    case 0:txt="Architecture";
                        break;
                    case 1:txt="Bluebook";
                        break;
                    case 2:txt="Digital Design";
                        break;
                    case 3:txt="Electrizite";
                        break;
                    case 4:txt="Fundaaz";
                        break;
                    case 5:txt="Konstruction";
                        break;
                    case 6:txt="Machination";
                        break;
                    case 7:txt="Mageficie";
                        break;
                    case 8:txt="Online";
                        break;
                    case 9:txt="Presentatio";
                        break;
                    case 10:txt="Robogyan";
                        break;
                    case 11:txt="Vimanaz";
                        break;
                    case 12:txt="X-Zone";
                        break;
                    case 13:txt="Yudhamme";
                        break;
                }

                domainPagerFragment.setData(txt);
                return domainPagerFragment;
            }

            @Override
            public int getCount() {
                return 14;
            }

            @Override
            public CharSequence getPageTitle(int position) {
                String txt=null;
                switch (position){
                    case 0:txt="Architecture";
                        break;
                    case 1:txt="Bluebook";
                        break;
                    case 2:txt="Digital Design";
                        break;
                    case 3:txt="Electrizite";
                        break;
                    case 4:txt="Fundaaz";
                        break;
                    case 5:txt="Konstruction";
                        break;
                    case 6:txt="Machination";
                        break;
                    case 7:txt="Mageficie";
                        break;
                    case 8:txt="Online";
                        break;
                    case 9:txt="Presentatio";
                        break;
                    case 10:txt="Robogyan";
                        break;
                    case 11:txt="Vimanaz";
                        break;
                    case 12:txt="X-Zone";
                        break;
                    case 13:txt="Yudhamme";
                        break;

                }
                return txt;
            }
        });

        materialViewPager.setMaterialViewPagerListener(new MaterialViewPager.Listener() {
            @TargetApi(Build.VERSION_CODES.LOLLIPOP)
            @Override
            public HeaderDesign getHeaderDesign(int page) {
                int color=R.color.red;
                Drawable drawable=getResources().getDrawable(R.drawable.logo_white,null);
                switch (page) {
                    case 0:color=R.color.green;
                        drawable=getResources().getDrawable(R.drawable.architecture,null);
                        break;
                    case 1:color=R.color.blue;
                        drawable=getResources().getDrawable(R.drawable.bluebook,null);
                        break;
                    case 2:color=R.color.cyan;
                        drawable=getResources().getDrawable(R.drawable.digital_design,null);
                        break;
                    case 3:color=R.color.red;
                        drawable=getResources().getDrawable(R.drawable.electrizite,null);
                        break;
                    case 4:color=R.color.green;
                        drawable=getResources().getDrawable(R.drawable.fundaaz,null);
                        break;
                    case 5:color=R.color.blue;
                        drawable=getResources().getDrawable(R.drawable.konstruction,null);
                        break;
                    case 6:color=R.color.cyan;
                        drawable=getResources().getDrawable(R.drawable.machination,null);
                        break;
                    case 7:color=R.color.red;
                        drawable=getResources().getDrawable(R.drawable.mageficie,null);
                        break;
                    case 8:color=R.color.green;
                        drawable=getResources().getDrawable(R.drawable.online,null);
                        break;
                    case 9:color=R.color.blue;
                        drawable=getResources().getDrawable(R.drawable.presentatio,null);
                        break;
                    case 10:color=R.color.cyan;
                        drawable=getResources().getDrawable(R.drawable.robogyan,null);
                        break;
                    case 11:color=R.color.red;
                        drawable=getResources().getDrawable(R.drawable.vimanaz,null);
                        break;
                    case 12:color=R.color.green;
                        drawable=getResources().getDrawable(R.drawable.xzone,null);
                        break;
                    case 13:color=R.color.blue;
                        drawable=getResources().getDrawable(R.drawable.yudhamme,null);
                        break;


                }
                return HeaderDesign.fromColorAndDrawable(ContextCompat.getColor(getContext(),color),drawable);
            }
        });

        materialViewPager.getViewPager().setOffscreenPageLimit(materialViewPager.getViewPager().getAdapter().getCount());
        materialViewPager.getPagerTitleStrip().setViewPager(materialViewPager.getViewPager());
        materialViewPager.getToolbar().removeAllViews();
        return view;
    }


}
