package com.example.baseprojectandroid.models.menu_model;

public class MenuModel {
    private int id;
    private String mImage;
    private String mName;
    private double mMoney;
    private String mType;
    private int mNumber;

    public MenuModel(String mImage, String mName, double mMoney, String mType, int mNumber) {
        this.mImage = mImage;
        this.mName = mName;
        this.mMoney = mMoney;
        this.mType = mType;
        this.mNumber = mNumber;
    }

    public MenuModel(){

    }

    public int getmNumber() {
        return mNumber;
    }

    public void setmNumber(int mNumber) {
        this.mNumber = mNumber;
    }

    public double getmMoney() {
        return mMoney;
    }

    public void setmMoney(double mMoney) {
        this.mMoney = mMoney;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getmType() {
        return mType;
    }

    public void setmType(String mType) {
        this.mType = mType;
    }

    public String getmImage() {
        return mImage;
    }

    public void setmImage(String mImage) {
        this.mImage = mImage;
    }

    public String getmName() {
        return mName;
    }

    public void setmName(String mName) {
        this.mName = mName;
    }

}
