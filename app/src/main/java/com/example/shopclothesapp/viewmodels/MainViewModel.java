package com.example.shopclothesapp.viewmodels;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.shopclothesapp.repositories.Slider;

import java.util.ArrayList;
import java.util.List;

public class MainViewModel extends ViewModel {
    private final MutableLiveData<List<Slider>> slidersLiveData;
    private List<Slider> sliders;
    public MainViewModel() {
        slidersLiveData = new MutableLiveData<>();
        sliders = new ArrayList<>();
        slidersLiveData.setValue(sliders);
    }

    public MutableLiveData<List<Slider>> getSliderLiveData() {
        return slidersLiveData;
    }
    public void setSliders(List<Slider> sliders) {
        this.sliders = sliders;
        slidersLiveData.setValue(sliders);
    }
}
