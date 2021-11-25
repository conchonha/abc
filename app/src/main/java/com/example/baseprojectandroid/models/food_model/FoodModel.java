package com.example.baseprojectandroid.models.food_model;

public class FoodModel {
    private int mId;
    private String mImage;
    private int mPrice;
    private String mName;

    public FoodModel( String mImage, int mPrice, String mName) {
        this.mImage = mImage;
        this.mPrice = mPrice;
        this.mName = mName;
    }

    public FoodModel(){

    }

    public int getmId() {
        return mId;
    }

    public void setmId(int mId) {
        this.mId = mId;
    }

    public String getmImage() {
        return mImage;
    }

    public void setmImage(String mImage) {
        this.mImage = mImage;
    }

    public int getmPrice() {
        return mPrice;
    }

    public void setmPrice(int mPrice) {
        this.mPrice = mPrice;
    }

    public String getmName() {
        return mName;
    }

    public void setmName(String mName) {
        this.mName = mName;
    }
}
