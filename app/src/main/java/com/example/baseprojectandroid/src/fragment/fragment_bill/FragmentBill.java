package com.example.baseprojectandroid.src.fragment.fragment_bill;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.baseprojectandroid.R;
import com.example.baseprojectandroid.compoments.toolbar.Toolbar;
import com.example.baseprojectandroid.models.callback.CallbackToolbar;
import com.example.baseprojectandroid.src.adapter.bill_adapter.BillAdapter;
import com.example.baseprojectandroid.models.bill_model.BillModel;
import com.example.baseprojectandroid.src.viewmodel.bill_viewmodel.BillViewModel;

import java.util.ArrayList;
import java.util.List;

public class FragmentBill extends Fragment {
    private View mView;
    private RecyclerView mRecyclerView;
    private BillViewModel mBillViewModel;
    private BillAdapter mAdapterBill;
    private Toolbar mToolbar;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.fragment_bill,container,false);

        initViewmodel();
        initView();
        init();
        return mView;
    }

    // ánh xạ view
    private void initView() {
        mRecyclerView = mView.findViewById(R.id.recyclerBill);
    }

    //create viewmodel
    private void initViewmodel() {
        mBillViewModel = ViewModelProviders.of(getActivity()).get(BillViewModel.class);
        mBillViewModel.initBill();

        // lắng nghe quan sát sự thay đổi của dữ liệu
        mBillViewModel.getmArrayBill().observe(getViewLifecycleOwner(), new Observer<List<BillModel>>() {
            @Override
            public void onChanged(List<BillModel> billModels) {
           mAdapterBill.notifyDataSetChanged();
            }
        });
    }

    private void init() {
        //set recyclerview
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(mView.getContext()));
        mAdapterBill = new BillAdapter((ArrayList) mBillViewModel.getmArrayBill().getValue(),mView.getContext(),R.layout.layout_item_bill);
        mRecyclerView.setAdapter(mAdapterBill);
        mAdapterBill.notifyDataSetChanged();

        //add toolbar
        mToolbar = new Toolbar();
        getFragmentManager().beginTransaction().add(R.id.relative_layout_bill, mToolbar,"toolbar_bill").commit();
        setCallbackToolbar(mToolbar);
    }

    private void setCallbackToolbar(CallbackToolbar callbackToolbar) {
        CallbackToolbar callbackToolbar1 = callbackToolbar;
        callbackToolbar1.onReceiveTitle(getString(R.string.lbl_bill));
    }
}
