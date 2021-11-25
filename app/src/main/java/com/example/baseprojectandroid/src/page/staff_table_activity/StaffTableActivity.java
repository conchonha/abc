package com.example.baseprojectandroid.src.page.staff_table_activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.baseprojectandroid.R;
import com.example.baseprojectandroid.compoments.toolbar.Toolbar;
import com.example.baseprojectandroid.models.callback.CallbackToolbar;
import com.example.baseprojectandroid.models.table_model.TableModel;
import com.example.baseprojectandroid.src.adapter.table_adapter.TableAdapter;
import com.example.baseprojectandroid.src.viewmodel.table_viewmodel.TableViewModel;

import java.util.ArrayList;
import java.util.List;

public class StaffTableActivity extends AppCompatActivity {
    private RecyclerView mRecyclerView;
    private TableViewModel mTableViewModel;

    //variable
    private TableAdapter mTableAdapter;
    private Toolbar mToolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_staff_table);
        initViewModel();
        initView();
        init();
    }

    private void init() {
        //set recycler view
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new GridLayoutManager(StaffTableActivity.this,2));
        mTableAdapter = new TableAdapter((ArrayList) mTableViewModel.getmArrayTable().getValue(),getBaseContext(),R.layout.layout_item_listtable,getSupportFragmentManager());
        mRecyclerView.setAdapter(mTableAdapter);
        mTableAdapter.notifyDataSetChanged();

        //setTool bar
        mToolbar = new Toolbar();
        getSupportFragmentManager().beginTransaction().add(R.id.linear_staff_table,mToolbar,"toolbar_staff_table").commit();
        CallbackToolbar callbackToolbar = mToolbar;
        callbackToolbar.onReceiveTitle(getString(R.string.lbl_wellcome)+", ...");
    }

    //create viewmodel
    private void initViewModel() {
        mTableViewModel = ViewModelProviders.of(StaffTableActivity.this).get(TableViewModel.class);
        mTableViewModel.initTable();

        //lắng nghe và quan sát dữ liệu
        mTableViewModel.getmArrayTable().observe(StaffTableActivity.this, new Observer<List<TableModel>>() {
            @Override
            public void onChanged(List<TableModel> tableModels) {
                mTableAdapter.notifyDataSetChanged();
            }
        });
    }

    //ánh xạ view
    private void initView() {
        mRecyclerView = findViewById(R.id.recyclerview_staff_table);
    }
}