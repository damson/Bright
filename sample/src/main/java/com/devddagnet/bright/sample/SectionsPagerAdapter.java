package com.devddagnet.bright.sample;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

public class SectionsPagerAdapter extends FragmentPagerAdapter {

    private final String[] urls = new String[]{
            "http://i.imgur.com/CqmBjo5.jpg",
            "http://i.imgur.com/zkaAooq.jpg",
            "http://i.imgur.com/0gqnEaY.jpg",
            "http://i.imgur.com/P5JLfjk.jpg",
            "http://i.imgur.com/nz67a4F.jpg",
            "http://i.imgur.com/dFH34N5.jpg",
            "http://i.imgur.com/FI49ftb.jpg",
            "http://i.imgur.com/DvpvklR.jpg",
            "http://i.imgur.com/DNKnbG8.jpg",
            "http://i.imgur.com/9gbQ7YR.jpg"
    };
    private final Context mContext;


    public SectionsPagerAdapter(FragmentManager fm, Context context) {
        super(fm);

        mContext = context;
    }

    @Override
    public Fragment getItem(int position) {
        if (position == 0) {
            return MultiColorFragment.newInstance();
        }
        return PictureFragment.newInstance(urls[position]);
    }

    @Override
    public int getCount() {
        return urls.length;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        if (position == 0) {
            return mContext.getString(R.string.title_section1);
        }
        return mContext.getString(R.string.title_section2);
    }
}