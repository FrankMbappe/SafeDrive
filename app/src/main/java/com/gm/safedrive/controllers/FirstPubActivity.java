package com.gm.safedrive.controllers;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.gm.safedrive.R;
import com.gm.safedrive.controllers.adapters.SliderAdapter;

public class FirstPubActivity extends AppCompatActivity {
    public static final String TAG = "FirstPubActivity";
    public static final int DOT_SIZE = 50;
    private ViewPager mSlideViewPager;
    private LinearLayout mDotsLayout;
    private SliderAdapter mSliderAdapter;
    private Button mBtnPrevious;
    private Button mBtnNext;

    private int mCurrentPage;
    private TextView[] mDots;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first_pub);

        mSlideViewPager = findViewById(R.id.activity_first_pub_viewpager);
        mDotsLayout = findViewById(R.id.activity_first_pub_bottom_layout);
        mBtnPrevious = findViewById(R.id.activity_first_pub_previous_btn);
        mBtnNext = findViewById(R.id.activity_first_pub_next_btn);

        mSliderAdapter = new SliderAdapter(this);
        mSlideViewPager.setAdapter(mSliderAdapter);
        mSlideViewPager.addOnPageChangeListener(viewListener);

        mBtnPrevious.setVisibility(View.GONE);
        mBtnPrevious.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mSlideViewPager.setCurrentItem(mCurrentPage - 1);
            }
        });

        mBtnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mSlideViewPager.setCurrentItem(mCurrentPage + 1);
            }
        });

        addDotsIndicator(0);
    }

    public void addDotsIndicator(int position){
        mDots = new TextView[mSliderAdapter.getCount()];
        mDotsLayout.removeAllViews();
        Log.d(TAG, "addDotsIndicator method invoked. Position value : " + position + ", Dots array length : " + mDots.length);

        for (int i = 0; i < mDots.length; i++){
            mDots[i] = new TextView(this);
            mDots[i].setText(Html.fromHtml("&#8226;"));
            mDots[i].setTextSize(DOT_SIZE);
            if(i != position){
                mDots[i].setTextColor(getResources().getColor(R.color.colorBitDarker));
            }
            else {
                mDots[i].setTextColor(getResources().getColor(R.color.colorPrimary));
            }
            final int finalI = i;
            mDots[i].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mSlideViewPager.setCurrentItem(finalI);
                }
            });

            mDotsLayout.addView(mDots[i]);
        }

    }

    ViewPager.OnPageChangeListener viewListener = new ViewPager.OnPageChangeListener() {
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        }

        @Override
        public void onPageSelected(int position) {
            addDotsIndicator(position);

            mCurrentPage = position;

            if(position == 0){
                mBtnPrevious.setVisibility(View.GONE);
            } else {
                mBtnPrevious.setVisibility(View.VISIBLE);
            }

            if(position == (mDots.length - 1)){
                mBtnNext.setText(getResources().getString(R.string.str_finish));
                mBtnNext.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        startActivity(new Intent(FirstPubActivity.this, VehiclesActivity.class));
                    }
                });
            } else {
                mBtnNext.setText(getResources().getString(R.string.str_next));
                mBtnNext.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        mSlideViewPager.setCurrentItem(mCurrentPage + 1);
                    }
                });
            }

        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    };

}
