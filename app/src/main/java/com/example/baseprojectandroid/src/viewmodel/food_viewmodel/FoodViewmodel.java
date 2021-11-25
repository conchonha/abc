package com.example.baseprojectandroid.src.viewmodel.food_viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.baseprojectandroid.models.food_model.FoodModel;
import com.example.baseprojectandroid.src.repositories.food_repositories.FoodRepositorie;

import java.util.List;

public class FoodViewmodel extends ViewModel {
    private FoodRepositorie mFoodRepositorie;
    private MutableLiveData<List<FoodModel>>mListFood;

    public void initListFood(){
        if(mListFood != null){
            return;
        }
        mFoodRepositorie = FoodRepositorie.getInstance();
        mListFood = mFoodRepositorie.getListFood();
    }

    public LiveData<List<FoodModel>> getListFood(){
        return mListFood;
    }
}
