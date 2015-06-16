package com.devddagnet.bright.sample;

import com.devddagnet.bright.sample.widget.SlidingTabLayout;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class BrightActivity extends AppCompatActivity implements ViewPager.OnPageChangeListener {

    private ActionBar        mActionbar;
    private SlidingTabLayout mSlidingTabLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bright);

        mActionbar = this.getSupportActionBar();
        mActionbar.setElevation(0);

        ViewPager viewPager = (ViewPager) findViewById(R.id.pager);
        viewPager.setAdapter(new SectionsPagerAdapter(getSupportFragmentManager(), this));
        viewPager.addOnPageChangeListener(this);

        mSlidingTabLayout = (SlidingTabLayout) this.findViewById(R.id.sliding_tabs);
        mSlidingTabLayout.setViewPager(viewPager);
    }

    @Override
    public void onPageSelected(int position) {
        if (position == 0) {
            mActionbar.show();
            mSlidingTabLayout.setVisibility(View.VISIBLE);
        } else {
            mActionbar.hide();
            mSlidingTabLayout.setVisibility(View.GONE);
        }
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageScrollStateChanged(int state) {
    }
}
