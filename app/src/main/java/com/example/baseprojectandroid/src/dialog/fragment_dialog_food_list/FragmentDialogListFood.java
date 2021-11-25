package com.example.baseprojectandroid.src.dialog.fragment_dialog_food_list;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.baseprojectandroid.R;
import com.example.baseprojectandroid.compoments.toolbar.Toolbar;
import com.example.baseprojectandroid.models.food_model.FoodModel;
import com.example.baseprojectandroid.src.adapter.food_adapter.FoodAdapter;
import com.example.baseprojectandroid.src.viewmodel.food_viewmodel.FoodViewmodel;
import com.example.baseprojectandroid.utils.Constain;
import com.example.baseprojectandroid.utils.Helpers;

import java.util.ArrayList;
import java.util.List;

public class FragmentDialogListFood extends DialogFragment {
    private View mView;
    private RecyclerView mRecyclerviewFood;
    private Button mBtnCancel,mBtnAdd;

    //variable
    private Toolbar mToolbar;
    private FoodAdapter mFoodAdapter;
    private FoodViewmodel mFoodViewmodel;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.fragment_dialog_list_food,container,false);
        initViewmodel();
        initView();
        init();
        listenerOnclickedView();
        return mView;
    }

    private void listenerOnclickedView() {
        mBtnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Helpers.hideFragmentDialog(FragmentDialogListFood.this,Constain.dialogFood);
            }
        });
    }

    private void init() {
        //set recyclerview
        mRecyclerviewFood.setHasFixedSize(true);
        mRecyclerviewFood.setLayoutManager(new GridLayoutManager(mView.getContext(),1));
        mFoodAdapter = new FoodAdapter((ArrayList) mFoodViewmodel.getListFood().getValue(),R.layout.layout_item_food,getActivity());
        mRecyclerviewFood.setAdapter(mFoodAdapter);
        mFoodAdapter.notifyDataSetChanged();

    }

    private void initView() {
        mRecyclerviewFood = mView.findViewById(R.id.recyclerview_food);
        mBtnAdd = mView.findViewById(R.id.btn_add);
        mBtnCancel = mView.findViewById(R.id.btn_cancel);
    }

    private void initViewmodel() {
        mFoodViewmodel = ViewModelProviders.of(getActivity()).get(FoodViewmodel.class);
        mFoodViewmodel.initListFood();
        
        //lắng nghe và quan sát dữ liệu
        mFoodViewmodel.getListFood().observe(getViewLifecycleOwner(), new Observer<List<FoodModel>>() {
            @Override
            public void onChanged(List<FoodModel> foodModels) {
                mFoodAdapter.notifyDataSetChanged();
            }
        });
    }
}
