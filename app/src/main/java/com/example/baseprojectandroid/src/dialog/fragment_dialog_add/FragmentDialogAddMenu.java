package com.example.baseprojectandroid.src.dialog.fragment_dialog_add;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.baseprojectandroid.R;
import com.example.baseprojectandroid.models.menu_model.MenuModel;
import com.example.baseprojectandroid.src.viewmodel.menu_viewmodel.MenuViewModel;
import com.example.baseprojectandroid.utils.Constain;
import com.example.baseprojectandroid.utils.Helpers;
import com.example.baseprojectandroid.utils.Validations;

import java.util.List;

import static android.app.Activity.RESULT_OK;

public class FragmentDialogAddMenu extends DialogFragment {
    private View mView;
    private Spinner mSpinner;
    private MenuViewModel mMenuViewmodel;
    private Button mBtnCancel, mBtnAdd;
    private EditText mEdtName, mEdtPrice, mEdtNumber;
    private ImageView mImage;

    //variable
    private String mType = "";
    private String TAG = "FragmentDialogAddMenu";
    private final int REQUEST_CODE_LOAD_IMAGE = 01234;
    private String mUriImage = "";
    private Dialog dialog;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.fragment_dialog_add_menu, container, false);
        initView();
        initViewModel();
        setDataSpiner();
        listenerOnclickedView();
        return mView;
    }

    // lắng nghe sự kiện onclick view
    private void listenerOnclickedView() {
        // cancel dialog fragment
        mBtnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Helpers.hideFragmentDialog(FragmentDialogAddMenu.this, Constain.dialogMenuAdd);
            }
        });

        // add new menu
        mBtnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = mEdtName.getText().toString();
                String price = mEdtPrice.getText().toString();
                String number = mEdtNumber.getText().toString();
                String image = "https://images.media-allrecipes.com/userphotos/453291.jpg"; // mUriImage

                if (checkValidationInsertMenu()) {
                    //create model
                    final MenuModel menuModel = new MenuModel(image, name, Integer.parseInt(price), mType, Integer.parseInt(number));

                    //insert menu
                    new AsyncTask<Void, Void, Void>() {
                        @Override
                        protected void onPostExecute(Void aVoid) {
                            // colse dialog
                            dialog.dismiss();
                            Helpers.hideFragmentDialog(FragmentDialogAddMenu.this, Constain.dialogMenuAdd);
                            super.onPostExecute(aVoid);
                        }

                        @Override
                        protected Void doInBackground(Void... voids) {
                            try {
                                Thread.sleep(3000);
                                mMenuViewmodel.insertMenu(menuModel);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }

                            return null;
                        }

                        @Override
                        protected void onPreExecute() {
                            super.onPreExecute();
                            // show dialog
                            dialog = Helpers.showLoadingDialog(getActivity());
                            dialog.show();
                        }
                    }.execute();
                }
            }
        });

        //item selected spiner
        mSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                mType = mSpinner.getSelectedItem().toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        //selected image category
        mImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_PICK);
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(intent, REQUEST_CODE_LOAD_IMAGE);
            }
        });
    }

    // check validation insert menu
    private boolean checkValidationInsertMenu() {
        if (Validations.isValidName(mEdtName.getText().toString())) {
            mEdtName.setError(getString(R.string.lbl_err_name_invalid));
            return false;
        }
        mEdtName.setError(null);

        if (mEdtPrice.getText().toString().equals("")) {
            mEdtPrice.setError(getString(R.string.lbl_err_price_invalid));
            return false;
        }
        mEdtPrice.setError(null);

        if (mUriImage.equals("")) {
            mImage.setBackgroundColor(Color.RED);
            Toast.makeText(getContext(), getString(R.string.lbl_err_image_invalid), Toast.LENGTH_SHORT).show();
            return false;
        }
        mImage.setBackgroundColor(getResources().getColor(R.color.transparent));

        if (mEdtNumber.getText().toString().equals("")) {
            mEdtNumber.setError(getString(R.string.lbl_err_number_invalid));
            return false;
        }
        mEdtNumber.setError(null);

        return true;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE_LOAD_IMAGE && resultCode == RESULT_OK && data != null) {
            Uri uri = data.getData();
            mUriImage = uri.getPath();
            mImage.setImageURI(uri);
        }
    }

    // set data spiner
    private void setDataSpiner() {
        ArrayAdapter<String> adapter = new ArrayAdapter(mView.getContext(),
                android.R.layout.simple_spinner_item,
                mMenuViewmodel.getArayListTile().getValue());

        mType = mMenuViewmodel.getArayListTile().getValue().get(0);
        adapter.setDropDownViewResource(android.R.layout.simple_list_item_single_choice);

        // set adapter spiner
        mSpinner.setAdapter(adapter);

    }

    //khởi tạo viewmodel
    private void initViewModel() {
        mMenuViewmodel = ViewModelProviders.of(getActivity()).get(MenuViewModel.class);
        mMenuViewmodel.initAddMenu();

        //lắng nghe và quan sát dữ liệu
        mMenuViewmodel.getArayListTile().observe(getViewLifecycleOwner(), new Observer<List<String>>() {
            @Override
            public void onChanged(List<String> strings) {
                Log.d(TAG, "onChanged: " + strings.size());
            }
        });
    }

    //ánh xạ view
    private void initView() {
        mSpinner = mView.findViewById(R.id.spiner_add_menu);
        mBtnCancel = mView.findViewById(R.id.btn_cancel);
        mBtnAdd = mView.findViewById(R.id.btn_add);
        mEdtName = mView.findViewById(R.id.edt_dialog_menu_name);
        mEdtNumber = mView.findViewById(R.id.edt_dialog_menu_number);
        mEdtPrice = mView.findViewById(R.id.edt_dialog_menu_price);
        mImage = mView.findViewById(R.id.img_dialog_menu);
    }
}
