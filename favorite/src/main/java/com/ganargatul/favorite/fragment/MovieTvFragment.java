package com.ganargatul.favorite.fragment;


import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import com.ganargatul.favorite.R;
import com.google.android.material.tabs.TabLayout;

/**
 * A simple {@link Fragment} subclass.
 */
public class MovieTvFragment extends Fragment {

    TabLayout mTabLayout;
    ViewPager mViewPager;
    public MovieTvFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v =inflater.inflate(R.layout.fragment_movie_tv, container, false);
        mTabLayout = v.findViewById(R.id.tabs_fav);
        mViewPager = v.findViewById(R.id.viewpager_fav);
        LinearLayoutManager mLinearLayoutManager = new LinearLayoutManager(getActivity());
        mLinearLayoutManager.setOrientation(RecyclerView.VERTICAL);
        mTabLayout.addTab(mTabLayout.newTab().setText(getResources().getString(R.string.fav_movie)));
        mTabLayout.addTab(mTabLayout.newTab().setText(getResources().getString(R.string.fav_tv)));
        mViewPager.setAdapter(new viewpageradapter(getFragmentManager(),mTabLayout.getTabCount()));
        mViewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(mTabLayout));
        mTabLayout.setupWithViewPager(mViewPager);
        mTabLayout.setTabMode(TabLayout.MODE_FIXED);
        mTabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                mViewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
        return v;
    }

    private class viewpageradapter extends FragmentStatePagerAdapter {
        int mNumofTabs;
        public viewpageradapter(FragmentManager fm,int mNumOfTabs) {
            super(fm);
            this.mNumofTabs = mNumOfTabs;

        }

        @Override
        public Fragment getItem(int position) {
            switch (position){
                case 1:
                    return new MovieFav();

                case 2:
                    return new MovieFav();
                    default:
                        return null;
            }
        }

        @Override
        public int getCount() {
            return mNumofTabs;
        }
    }
}
