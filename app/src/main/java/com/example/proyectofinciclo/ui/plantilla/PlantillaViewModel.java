package com.example.proyectofinciclo.ui.plantilla;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class PlantillaViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public PlantillaViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is notifications fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}