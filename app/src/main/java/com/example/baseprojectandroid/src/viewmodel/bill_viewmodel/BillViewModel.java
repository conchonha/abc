package com.example.baseprojectandroid.src.viewmodel.bill_viewmodel;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.baseprojectandroid.models.bill_model.BillModel;
import com.example.baseprojectandroid.src.repositories.bill_repositories.BillRepositories;

import java.util.List;

public class BillViewModel extends ViewModel {
    private MutableLiveData<List<BillModel>> mArrayBill;
    private BillRepositories mBillRepositories;

    public void initBill() {
        if (mArrayBill != null) {
            return;
        }
        mBillRepositories = BillRepositories.getInstance();
        mArrayBill = mBillRepositories.getListBill();
    }

    public MutableLiveData<List<BillModel>> getmArrayBill() {
        return mArrayBill;
    }
}
