package com.example.shopclothesapp.repositories;

public class Slider {
    public Slider(String urlImage) {
        this.urlImage = urlImage;
    }

    public Slider() {
    }

    private String urlImage;

    public String getUrlImage() {
        return urlImage;
    }

    public void setUrlImage(String urlImage) {
        this.urlImage = urlImage;
    }
}
