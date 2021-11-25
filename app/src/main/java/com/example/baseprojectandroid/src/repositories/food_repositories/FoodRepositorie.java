package com.example.baseprojectandroid.src.repositories.food_repositories;

import androidx.lifecycle.MutableLiveData;

import com.example.baseprojectandroid.models.food_model.FoodModel;

import java.util.ArrayList;
import java.util.List;

public class FoodRepositorie {
    public static FoodRepositorie mInstanceFoodRepositories;
    private List<FoodModel>mListFood = new ArrayList<>();

    public static FoodRepositorie getInstance(){
        if(mInstanceFoodRepositories == null){
            mInstanceFoodRepositories = new FoodRepositorie();
        }
        return mInstanceFoodRepositories;
    }

    public MutableLiveData<List<FoodModel>> getListFood(){
        MutableLiveData<List<FoodModel>>arrayTmt = new MutableLiveData<>();
        setDataListFood();
        arrayTmt.setValue(mListFood);
        return arrayTmt;
    }

    private void setDataListFood() {
        for (int i = 0 ; i <= 12; i++){
            mListFood.add(new FoodModel("image",25000,"Hamburger"));
        }
    }
}
