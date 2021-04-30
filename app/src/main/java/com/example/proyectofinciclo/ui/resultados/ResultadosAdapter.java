package com.example.proyectofinciclo.ui.resultados;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

public class ResultadosAdapter extends FragmentStatePagerAdapter {

    public ResultadosAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int i) {
        Fragment fragment;
        switch(i) {
            case 0:
                fragment = new CalendarioFragment();
                break;
            case 1:
                fragment = new ClasiFragment();
                break;
            default:
                fragment = null;
        }
        return fragment;
    }

    @Override
    public int getCount() {
        return 2;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        switch (position) {
            case 0:
                return "Calendario";
            case 1:
                return "Clasificación";
        }
        return null;
    }

}
