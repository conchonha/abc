package com.example.baseprojectandroid.src.fragment.fragment_menu;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.baseprojectandroid.R;
import com.example.baseprojectandroid.compoments.toolbar.Toolbar;
import com.example.baseprojectandroid.models.callback.CallbackToolbar;
import com.example.baseprojectandroid.models.menu_model.MenuModel;
import com.example.baseprojectandroid.src.adapter.menu_adapter.MenuAdapter;
import com.example.baseprojectandroid.src.dialog.fragment_dialog_add.FragmentDialogAddMenu;
import com.example.baseprojectandroid.src.viewmodel.menu_viewmodel.MenuViewModel;
import com.example.baseprojectandroid.utils.Constain;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class FragmentMenu extends Fragment {
    private View mView;
    private RecyclerView mRecyclerMenu;
    private MenuViewModel mMenuViewModel;
    private MenuAdapter mAdapter;
    private FloatingActionButton mFabAddMenu;

    //variable
    private FragmentDialogAddMenu mFragmentDialogMenu;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.fragment_menu, container, false);
        initViewModel();
        initView();
        init();
        listenerOnclicked();
        return mView;
    }

    // lắng nghe sự kiện onclick view
    private void listenerOnclicked() {
        mFabAddMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mFragmentDialogMenu = new FragmentDialogAddMenu();
                mFragmentDialogMenu.show(getFragmentManager(), Constain.dialogMenuAdd);
            }
        });
    }

    //create viewmodel
    private void initViewModel() {
        mMenuViewModel = ViewModelProviders.of(getActivity()).get(MenuViewModel.class);
        mMenuViewModel.initMenu();

        // lắng nghe quan sát sự thay đổi của dữ liệu
        mMenuViewModel.getmArrayMenu().observe(getViewLifecycleOwner(), new Observer<List<MenuModel>>() {
            @Override
            public void onChanged(List<MenuModel> menuModels) {
                mAdapter.notifyDataSetChanged();
            }
        });
    }

    private void init() {
        //set recyclerview
        mRecyclerMenu.setHasFixedSize(true);
        mRecyclerMenu.setLayoutManager(new GridLayoutManager(mView.getContext(), 2));
        mAdapter = new MenuAdapter((ArrayList) mMenuViewModel.getmArrayMenu().getValue(), mView.getContext(), R.layout.layout_item_menu);
        mRecyclerMenu.setAdapter(mAdapter);
        mAdapter.notifyDataSetChanged();

        // add toolbar
        Toolbar mToolbar = new Toolbar();
        getFragmentManager().beginTransaction().add(R.id.relative_layout_menu, mToolbar, "toolbar_menu").commit();
        setCallbackToolbar(mToolbar);
    }

    // ánh xạ view
    private void initView() {
        mRecyclerMenu = mView.findViewById(R.id.recyclerMenu);
        mFabAddMenu = mView.findViewById(R.id.fab_add_menu);
    }

    private void setCallbackToolbar(CallbackToolbar callbackToolbar1) {
        CallbackToolbar callbackToolbar = callbackToolbar1;
        callbackToolbar.onReceiveTitle(getResources().getString(R.string.lbl_menu));
    }
}
