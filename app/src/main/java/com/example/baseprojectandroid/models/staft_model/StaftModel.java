package com.example.baseprojectandroid.models.staft_model;

public class StaftModel {
    private int mId;
    private String mNameStaft;
    private int mAge;
    private String mAddress;
    private String mPhoneNumber;
    private String mRole;
    private String mImage;

    public StaftModel(String mNameStaft, int mAge, String mAddress, String mPhoneNumber, String mRole, String mImage) {
        this.mNameStaft = mNameStaft;
        this.mAge = mAge;
        this.mAddress = mAddress;
        this.mPhoneNumber = mPhoneNumber;
        this.mRole = mRole;
        this.mImage = mImage;
    }

    /*
    constructer defaulf
     */
    public StaftModel(){

    }

    public int getmId() {
        return mId;
    }

    public void setmId(int mId) {
        this.mId = mId;
    }

    public String getmNameStaft() {
        return mNameStaft;
    }

    public void setmNameStaft(String mNameStaft) {
        this.mNameStaft = mNameStaft;
    }

    public int getmAge() {
        return mAge;
    }

    public void setmAge(int mAge) {
        this.mAge = mAge;
    }

    public String getmAddress() {
        return mAddress;
    }

    public void setmAddress(String mAddress) {
        this.mAddress = mAddress;
    }

    public String getmPhoneNumber() {
        return mPhoneNumber;
    }

    public void setmPhoneNumber(String mPhoneNumber) {
        this.mPhoneNumber = mPhoneNumber;
    }

    public String getmRole() {
        return mRole;
    }

    public void setmRole(String mRole) {
        this.mRole = mRole;
    }

    public String getmImage() {
        return mImage;
    }

    public void setmImage(String mImage) {
        this.mImage = mImage;
    }
}
