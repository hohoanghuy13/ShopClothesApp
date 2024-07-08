package com.example.shopclothesapp.viewmodels;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.shopclothesapp.repositories.Categories;
import com.example.shopclothesapp.repositories.Products;
import com.example.shopclothesapp.repositories.Slider;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class MainViewModel extends ViewModel {
    private final MutableLiveData<List<Slider>> slidersLiveData;
    private final MutableLiveData<List<CardProductViewModel>> cardsLiveData;
    FirebaseDatabase database;

    public MutableLiveData<List<CardProductViewModel>> getCardsLiveData() {
        return cardsLiveData;
    }

    private List<Slider> sliders;
    private List<CardProductViewModel> cardProducts;
    public MainViewModel() {
        slidersLiveData = new MutableLiveData<>();
        cardsLiveData = new MutableLiveData<>();
        database = FirebaseDatabase.getInstance();

        sliders = new ArrayList<>();
        initData();
        slidersLiveData.setValue(sliders);
    }

    public MutableLiveData<List<Slider>> getSliderLiveData() {
        return slidersLiveData;
    }
    public void setSliders(List<Slider> sliders) {
        this.sliders = sliders;
        slidersLiveData.setValue(sliders);
    }

    public void initData() {
        cardProducts = new ArrayList<>();
        List<Products> products = new ArrayList<>();
        DatabaseReference myReferences = database.getReference("products");

        myReferences.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                for(DataSnapshot dataProduct : dataSnapshot.getChildren()) {
                    Products product = dataProduct.getValue(Products.class);

                    if(product != null) {
                        String id = dataProduct.getKey();
                        Log.d("TAG", "Key is: " + id + ". Image: " + product.getImages().get(0));
                        products.add(product);
                        cardProducts.add(new CardProductViewModel(product.getImages().get(0), String.valueOf(product.getPrice()), product.getProductName()));
                        cardsLiveData.setValue(cardProducts);
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                // Failed to read value
                Log.w("TAG", "Failed to read value.", error.toException());
            }
        });
    }
}
