package com.example.baseprojectandroid.src.repositories.staft_repositories;

import androidx.lifecycle.MutableLiveData;

import com.example.baseprojectandroid.models.staft_model.StaftModel;

import java.util.ArrayList;
import java.util.List;

public class StaftRepositories {
    private static  StaftRepositories instanceStarft;
    private static List<StaftModel> mListStaft = new ArrayList<>();
    private static List<String>mListRoles = new ArrayList<>();

    public static StaftRepositories getInstance(){
        if (instanceStarft == null){
            instanceStarft = new StaftRepositories();
        }
        return instanceStarft;
    }
    
    // get list staft
    public MutableLiveData<List<StaftModel>> getListStaft(){
        MutableLiveData<List<StaftModel>> arrayTmp = new MutableLiveData<>();
        setDataStaff();
        arrayTmp.setValue(mListStaft);
        return arrayTmp;
    }

    private void setDataStaff(){
        mListStaft.clear();
        for (int i = 0; i <= 2; i++){
            StaftModel models = new StaftModel();
            models.setmNameStaft("Account Demo");
            models.setmRole("Role");
            mListStaft.add(models);
        }
    }

    //get list roles
    public MutableLiveData<List<String>>getListRoles(){
        MutableLiveData<List<String>> arrayTmt = new MutableLiveData<>();
        setDataRoles();
        arrayTmt.setValue(mListRoles);
        return arrayTmt;
    }

    private void setDataRoles(){
        mListRoles.clear();
        mListRoles.add("Staff");
        mListRoles.add("Admin");
        mListRoles.add("Cashier");
    }
}
