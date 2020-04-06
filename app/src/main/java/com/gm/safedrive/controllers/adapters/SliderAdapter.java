package com.gm.safedrive.controllers.adapters;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.viewpager.widget.PagerAdapter;

import com.gm.safedrive.R;

import de.hdodenhof.circleimageview.CircleImageView;

public class SliderAdapter extends PagerAdapter {
    public static final String TAG = "SliderAdapter";
    Context mContext;

    public int[] slideImages;
    public String[] slideTitles;
    public String[] slideDescriptions;


    public SliderAdapter(Context context){
        this.mContext = context;
        slideImages = new int[]{
                R.drawable.pub_safedrive_everywhere,
                R.drawable.pub_no_matter_your_vehicle
        };
        slideTitles = new String[]{
                mContext.getResources().getString(R.string.pub_fl_1_title),
                mContext.getResources().getString(R.string.pub_fl_2_title)
        };
        slideDescriptions = new String[]{
                mContext.getResources().getString(R.string.pub_fl_1_description),
                mContext.getResources().getString(R.string.pub_fl_2_description)
        };
    }


    @Override
    public int getCount() {
        Log.d(TAG, "getCount method invoked. slideTitles.length = " + slideTitles.length);
        return slideTitles.length;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == object;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.slide_first_launch, container, false);

        CircleImageView image = view.findViewById(R.id.slide_fl_image);
        TextView title = view.findViewById(R.id.slide_fl_title);
        TextView description = view.findViewById(R.id.slide_fl_description);

        image.setImageResource(slideImages[position]);
        title.setText(slideTitles[position]);
        description.setText(slideDescriptions[position]);

        container.addView(view);
        Log.d(TAG, "instantiateItem: method invoked.");

        return view;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((ConstraintLayout) object);
        Log.d(TAG, "destroyItem: method invoked.");
    }
}
