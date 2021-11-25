package com.example.baseprojectandroid.src.fragment.fragment_staft;

import android.app.Dialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.baseprojectandroid.R;
import com.example.baseprojectandroid.compoments.toolbar.Toolbar;
import com.example.baseprojectandroid.models.callback.CallbackToolbar;
import com.example.baseprojectandroid.models.staft_model.StaftModel;
import com.example.baseprojectandroid.src.adapter.staft_adapter.StaftAdapter;
import com.example.baseprojectandroid.src.dialog.fragment_dialog_add.FragmentDialogAddStaff;
import com.example.baseprojectandroid.src.viewmodel.staft_viewmodel.StaftViewModel;
import com.example.baseprojectandroid.utils.Constain;
import com.example.baseprojectandroid.utils.Helpers;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class FragmentStaft extends Fragment {
    private View mView;
    private RecyclerView mRecyclerStaft;
    private StaftViewModel mStaftViewModel;
    private StaftAdapter mAdapterStaft;
    private Toolbar mToolbar;
    private FloatingActionButton mFabStaff;

    //variable
    private Dialog mDialog;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.fragment_staft, container, false);

        initViewmodel();
        initView();
        init();
        listenerOnclickedView();
        return mView;
    }

    private void listenerOnclickedView() {
            mFabStaff.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentDialogAddStaff fragmentDialogAddStaff = new FragmentDialogAddStaff();
                fragmentDialogAddStaff.show(getFragmentManager(), Constain.dialogStaff);
                Toast.makeText(getContext(), "daf", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void init() {
        //set recyclerview
        mRecyclerStaft.setHasFixedSize(true);
        mRecyclerStaft.setLayoutManager(new LinearLayoutManager(mView.getContext()));
        mAdapterStaft = new StaftAdapter(FragmentStaft.this, R.layout.layout_item_staft, (ArrayList) mStaftViewModel.getmArrayStaff().getValue());
        mRecyclerStaft.setAdapter(mAdapterStaft);
        mAdapterStaft.notifyDataSetChanged();

        // add toolbar
        mToolbar = new Toolbar();
        getFragmentManager().beginTransaction().add(R.id.relative_staft, mToolbar, "toolbar_staft").commit();
        setCallbackToolbar(mToolbar);
    }

    private void setCallbackToolbar(CallbackToolbar callbackToolbar) {
        CallbackToolbar callbackToolbar1 = callbackToolbar;
        callbackToolbar1.onReceiveTitle(getString(R.string.lbl_staft));
    }

    //create viewmodel
    private void initViewmodel() {
        mStaftViewModel = ViewModelProviders.of(getActivity()).get(StaftViewModel.class);
        mStaftViewModel.initStaft();

        // lắng nghe quan sát sự thay đổi của dữ liệu
        mStaftViewModel.getmArrayStaff().observe(getViewLifecycleOwner(), new Observer<List<StaftModel>>() {
            @Override
            public void onChanged(List<StaftModel> staftModels) {
                Log.d("11111", "onChange: " + staftModels.size());
                mAdapterStaft.notifyDataSetChanged();
            }
        });
    }

    // ánh xạ view
    private void initView() {
        mRecyclerStaft = mView.findViewById(R.id.recyclerStaft);
        mFabStaff = mView.findViewById(R.id.fab_add_staff);
    }

    //remove staff
    public void removeStaff(final int position){
        final Dialog dialog = new Dialog(getActivity());
        dialog.setContentView(R.layout.layout_dialog_remove);

        //ánh xạ
        Button btnCancel = dialog.findViewById(R.id.btn_cancel);
        Button btnDelete = dialog.findViewById(R.id.btn_delete);

        //onclicked view
        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new AsyncTask<Void, Void, Void>() {
                    @Override
                    protected void onPreExecute() {
                        mDialog = Helpers.showLoadingDialog(getActivity());
                        mDialog.show();
                        super.onPreExecute();
                    }

                    @Override
                    protected Void doInBackground(Void... voids) {
                        try {
                            Thread.sleep(3000);
                            mStaftViewModel.removeStaff(position);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        return null;
                    }

                    @Override
                    protected void onPostExecute(Void aVoid) {
                        super.onPostExecute(aVoid);
                        //close dialog
                        mDialog.dismiss();
                        dialog.dismiss();
                        Fragment prev = getFragmentManager().findFragmentByTag(Constain.dialogStaffEdit);
                        if (prev != null) {
                            DialogFragment df = (DialogFragment) prev;
                            df.dismiss();
                        }
                    }
                }.execute();
            }
        });

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        dialog.show();

    }
}
