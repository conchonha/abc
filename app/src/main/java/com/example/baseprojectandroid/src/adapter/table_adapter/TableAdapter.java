package com.example.baseprojectandroid.src.adapter.table_adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.baseprojectandroid.R;
import com.example.baseprojectandroid.models.table_model.TableModel;
import com.example.baseprojectandroid.src.dialog.fragment_dialog_food_list.FragmentDialogListFood;
import com.example.baseprojectandroid.utils.Constain;

import java.util.ArrayList;

public class TableAdapter extends RecyclerView.Adapter<TableAdapter.ViewHodler> {
    private ArrayList<TableModel> mArrayTable;
    private Context mContext;
    private int mLayout;
    private FragmentManager mFragmentManager;

    public TableAdapter(ArrayList<TableModel> mArrayTable, Context mContext, int mLayout, FragmentManager fragmentManager) {
        this.mArrayTable = mArrayTable;
        this.mContext = mContext;
        this.mLayout = mLayout;
        this.mFragmentManager = fragmentManager;
    }

    @NonNull
    @Override
    public ViewHodler onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = View.inflate(mContext, mLayout, null);
        return new ViewHodler(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHodler holder, int position) {
        holder.mTxtTable.setText((position + 1) + "");
        listenerOnclickedView(holder);
    }

    // lắng nghe sự kiện inclick view
    private void listenerOnclickedView(ViewHodler hodler) {
        hodler.mLinearTable.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentDialogListFood fragmentDialogListFood = new FragmentDialogListFood();
                fragmentDialogListFood.show(mFragmentManager, Constain.dialogFood);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mArrayTable.size();
    }

    public class ViewHodler extends RecyclerView.ViewHolder {
        private TextView mTxtTable;
        private LinearLayout mLinearTable;

        public ViewHodler(@NonNull View itemView) {
            super(itemView);
            mTxtTable = itemView.findViewById(R.id.txt_table);
            mLinearTable = itemView.findViewById(R.id.linear_table);
        }
    }
}
