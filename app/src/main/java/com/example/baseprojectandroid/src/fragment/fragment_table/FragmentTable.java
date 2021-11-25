package com.example.baseprojectandroid.src.fragment.fragment_table;

import android.app.Dialog;
import android.os.AsyncTask;
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
import com.example.baseprojectandroid.models.table_model.TableModel;
import com.example.baseprojectandroid.src.adapter.table_adapter.TableAdapter;
import com.example.baseprojectandroid.src.viewmodel.table_viewmodel.TableViewModel;
import com.example.baseprojectandroid.utils.Helpers;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class FragmentTable extends Fragment {
    private View mView;
    private RecyclerView mRecyclerView;
    private TableViewModel mTableViewModel;
    private TableAdapter mAdapter;
    private FloatingActionButton mFabAddTable;
    //variable
    private Dialog mDialog;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.fragment_table, container, false);
        initViewModel();
        initView();
        init();
        listenerOnclicked();
        return mView;
    }

    private void listenerOnclicked() {
        mFabAddTable.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new AsyncTask<Void, Void, Void>() {
                    @Override
                    protected void onPreExecute() {
                        super.onPreExecute();
                        mDialog = Helpers.showLoadingDialog(getActivity());
                        mDialog.show();
                    }

                    @Override
                    protected Void doInBackground(Void... voids) {
                        try {
                            Thread.sleep(3000);
                            mTableViewModel.insertTable();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        return null;
                    }

                    @Override
                    protected void onPostExecute(Void aVoid) {
                        super.onPostExecute(aVoid);
                        mDialog.dismiss();
                    }
                }.execute();
            }
        });
    }

    //create viewmodel
    private void initViewModel() {
        mTableViewModel = ViewModelProviders.of(getActivity()).get(TableViewModel.class);
        mTableViewModel.initTable();

        // lắng nghe quan sát sự thay đổi của dữ liệu
        mTableViewModel.getmArrayTable().observe(getViewLifecycleOwner(), new Observer<List<TableModel>>() {
            @Override
            public void onChanged(List<TableModel> tableModels) {
                mAdapter.notifyDataSetChanged();
            }
        });
    }

    private void init() {
        //create recyclerview
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new GridLayoutManager(mView.getContext(), 2));
        mAdapter = new TableAdapter((ArrayList) mTableViewModel.getmArrayTable().getValue(), mView.getContext(), R.layout.layout_item_listtable,getFragmentManager());
        mRecyclerView.setAdapter(mAdapter);
        mAdapter.notifyDataSetChanged();

        //add toolbar
        Toolbar toolbar = new Toolbar();
        getFragmentManager().beginTransaction().add(R.id.relative_layout_tabe, toolbar, "toolbar_table").commit();
        setCallbackToolbar(toolbar);
    }

    //ánh xạ view
    private void initView() {
        mRecyclerView = mView.findViewById(R.id.recyclerTable);
        mFabAddTable = mView.findViewById(R.id.fab_add_table);
    }

    private void setCallbackToolbar(CallbackToolbar callbackToolbar) {
        CallbackToolbar callbackToolbar1 = callbackToolbar;
        callbackToolbar1.onReceiveTitle(getResources().getString(R.string.lbl_table));
    }


}
