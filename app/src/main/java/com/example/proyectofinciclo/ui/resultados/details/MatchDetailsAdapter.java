package com.example.proyectofinciclo.ui.resultados.details;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.example.proyectofinciclo.ui.resultados.ClasiFragment;

public class MatchDetailsAdapter extends FragmentStatePagerAdapter {

    public MatchDetailsAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int i) {
        Fragment fragment;
        switch(i) {
            case 0:
                fragment = new TwitterFragment();
                break;
            case 1:
                fragment = new ClasiFragment();
                break;
            case 2:
                fragment = new ClasiFragment();
                break;
            case 3:
                fragment = new ClasiFragment();
                break;
            default:
                fragment = null;
        }
        return fragment;
    }

    @Override
    public int getCount() {
        return 1;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        switch (position) {
            case 0:
                return "";
            case 1:
                return "Previa";
            case 2:
                return "Live";
            case 3:
                return "ALGO MAS";
        }
        return null;
    }

}
