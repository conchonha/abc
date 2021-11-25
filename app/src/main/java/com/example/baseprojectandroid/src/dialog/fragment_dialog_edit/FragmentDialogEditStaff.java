package com.example.baseprojectandroid.src.dialog.fragment_dialog_edit;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.baseprojectandroid.R;
import com.example.baseprojectandroid.models.callback.CallbackStaff;
import com.example.baseprojectandroid.models.staft_model.StaftModel;
import com.example.baseprojectandroid.src.viewmodel.staft_viewmodel.StaftViewModel;
import com.example.baseprojectandroid.utils.Constain;
import com.example.baseprojectandroid.utils.Helpers;
import com.example.baseprojectandroid.utils.Validations;

import java.util.List;

import static android.app.Activity.RESULT_OK;

public class FragmentDialogEditStaff extends DialogFragment implements CallbackStaff {
    private View mView;
    private EditText mEdtFullName, mEdtAge, mEdtAddress, mEdtPhoneNumber;
    private ImageView mImageView;
    private Spinner mSpiner;
    private Button mBtnCancel, mBtnUpdate;
    private TextView mTxtTitle;

    //variable
    private StaftViewModel mStaftViewModel;
    private String mType = "";
    private final int REQUEST_CODE_LOAD_IMAGE = 01234;
    private String mUriImage = "";
    private Dialog mDialog;
    private StaftModel mStaftModel;
    private int mPosition = 0;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.fragment_dialog_add_staff, container, false);
        initView();
        initViewModel();
        setDataSpiner();
        listenerOnclickedView();
        updateUi();
        return mView;
    }

    private void updateUi() {
        if(mStaftModel != null){
            mTxtTitle.setText(getString(R.string.lbl_update));
            mBtnUpdate.setText(getString(R.string.lbl_update));
            mEdtFullName.setText(mStaftModel.getmNameStaft()+"");
            mEdtAge.setText(mStaftModel.getmAge()+"");
            mEdtAddress.setText(mStaftModel.getmAddress()+"");
            mEdtPhoneNumber.setText(mStaftModel.getmPhoneNumber()+"");
            mSpiner.getItemAtPosition(mStaftViewModel.getmArrayRoles().getValue().indexOf(mStaftModel.getmRole()));
        }
    }

    // lắng nghe sự kiện onclick view
    private void listenerOnclickedView() {
        //item selected spiner
        mSpiner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                mType = mSpiner.getSelectedItem().toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        //selected image category
        mImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_PICK);
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(intent, REQUEST_CODE_LOAD_IMAGE);
            }
        });

        //add staff
        mBtnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (checkValidation()) {
                    final String fullName = mEdtFullName.getText().toString();
                    final int age = Integer.parseInt(mEdtAge.getText().toString());
                    final String address = mEdtAddress.getText().toString();
                    final String phone = mEdtPhoneNumber.getText().toString();

                    new AsyncTask<Void, Void, Void>() {
                        @Override
                        protected void onPreExecute() {
                            super.onPreExecute();
                            mDialog = Helpers.showLoadingDialog(getActivity());
                            mDialog.show();
                        }

                        @Override
                        protected Void doInBackground(Void... voids) {
                            //create model staft
                            StaftModel staftModel = new StaftModel(fullName, age, address, phone, mType, mUriImage);
                            try {
                                Thread.sleep(3000);
                                mStaftViewModel.updateStaff(staftModel,mPosition);
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

                            Helpers.hideFragmentDialog(FragmentDialogEditStaff.this, Constain.dialogStaffEdit);
                        }
                    }.execute();
                }
            }
        });

        //cancel dialog fragment
        mBtnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Helpers.hideFragmentDialog(FragmentDialogEditStaff.this, Constain.dialogStaffEdit);
            }
        });
    }

    // check validation
    private boolean checkValidation() {
        if (Validations.isValidName(mEdtFullName.getText().toString())) {
            mEdtFullName.setError(getString(R.string.lbl_err_name_invalid));
            return false;
        }
        mEdtFullName.setError(null);

        if (mEdtAge.getText().toString().equals("")) {
            mEdtAge.setError(getString(R.string.lbl_err_age_invalid));
            return false;
        }
        mEdtAge.setError(null);

        if (Validations.isValidAddress(mEdtAddress.getText().toString())) {
            mEdtAddress.setError(getString(R.string.lbl_err_address_invalid));
            return false;
        }
        mEdtAddress.setError(null);

        if (!Validations.isValidPhoneNumber(mEdtPhoneNumber.getText().toString())) {
            mEdtPhoneNumber.setError(getString(R.string.lbl_err_phone_invalid));
            return false;
        }
        mEdtPhoneNumber.setError(null);

        if (mUriImage.equals("")) {
            Toast.makeText(getContext(), getString(R.string.lbl_err_image_invalid), Toast.LENGTH_SHORT).show();
            mImageView.setBackgroundColor(Color.RED);
            return false;
        }
        mImageView.setBackgroundColor(getResources().getColor(R.color.transparent));

        return true;
    }

    // set data spiner
    private void setDataSpiner() {
        ArrayAdapter<String> adapter = new ArrayAdapter(mView.getContext(),
                android.R.layout.simple_spinner_item,
                mStaftViewModel.getmArrayRoles().getValue());

        mType = mStaftViewModel.getmArrayRoles().getValue().get(0);
        adapter.setDropDownViewResource(android.R.layout.simple_list_item_single_choice);

        // set adapter spiner
        mSpiner.setAdapter(adapter);

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE_LOAD_IMAGE && resultCode == RESULT_OK && data != null) {
            Uri uri = data.getData();
            mUriImage = uri.getPath();
            mImageView.setImageURI(uri);
        }
    }

    // create viewmodel
    private void initViewModel() {
        mStaftViewModel = ViewModelProviders.of(getActivity()).get(StaftViewModel.class);
        mStaftViewModel.initRoles();

        // lắng nghe quan sát dữ liệu thay đổi
        mStaftViewModel.getmArrayRoles().observe(getViewLifecycleOwner(), new Observer<List<String>>() {
            @Override
            public void onChanged(List<String> strings) {

            }
        });
    }

    //ánh xạ view
    private void initView() {
        mEdtFullName = mView.findViewById(R.id.edt_dialog_staff_name);
        mEdtAge = mView.findViewById(R.id.edt_dialog_staff_age);
        mEdtAddress = mView.findViewById(R.id.edt_dialog_staff_address);
        mEdtPhoneNumber = mView.findViewById(R.id.edt_dialog_staff_phone_number);
        mImageView = mView.findViewById(R.id.img_dialog_staff);
        mSpiner = mView.findViewById(R.id.spiner_staff);
        mBtnCancel = mView.findViewById(R.id.btn_cancel);
        mBtnUpdate = mView.findViewById(R.id.btn_add);
        mTxtTitle = mView.findViewById(R.id.txt_title_dialog_fragment_edit);
    }

    @Override
    public void onGetStaff(StaftModel staftModel, int position) {
        mStaftModel = staftModel;
        mPosition = position;
    }
}
