package com.example.shopclothesapp.viewmodels;

public class CardProductViewModel {
    private String urlImageProduct;
    private String priceProduct;
    private String nameProduct;

    public CardProductViewModel(String urlImageProduct, String priceProduct, String nameProduct) {
        this.urlImageProduct = urlImageProduct;
        this.priceProduct = priceProduct;
        this.nameProduct = nameProduct;
    }

    public CardProductViewModel() {
    }

    public String getUrlImageProduct() {
        return urlImageProduct;
    }

    public void setUrlImageProduct(String urlImageProduct) {
        this.urlImageProduct = urlImageProduct;
    }

    public String getPriceProduct() {
        return priceProduct;
    }

    public void setPriceProduct(String priceProduct) {
        this.priceProduct = priceProduct;
    }

    public String getNameProduct() {
        return nameProduct;
    }

    public void setNameProduct(String nameProduct) {
        this.nameProduct = nameProduct;
    }
}
