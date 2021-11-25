package com.example.baseprojectandroid.src.repositories.table_repositories;

import androidx.lifecycle.MutableLiveData;

import com.example.baseprojectandroid.models.table_model.TableModel;

import java.util.ArrayList;
import java.util.List;

public class TableRepositories {

    private static TableRepositories instance;
    public static List<TableModel>mListTable = new ArrayList<>();


    public static TableRepositories getInstance(){
        if (instance == null){
            instance = new TableRepositories();
        }
        return instance;
    }
        //quan sát, lắng nghe dữ liệu
    public MutableLiveData<List<TableModel>> getListTable(){
        MutableLiveData<List<TableModel>> arrayTmp = new MutableLiveData<>();
        setDataTable();
        arrayTmp.setValue(mListTable);
        return arrayTmp;
    }

    private void setDataTable(){
        mListTable.clear();
        for (int i = 0; i <= 5; i++){
            TableModel models = new TableModel();
            models.setmNumber("");
            mListTable.add(models);
        }
    }

}
