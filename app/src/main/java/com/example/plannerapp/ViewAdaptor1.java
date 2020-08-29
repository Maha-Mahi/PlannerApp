package com.example.plannerapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

public class ViewAdaptor1 extends PagerAdapter {

    private Context context;
    private LayoutInflater layoutInflater;
    private Integer[] iamges={R.drawable.valima1,R.drawable.valima2,R.drawable.valima3,R.drawable.valima4};

    ViewAdaptor1(Context context){
        this.context=context;
    }

    @Override
    public int getCount() {
        return iamges.length;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == object;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        layoutInflater = (LayoutInflater) context.getSystemService(
                Context.LAYOUT_INFLATER_SERVICE);
        View view=layoutInflater.inflate(R.layout.item3,null);
        ImageView imageView=view.findViewById(R.id.imagevalima);
        imageView.setImageResource(iamges[position]);
        ViewPager viewPager=(ViewPager) container;
        viewPager.addView(view,0);
        return view;

    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        ViewPager viewPager=(ViewPager) container;
        View view=(View) object;
        viewPager.removeView(view);
    }
}
