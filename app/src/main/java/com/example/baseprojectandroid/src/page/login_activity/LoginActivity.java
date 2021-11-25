package com.example.baseprojectandroid.src.page.login_activity;

import android.app.Dialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.method.PasswordTransformationMethod;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import com.example.baseprojectandroid.R;
import com.example.baseprojectandroid.src.page.bottom_navigator_bar.BottomNavigatorBarActivity;
import com.example.baseprojectandroid.utils.Helpers;
import com.example.baseprojectandroid.utils.SharePrefs;
import com.example.baseprojectandroid.utils.Validations;

public class LoginActivity extends AppCompatActivity {
    private CardView mCardLogin;
    private EditText mEdtEmail, mEdtPassword;
    private ImageView mImgShowPass;
    private CheckBox mCheckBox;

    //variable
    private Boolean mCheckSaveLogin = false;
    private Boolean mCheckShowPass = false;
    private String TAG = "LoginActivity";
    private Dialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        initView();
        listennerOnclicked();
    }

    // lắng nghe sự kiện onclick view
    private void listennerOnclicked() {
        // login
        mCardLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (checkVaidation()) {
                    new AsyncTask<Void, Void, Void>() {
                        @Override
                        protected void onPreExecute() {
                            dialog = Helpers.showLoadingDialog(LoginActivity.this);
                            dialog.show();
                            super.onPreExecute();
                        }

                        @Override
                        protected Void doInBackground(Void... voids) {
                            try {
                                Thread.sleep(3000);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                            return null;
                        }

                        @Override
                        protected void onPostExecute(Void aVoid) {
                            super.onPostExecute(aVoid);
                            dialog.dismiss();
                            startActivity(new Intent(getApplicationContext(), BottomNavigatorBarActivity.class));
                            overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_right);
                            new SharePrefs(LoginActivity.this).saveIsLogin(mCheckSaveLogin);
                            finish();
                        }
                    }.execute();
                }
            }
        });

        // lắng nghe checkbox
        mCheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                Log.d(TAG, "onCheckedChanged: " + isChecked);
                mCheckSaveLogin = isChecked;
            }
        });

        // check show password
        mImgShowPass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mCheckShowPass = !mCheckShowPass;
                if (mCheckShowPass) {
                    mImgShowPass.setImageDrawable(getResources().getDrawable(R.drawable.ic_show_pass));
                    mEdtPassword.setTransformationMethod(new PasswordTransformationMethod());
                } else {
                    mImgShowPass.setImageDrawable(getResources().getDrawable(R.drawable.ic_hide_pass));
                    mEdtPassword.setTransformationMethod(null);
                }
            }
        });

    }

    // check validation
    private boolean checkVaidation() {
        if (!Validations.isEmailValid(mEdtEmail.getText().toString())) {
            mEdtEmail.setError(getResources().getString(R.string.lbl_err_invalid_email));
            return false;
        }
        mEdtEmail.setError(null);

        if (!Validations.isPasswordValid(mEdtPassword.getText().toString())) {
            mEdtPassword.setError(getResources().getString(R.string.lbl_err_invalid_password));
            return false;
        }
        mEdtPassword.setError(null);

        return true;

    }

    //ánh xạ view
    private void initView() {
        mCheckBox = findViewById(R.id.check_box);
        mCardLogin = findViewById(R.id.btn_login);
        mEdtPassword = findViewById(R.id.edt_pass);
        mEdtEmail = findViewById(R.id.edt_email);
        mImgShowPass = findViewById(R.id.img_show_pass);
    }
}