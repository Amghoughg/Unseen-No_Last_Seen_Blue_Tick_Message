package com.example.unseenmessagesfha.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.example.unseenmessagesfha.R;

import java.util.ArrayList;

public class SliderAdapter extends PagerAdapter {

    private Context context;
    private ArrayList<Integer> data;

    public SliderAdapter(Context context, ArrayList<Integer> data) {
        this.context = context;
        this.data = data;
    }

    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == object;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        LayoutInflater inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.image_slider,null);
        ImageView imageView = view.findViewById(R.id.imageSlider);
        imageView.setImageResource(data.get(position));
        ViewPager viewPager = (ViewPager) container;
        viewPager.addView(view, 0);

        return view;
    }
    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        ViewPager viewPager = (ViewPager) container;
        View view = (View) object;
        viewPager.removeView(view);
    }
}
