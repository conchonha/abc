package com.example.baseprojectandroid.src.page.bottom_navigator_bar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;

import com.example.baseprojectandroid.R;
import com.example.baseprojectandroid.src.adapter.bottom_navigator_bar.BottomNavigatorAdapter;
import com.example.baseprojectandroid.src.fragment.fragment_bill.FragmentBill;
import com.example.baseprojectandroid.src.fragment.fragment_menu.FragmentMenu;
import com.example.baseprojectandroid.src.fragment.fragment_staft.FragmentStaft;
import com.example.baseprojectandroid.src.fragment.fragment_table.FragmentTable;
import com.google.android.material.tabs.TabLayout;

public class BottomNavigatorBarActivity extends AppCompatActivity {
    private ViewPager mViewPager;
    private TabLayout mTabLayout;
    private BottomNavigatorAdapter mAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bottom_navigator_bar);
        initView();
        init();
    }

    // khởi tạo adapter
    private void init() {
        mAdapter = new BottomNavigatorAdapter(getSupportFragmentManager());
        mAdapter.addFragment(new FragmentMenu(),"Menu");
        mAdapter.addFragment(new FragmentTable(),"Table");
        mAdapter.addFragment(new FragmentStaft(),"Staft");
        mAdapter.addFragment(new FragmentBill(),"Bill");

        //set adapter cho viewpager
        mViewPager.setAdapter(mAdapter);

        //set icon cho tablayout
        mTabLayout.setupWithViewPager(mViewPager);
        mTabLayout.getTabAt(0).setIcon(R.drawable.ic_menu);
        mTabLayout.getTabAt(1).setIcon(R.drawable.ic_table);
        mTabLayout.getTabAt(2).setIcon(R.drawable.ic_staff);
        mTabLayout.getTabAt(3).setIcon(R.drawable.ic_bill);
    }

    // ánh xạ view
    private void initView() {
        mViewPager = findViewById(R.id.viewpager);
        mTabLayout = findViewById(R.id.tab_layout);
    }
}