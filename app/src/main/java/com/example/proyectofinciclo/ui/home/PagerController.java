package com.example.proyectofinciclo.ui.home;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.example.proyectofinciclo.TimelineNewsFragment;
import com.example.proyectofinciclo.TimelineTwitterFragment;

public class PagerController extends FragmentPagerAdapter {
    int numtabs;

    public PagerController(@NonNull FragmentManager fm, int numtabs) {
        super(fm, numtabs);
        this.numtabs = numtabs;
    }


    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:
                return new TimelineTwitterFragment();
            case 1:
                return new TimelineNewsFragment();
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return numtabs;
    }
}
