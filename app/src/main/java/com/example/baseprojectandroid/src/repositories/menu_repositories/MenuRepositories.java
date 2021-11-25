package com.example.baseprojectandroid.src.repositories.menu_repositories;

import androidx.lifecycle.MutableLiveData;

import com.example.baseprojectandroid.models.menu_model.MenuModel;

import java.util.ArrayList;
import java.util.List;

public class MenuRepositories {
    private static  MenuRepositories instanceMenu;
    private static List<MenuModel>mListMenu = new ArrayList<>();
    private static List<String>mListTitle = new ArrayList<>();

    public static MenuRepositories getInstance(){
        if (instanceMenu == null){
            instanceMenu = new MenuRepositories();
        }
        return instanceMenu;
    }

    public MutableLiveData<List<MenuModel>> getListMenu(){
        MutableLiveData<List<MenuModel>> arrayTmp = new MutableLiveData<>();
        setDataMenu();
        arrayTmp.setValue(mListMenu);
        return arrayTmp;
    }

    public void setDataMenu(){
        mListMenu.clear();
        for (int i = 0; i <= 2; i++){
            MenuModel models = new MenuModel();
            models.setmImage("https://images.media-allrecipes.com/userphotos/453291.jpg");
            models.setmName("Ice Cream");
            models.setmMoney(2500);
            mListMenu.add(models);
        }
    }

    public MutableLiveData<List<String>>getListTitle(){
        MutableLiveData<List<String>>arrayTmt = new MutableLiveData<>();
        setDataAddnew();
        arrayTmt.setValue(mListTitle);
        return arrayTmt;
    }

    public void setDataAddnew(){
        mListTitle.clear();
        mListTitle.add("Đồ uống");
        mListTitle.add("Thức ăn");
        mListTitle.add("Combo");
        mListTitle.add("Khác");
    }
}
