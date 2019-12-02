package com.example.presentation.ui;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.content.Context;
import android.content.res.Resources;
import android.os.Bundle;

import com.example.presentation.R;
import com.example.presentation.utils.DateUtils;

public class MainActivity extends AppCompatActivity {


    private ViewPager mPager;
    private PagerAdapter mPagerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mPager = findViewById(R.id.pager);
        mPagerAdapter = new PageAdapter(getSupportFragmentManager(), 0, this);
        mPager.setAdapter(mPagerAdapter);
    }

    private static class PageAdapter extends FragmentStatePagerAdapter {

        private final Resources mResources;

        PageAdapter(@NonNull FragmentManager fm, int behavior, Context context) {
            super(fm, behavior);
            mResources = context.getResources();
        }

        @NonNull
        @Override
        public Fragment getItem(int position) {
            return MainFragment.newInstance(position);
        }

        @Override
        public int getCount() {
            return DateUtils.getLengthOfYear();
        }

        @Nullable
        @Override
        public CharSequence getPageTitle(int position) {
            return mResources.getString(R.string.astronomy_picture, DateUtils.getDateOffset(position));

        }
    }
}
