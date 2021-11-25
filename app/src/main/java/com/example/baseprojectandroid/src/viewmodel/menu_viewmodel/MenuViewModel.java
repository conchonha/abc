package com.example.baseprojectandroid.src.viewmodel.menu_viewmodel;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.baseprojectandroid.models.menu_model.MenuModel;
import com.example.baseprojectandroid.src.repositories.menu_repositories.MenuRepositories;

import java.util.List;

public class MenuViewModel extends ViewModel {
    private MutableLiveData<List<MenuModel>> mArrayMenu;
    private MenuRepositories mMenuRepositories;
    private MutableLiveData<List<String>>mArrayListTitle;

    //khởi tạo và get data menu
    public void initMenu() {
        if (mArrayMenu != null) {
            return;
        }
        mMenuRepositories = MenuRepositories.getInstance();
        mArrayMenu = mMenuRepositories.getListMenu();
    }

    public MutableLiveData<List<MenuModel>> getmArrayMenu() {
        return mArrayMenu;
    }

    //khởi tạo + get data spiner + insert data menu
    public void insertMenu(MenuModel menuModel){
        List<MenuModel> list = mArrayMenu.getValue();
        list.add(menuModel);
        mArrayMenu.postValue(list);
    }

    public void initAddMenu(){
        if(mArrayListTitle != null){
            return;
        }
        mMenuRepositories = new MenuRepositories();
        mArrayListTitle = mMenuRepositories.getListTitle();
    }

    public MutableLiveData<List<String>>getArayListTile(){
        return mArrayListTitle;
    }
}
