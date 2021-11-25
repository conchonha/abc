package com.example.baseprojectandroid.compoments.toolbar;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.baseprojectandroid.R;
import com.example.baseprojectandroid.models.callback.CallbackToolbar;

public class Toolbar extends Fragment implements CallbackToolbar {
    private View mView;
    private TextView mTxtTitle;
    private String TAG = "FragmentToolbar";
    private String mTitle = "";

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.layout_toolbar,container,false);
        updateUI();
        return mView;
    }

    @Override
    public void onReceiveTitle(String title) {
        this.mTitle = title;
    }

    private void updateUI() {
        mTxtTitle = mView.findViewById(R.id.txt_title_appbar);
        mTxtTitle.setText(mTitle);
    }
}
