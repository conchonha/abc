package com.example.baseprojectandroid.src.viewmodel.table_viewmodel;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.baseprojectandroid.models.table_model.TableModel;
import com.example.baseprojectandroid.src.repositories.table_repositories.TableRepositories;

import java.util.List;

public class TableViewModel extends ViewModel {
    private MutableLiveData<List<TableModel>> mArrayTable;

    private TableRepositories tableRepositories;

    public void initTable(){
        if (mArrayTable != null){
            return;
        }
        tableRepositories = TableRepositories.getInstance();
        mArrayTable = tableRepositories.getListTable();
    }
    public MutableLiveData<List<TableModel>> getmArrayTable(){
        return mArrayTable;
    }

    public void insertTable(){
        List<TableModel>list = mArrayTable.getValue();
        list.add(new TableModel());
        mArrayTable.postValue(list);
    }

}
