package com.example.nasa.presentation.ui;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.os.Build;
import android.os.Bundle;

import com.example.nasa.R;

import java.time.YearMonth;

public class MainActivity extends AppCompatActivity {


    private ViewPager mPager;
    private PagerAdapter mPagerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mPager = findViewById(R.id.pager);
        mPagerAdapter = new PageAdapter(getSupportFragmentManager(),0);
        mPager.setAdapter(mPagerAdapter);
    }

    private static class PageAdapter extends FragmentPagerAdapter {

        public PageAdapter(@NonNull FragmentManager fm, int behavior) {
            super(fm, behavior);
        }

        @NonNull
        @Override
        public Fragment getItem(int position) {
            return MainFragment.newInstance(position);
        }

        @Override
        public int getCount() {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                return YearMonth.now().lengthOfMonth();
            }
            return 0;
        }

        @Nullable
        @Override
        public CharSequence getPageTitle(int position) {
            return "Title " + position;
        }
    }
}
